package com.example.cy_rate.Favorites;
import com.example.cy_rate.Business.Business;
import com.example.cy_rate.User.User;



import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Column;
import javax.persistence.OneToOne;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;



import org.springframework.beans.factory.annotation.Autowired;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "Favorites")
public class Favorites {
     //int favId, int userId, int busId
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int favId;
    
    @ManyToOne
    @JoinColumn(name = "userId")
    private User userId;

    @ManyToOne
    @JoinColumn(name = "busId")
    private Business busId;

    public Favorites(){
        
    }



}
