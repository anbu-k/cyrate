package com.cs309.demo.restaurant;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path = "api/v1/restaurant")
public class RestaurantController {

    @GetMapping
    public List<Restaurant> getRestaurants(){
        return List.of(
                new Restaurant(
                        1,
                        "AppleBees",
                        4
                ),
                new Restaurant(
                        2,
                        "Cactus",
                        5
                )
        );
    }
}
