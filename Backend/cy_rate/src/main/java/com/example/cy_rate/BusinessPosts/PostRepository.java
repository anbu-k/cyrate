package com.example.cy_rate.BusinessPosts;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import com.example.cy_rate.Business.Business;

public interface PostRepository extends JpaRepository<Post, Integer>{
    Post findByBid(int bid);
    void deleteByBid(int bid);
    List<Post> findByBusinessId(Business b);
    
}

