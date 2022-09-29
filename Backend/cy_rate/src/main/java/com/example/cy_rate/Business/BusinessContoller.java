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

    /**
     * Get all business from remote db
     * 
     * @return all businesses in business table
     */
    @GetMapping(path = "/business/all")
    List<Business> getAllBusiness()
    {
        return businessRepo.findAll();
    }

    /**
     * Returns specific business from Business table
     * @param id
     * @return business found by id given
     */
    @GetMapping(path = "/business/byId/{id}")
    Business getBusinessById(@PathVariable int id)
    {
        return businessRepo.findById(id);
    }

    /**
     * Create a business by passing json obj
     * @param bus
     * @return success/failure str
     */
    @PostMapping(path = "/business/create")
    String createBusiness(@RequestBody Business bus)
    {
        if(bus == null)
        {
            return failure;
        }
        businessRepo.save(bus);
        
        return success;
    }

    /**
     * Deletes business identified by id from business table in db
     * 
     * @param id
     * @return success/failure str
     */
    @DeleteMapping(path = "/business/delete/{id}")
    String deleteBusiness(@PathVariable int id)
    {
        businessRepo.deleteById(id);
        return success;
    }

}
