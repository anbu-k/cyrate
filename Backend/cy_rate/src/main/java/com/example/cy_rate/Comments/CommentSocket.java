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
import com.example.cy_rate.User.User;
import com.example.cy_rate.User.UserRepository;

@Controller
@ServerEndpoint(value = "/comments/{uid}/{bid}")
public class CommentSocket {
    @Autowired
    private BusinessRepository busRepo;
    
    @Autowired
    private UserRepository userRepo;


    // Think you have to use method b/c of static
    private static CommentRepository commentRepo;

    @Autowired
    public void setCommentRepository(CommentRepository repo)
    {
        commentRepo = repo;
    }

    private static Map< Session, String > sessionUsernameMap = new Hashtable<>();
    private static Map< String, Session > usernameSessionMap = new Hashtable<>();

    private final Logger logger = LoggerFactory.getLogger(CommentSocket.class);

    @OnOpen
    public void onOpen(Session session, @PathParam("uid") int uid, @PathParam("bid") int bid)
    throws IOException {
        logger.info("Entered into open ?");
        
        User usr = userRepo.findById(uid);
        Business bus = busRepo.findById(bid);

        sessionUsernameMap.put(session, usr.getUsername());
        usernameSessionMap.put(usr.getUsername(), session);

   
        sendMessageToParticularUser(usr.getUsername(), getAllComments());

        String message = "User:" + usr.getUsername() + "connected to live comments";
        //broadcast(message);

        //sessionUsernameMap.put(session);
    }

    @OnMessage
	public void onMessage(Session session, String message) throws IOException {

		// Handle new messages
		logger.info("Entered into Message: Got Message:" + message);
        
		String username = sessionUsernameMap.get(session);

    // Direct message to a user using the format "@username <message>"
		if (message.startsWith("@")) {
			String destUsername = message.split(" ")[0].substring(1); 

      // send the message to the sender and receiver
			//sendMessageToParticularUser(destUsername, "[DM] " + username + ": " + message);
			//sendMessageToParticularUser(username, "[DM] " + username + ": " + message);

		} 
    else { // broadcast
			//broadcast(username + ": " + message);
		}

		// Saving chat history to repository
		// commentRepo.save();
	}



    private void sendMessageToParticularUser(String username, List<Comment> comments) {
		try {
            for(Comment comment : comments){
                usernameSessionMap.get(username).getBasicRemote().sendObject(comment);
            }
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
