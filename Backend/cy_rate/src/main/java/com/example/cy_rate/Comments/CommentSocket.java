package com.example.cy_rate.Comments;

import java.io.IOException;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;

import javax.websocket.EncodeException;
import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.example.cy_rate.Business.Business;
import com.example.cy_rate.Business.BusinessRepository;
import com.example.cy_rate.Review.ReviewRepository;
import com.example.cy_rate.User.User;
import com.example.cy_rate.User.UserRepository;
import com.example.cy_rate.BusinessPosts.Post;
import com.example.cy_rate.BusinessPosts.PostRepository;
import com.example.cy_rate.Review.Review;

import com.google.gson.Gson;


@Controller
@ServerEndpoint(value = "/comments/{id}/{uid}") //"/comments/review/rid of review/uid that is connecting/commenting"
public class CommentSocket {                           //"/comments/businessPost/post id/uid that is connecting/commenting"
    private static BusinessRepository busRepo;
    
    private static UserRepository userRepo;

    private static ReviewRepository reviewRepo;

    private static PostRepository postRepo;

    private User usr;
    private Business business;

    // Think you have to use method b/c of static
    private static CommentRepository commentRepo;

    @Autowired
    public void setUserRepository(UserRepository repo)
    {
        userRepo = repo;
    }

    @Autowired
    public void setReviewRepository(ReviewRepository repo)
    {
        reviewRepo = repo;
    }

    @Autowired
    public void setCommentRepository(CommentRepository repo)
    {
        commentRepo = repo;
    }

    @Autowired
    public void setPostRepository(PostRepository repo)
    {
        postRepo = repo;
    }

    private static Map< Session, String > sessionUsernameMap = new Hashtable<>();
    private static Map< String, Session > usernameSessionMap = new Hashtable<>();

    private final Logger logger = LoggerFactory.getLogger(CommentSocket.class);

    @OnOpen
    public void onOpen(Session session, @PathParam("id") int id, @PathParam("uid") int uid, @PathParam("type") String type)
    throws IOException {
        logger.info("Entered into open ?");
        usr = userRepo.findById(uid);
 
        /**
         * "Review" (1)
         *      "comment" (1)
         *      "comment" (2)
         * 
         */
        //Business bus = review.getBusiness();

        sessionUsernameMap.put(session, usr.getUsername());
        usernameSessionMap.put(usr.getUsername(), session);

        sendMessageToParticularUser(usr.getUsername(), getAllComments());

        String message = "User:" + usr.getUsername() + "connected to live comments";
        //broadcast(message);

        //sessionUsernameMap.put(session);
    }

    @OnMessage
	public void onMessage(Session session, String message, @PathParam("id") int id) throws IOException {
        // handle type
		// Handle new messages
		logger.info("Entered into Message: Got Message:" + message);
        
        // message is the request body sent to the websocket
        // parse the string into a json object
        // check type and set review/busPost accordingly
        Gson gson = new Gson();
        Comment comment = gson.fromJson(message, Comment.class);
        if(comment.getCommentType().equals("review"))
        {
            Review review = reviewRepo.findById(id);
            if(review == null)
            {
             //send review not found message   
            }
            comment.setReview(review);
        }
        else if(comment.getCommentType().equals("busPost"))
        {
            Post post = postRepo.findById(id);
            if(post == null)
            {
            //send post not found message
            }
            comment.setPost(post);
        }
        
		String username = sessionUsernameMap.get(session);
		//broadcast(username + ": " + message);

		// Saving chat history to repository
        comment.setUser(usr); //set user as well
		commentRepo.save(comment);
	}



    private void sendMessageToParticularUser(String username, List<Comment> comments) {
		try {
            Gson gson = new Gson();
            for(Comment comment : comments){
                usernameSessionMap.get(username).getBasicRemote().sendText(gson.toJson(comment));
            }
		} 
        catch (IOException e) {
			logger.info("Exception: " + e.getMessage().toString());
			e.printStackTrace();
		}
        // catch(EncodeException s)
        // {
        //     logger.info("Exception: " + s.getMessage().toString());
		// 	s.printStackTrace();
        // }
	}

    private List<Comment> getAllComments()
    {
        return commentRepo.findAll();
    }

    private void broadcast(Comment comment)
    {
        sessionUsernameMap.forEach((session, username) -> {
            try {
                session.getBasicRemote().sendObject(comment);
            }
            catch (IOException e) {
                logger.info("Exception: " + e.getMessage().toString());
                e.printStackTrace();
            }
            catch(EncodeException s)
            {
                logger.info("Exception: " + s.getMessage().toString());
                s.printStackTrace();
            }
        });
    }
}
