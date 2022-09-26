package com.example.cy_rate.Business;
import org.springframework.data.jpa.repository.JpaRepository;


public interface BusinessRepository extends JpaRepository<Business, Integer> {
    Business findById(int id);
    void deleteById(int id);
}
