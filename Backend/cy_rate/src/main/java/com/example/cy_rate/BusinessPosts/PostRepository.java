package com.example.cy_rate.BusinessPosts;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import com.example.cy_rate.Business.Business;

public interface PostRepository extends JpaRepository<Post, Integer>{
    Post findById(int id);
    void deleteById(int id);
    
}

