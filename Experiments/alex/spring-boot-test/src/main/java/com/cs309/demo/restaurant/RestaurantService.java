package com.cs309.demo.restaurant;

import org.springframework.stereotype.Service;

import java.util.List;

/** RestaurantService acts as the backend
 * service for handling business logic. Ideally, an API layer
 * (Controller) would make calls to the Service to get info.
 * The Service would then have the ability to connect and talk
 * to the Database layer to get and manipulate data.
 */
@Service
public class RestaurantService {

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
