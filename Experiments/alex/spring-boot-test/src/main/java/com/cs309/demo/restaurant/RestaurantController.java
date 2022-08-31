package com.cs309.demo.restaurant;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path = "api/v1/restaurant")
public class RestaurantController {

    private final RestaurantService restaurantService;

    // Autowired allows for Dependency Injection (not having to construct a
    // new instance of RestaurantService but rather passing it through
    @Autowired
    public RestaurantController(RestaurantService restaurantService) {
        this.restaurantService = restaurantService;
    }

    @GetMapping
    public List<Restaurant> getRestaurants(){
       return restaurantService.getRestaurants();
    }
}
