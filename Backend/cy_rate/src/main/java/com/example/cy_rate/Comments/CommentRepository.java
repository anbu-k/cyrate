package com.example.cy_rate.Comments;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment, Integer> {
    Comment findById(int id);
    void deleteById(int id);
}