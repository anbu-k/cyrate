package com.example.cy_rate.Comments;
import com.example.cy_rate.Business.Business;
import com.example.cy_rate.User.User;
import io.swagger.v3.oas.annotations.Hidden;

// JPA stuff
import javax.persistence.Entity;
import javax.persistence.Table;



import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Column;
import javax.persistence.JoinColumn;

@Entity
@Table(name = "Comments")
public class Comment {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Hidden
    @Column(name="cid")
    private int cid;

    private String commenterName;

    @Column
    private String photoUrl;

    @Column
    private String commentBody;

    @Column
    private String date;

    @Hidden
    @ManyToOne
    @JoinColumn(name = "bid", referencedColumnName = "busId")
    private Business business;

    @Hidden
    @ManyToOne 
    @JoinColumn(name = "uid", referencedColumnName = "userId")
    private User user;



    public Comment() {
        this.photoUrl = "";
        this.commentBody = "";
        this.date = "";
        this.commenterName = "";
    }

    public Comment(String photoUrl, String commentBody, String date)
    {
        this.photoUrl = photoUrl;
        this.commentBody = commentBody;
        this.date = date;
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

    public String getphotoUrl()
    {
        return photoUrl;
    }

    public void setphotoUrl(String photoUrl)
    {
        this.photoUrl = photoUrl;
    }

    public String getdate()
    {
        return date;
    }

    public void setdate(String date)
    {
        this.date = date;
    }

    public Business getBusiness()
    {
        return business;
    }

    public void setBusiness(Business bus)
    {
        this.business = bus;
    }

    public User getUser()
    {
        return user;
    }

    public void setUser(User user)
    {
        this.user = user;
    }

    public String getCommenterName()
    {
        return commenterName;
    }

    public void setCommenterName(String commenterName)
    {
        this.commenterName = commenterName;
    }

}
