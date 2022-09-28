package com.example.cy_rate.Business;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;



@RestController
public class BusinessContoller {
    @Autowired
    BusinessRepository businessRepo;

    private String success = "{\"message\":\"success\"}";
    private String failure = "{\"message\":\"failure\"}";

    @GetMapping(path = "/business")
    List<Business> getAllBusiness()
    {
        return businessRepo.findAll();
    }

    @PostMapping(path = "/business")
    String createBusiness(@RequestBody Business bus)
    {
        if(bus == null)
        {
            return failure;
        }
        businessRepo.save(bus);
        
        return success;
    }

    @DeleteMapping(path = "/business/{id}")
    String deleteBusiness(@PathVariable int id)
    {
        businessRepo.deleteById(id);
        return success;
    }

}
