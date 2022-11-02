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

import com.example.cy_rate.Business.Business;
import com.example.cy_rate.Business.BusinessRepository;
import com.example.cy_rate.User.User;
import com.example.cy_rate.User.UserRepository;
import com.example.cy_rate.Favorites.FavoritesRepository;



@RestController
public class FavoritesController {
    @Autowired
    FavoritesRepository favRepo;

    @Autowired
    UserRepository userRepo;

    @Autowired 
    BusinessRepository businessRepo;

    private String success = "{\"message\":\"success\"}";
    private String failure = "{\"message\":\"failure\"}";

    /**
     * 
     * @return
     */
    @GetMapping(path = "/favorites/all")
    List<Favorites> getAllFavorites()
    {
        return favRepo.findAll();
    }

    /**
     * 
     */
    @GetMapping(path = "/favorites/user/{uid}")
    List<Favorites> getFavoritesByUser(@PathVariable int uid)
    {
        User u = userRepo.findById(uid);
        return favRepo.findByUser(u);
    }

    /**
     * 
     * @param bid
     * @return
     */
    @GetMapping(path = "/favorites/business/{bid}")
    List<Favorites> getFavoritesByBusiness(@PathVariable int bid)
    {
        Business b = businessRepo.findById(bid);
        return favRepo.findByBusiness(b);
    }

    /**
     * 
     * @param id
     * @return
     */
    @PostMapping(path = "/favorites/{bid}/user/{uid}")
    String chooseFavorite(@PathVariable int bid, @PathVariable int uid, @RequestBody Favorites favorite)
    {
        try {
            Business b = businessRepo.findById(bid);
            User u = userRepo.findById(uid);

            favorite.setBusiness(b);
            favorite.setUser(u);
            favRepo.save(favorite);

            return success;
        } catch (Exception e) {
            return e.toString();
        }
    }

    @DeleteMapping(path = "/favorites/delete/{id}")
    String deleteFavorite(@PathVariable int id)
    {
        favRepo.deleteById(id);
        return success;
    }

}
