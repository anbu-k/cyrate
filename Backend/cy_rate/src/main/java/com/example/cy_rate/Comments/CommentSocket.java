package com.example.cy_rate.Comments;

import java.io.IOException;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;
import java.util.ArrayList;

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

import ch.qos.logback.core.joran.conditional.ElseAction;

/**
 * Sample Tester
 * ws://localhost:8080/comments/businessPost/5/7
 * {"commenterName": "Anbu Krishnan", "commentType": "businessPost", "photoUrl": "google.images.com", "commentBody": "comment 8:15", "date":"12/2/2022"}
 */

@Controller
@ServerEndpoint(value = "/comments/{type}/{id}/{uid}") //"/comments/review/rid of review/uid that is connecting/commenting"
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
        sessionUsernameMap.put(session, usr.getUsername());
        usernameSessionMap.put(usr.getUsername(), session);
        
        // see if post/review exists
        // if doesn't exist close the session
        // remove session from maps
        if(!checkPostExists(type, id)){
            session.close();
            sessionUsernameMap.remove(session);
            usernameSessionMap.remove(usr.getUsername());
        }
 
        

        sendMessageToParticularUser(usr.getUsername(), getAllComments(type, id));

        //String message = "User:" + usr.getUsername() + "connected to live comments";
        //broadcast();
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
            comment.setReview(review);
        }
        else if(comment.getCommentType().equals("businessPost"))
        {
            Post post = postRepo.findById(id);
            comment.setPost(post);
        }
        
		// Saving chat history to repository
        comment.setUser(usr); //set user as well
        broadcast(comment);
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
	}

    private List<Comment> getAllComments(String type, int id)
    {
        if(type.equals("review"))
        {
            Review review = reviewRepo.findById(id);
            return commentRepo.findByReview(review);
        }
        else if(type.equals("businessPost"))
        {
            Post post = postRepo.findById(id);
            return commentRepo.findByPost(post);
        }
        else
        {
            return new ArrayList<Comment>();
        }
    }

    private void broadcast(Comment comment)
    {

        sessionUsernameMap.forEach((session, username) -> {
            try {
                Gson gson = new Gson();
                if(!usr.getUsername().equals(username)){
                    usernameSessionMap.get(username).getBasicRemote().sendText(gson.toJson(comment));
                }
            }
            catch (IOException e) {
                logger.info("Exception: " + e.getMessage().toString());
                e.printStackTrace();
            }
        });
    }

    private boolean checkPostExists(String type, int id)
    {
        try{
            if(type.equals("review"))
            {
                Review review = reviewRepo.findById(id);
                if(review == null)
                {
                    usernameSessionMap.get(usr.getUsername()).getBasicRemote().sendText("Reiew with ID: " + id + " not found");
                    return false;
                }
            }
            else if(type.equals("businessPost"))
            {
                Post post = postRepo.findById(id);
                if(post == null)
                {
                    usernameSessionMap.get(usr.getUsername()).getBasicRemote().sendText("Business Post with ID: " + id + " not found");
                    return false;
                }
            }
            //post exists
            return true;
        }
        catch (IOException e) {
            logger.info("Exception: " + e.getMessage().toString());
            e.printStackTrace();
            return false;
        }
    }
}
