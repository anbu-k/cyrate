package com.example.cy_rate.Favorites;


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
public class FavoritesController {
    @Autowired
    FavoritesRepository favRepo;

    private String success = "{\"message\":\"success\"}";
    private String failure = "{\"message\":\"failure\"}";

    @GetMapping(path = "/favorites/all")
    List<Favorites> getAllFavorites()
    {
        return favRepo.findAll();
    }

    @GetMapping(path = "/favorites/byId/{id}")
    Favorites getUserById(@PathVariable int id)
    {
        return favRepo.findById(id);
    }

    @PostMapping(path = "/favorites/create")
    String chooseFavorite(@RequestBody Favorites fav)
    {
        if(fav == null)
        {
            return failure;
        }
        favRepo.save(fav);
        return success;
    }

    @DeleteMapping(path = "/favorites/delete/{id}")
    String deleteFavorite(@PathVariable int id)
    {
        favRepo.deleteById(id);
        return success;
    }

    
    
}
