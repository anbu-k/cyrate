package com.example.cy_rate.Favorites;
import org.springframework.data.jpa.repository.JpaRepository;


public interface FavoritesRepository extends JpaRepository<Favorites, Integer>{
    Favorites findById(int id);
    void deleteById(int id);
}
