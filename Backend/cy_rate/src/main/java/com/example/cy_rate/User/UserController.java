package com.example.cy_rate.User;

import java.util.List;
import com.example.cy_rate.User.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {
    @Autowired
    UserRepository userRepo;

    private String success = "{\"message\":\"success\"}";
    private String failure = "{\"message\":\"failure\"}";

    /**
     * Gets all of the users from the remote db
     * 
     * @return all users in user table
     */
    @GetMapping(path = "/user")
    List<User> getUser()
    {
        return userRepo.findAll();
    }

    @PostMapping(path = "/user")
    String createUser(@RequestBody User user)
    {
        if(user == null)
        return failure;
        userRepo.save(user);
        return success;
    }

    @PostMapping(path = "/user")







    
}
