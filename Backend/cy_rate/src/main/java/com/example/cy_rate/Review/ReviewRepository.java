package com.example.cy_rate.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;


public interface ReviewRepository extends JpaRepository<Review, Integer>{
    Review findById(int id);
    List<Review> findByUser(int userID);
    List<Review> findByBusiness(int busID);
    void deleteById(int id);
}
