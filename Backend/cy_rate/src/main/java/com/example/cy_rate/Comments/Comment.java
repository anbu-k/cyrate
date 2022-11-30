package com.example.cy_rate.Comments;
import com.example.cy_rate.Business.Business;
import com.example.cy_rate.Business.BusinessRepository;
import com.example.cy_rate.User.User;
import com.example.cy_rate.User.UserRepository;
import io.swagger.v3.oas.annotations.Hidden;

// JPA stuff
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Table;

import org.springframework.beans.factory.annotation.Autowired;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonBackReference;


import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.Transient;

@Entity
@Table(name = "Comments")
public class Comment {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Hidden
    @Column(name="cid")
    private int cid;

    @Column
    private String photoURL;

    @Column
    private String commentBody;

    @Column
    private String datePosted;


    public Comment() {
        this.photoURL = "";
        this.commentBody = "";
        this.datePosted = "";
    }

    public Comment(String photoURL, String commentBody, String datePosted)
    {
        this.photoURL = photoURL;
        this.commentBody = commentBody;
        this.datePosted = datePosted;
    }

    //------------ Getter Setters ------------------ //

    public int getCid()
    {
        return cid;
    }

    public void setCid(int cid)
    {
        this.cid = cid;
    }

    public String getPhotoURL()
    {
        return photoURL;
    }

    public void setPhotoURL(String photoURL)
    {
        this.photoURL = photoURL;
    }

    public String getDatePosted()
    {
        return datePosted;
    }

    public void setDatePosted(String datePosted)
    {
        this.datePosted = datePosted;
    }
}
