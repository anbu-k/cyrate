package com.example.cy_rate.BusinessPosts;
import java.util.List;




import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.cy_rate.Business.BusinessRepository;
import com.example.cy_rate.Business.Business;

public class PostController {

    @Autowired
    PostRepository postRepo;

    @Autowired
    BusinessRepository businessRepo;

    private String success = "{\"message\":\"success\"}";
    private String failure = "{\"message\":\"failure\"}";

    /**
     * 
     * @return
     */
    @GetMapping(path = "/posts/all")
    List<Post> getAllPosts()
    {
        return postRepo.findAll();
    }
}