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
     * @return all business posts 
     */
    @GetMapping(path = "/posts/all")
    List<Post> getAllPosts()
    {
        return postRepo.findAll();
    }

    /**
     * 
     * @param bid
     * @return get all the posts for a business by their ID
     */
    @GetMapping(path = "/posts/byBid/{bid}")
    List<Post> getPostsByBusiness(@PathVariable int bid)
    {
        Business b = businessRepo.findById(bid);
        return postRepo.findByBusinessId(b);
    }

    /**
     * 
     * @param p
     * @return create a business post
     */
    @PostMapping(path = "/posts/create")
    String createPost(@RequestBody Post p)
    {
        if(p == null)
        {
            return failure;
        }

        postRepo.save(p);
        return success;
    }

    /**
     * Deletes Post through the bid that its given
     * @param bid
     * @return if the deletion was succesful or a failure
     */
    @DeleteMapping(path = "/posts/delete/{bid}")
    String deletePost(@PathVariable int bid)
    {
        postRepo.deleteByBid(bid);
        return success;
    }

    /**
     * Updates a post by its Business Id
     *  Allows you to edit a business post
     * @param bid
     * @param p
     * @return
     */
    @PutMapping(path = "/posts/updateByBid/{bid}")
    String editPost(@PathVariable int bid, @RequestBody Post p)
    {
        try{
            Post editPost = postRepo.findByBid(bid);

            editPost.setDate(p.getDate());
            editPost.setPostTxt(p.getPostTxt());
            editPost.setPhotoUrl(p.getPhotoUrl());
            postRepo.save(editPost);
        }
        catch(Exception e){
            return "Not able to find Post with id: " + bid;
        }
        return success;
    }
}