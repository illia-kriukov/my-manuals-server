package se.lnu.agile.mymanuals.model;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by Lo.Gas_2 on 21/12/2016.
 */
@Entity
public class Comment {

    @Id
    @GeneratedValue
    private Long id;

    @Column(nullable = false)
    private String userEmail;

    @ManyToOne
    @JoinColumn(name = "product.id")
    private Product product;

    @Column(nullable = false)
    private Date dateTime;

    @Column(nullable = false)
    private String comment;

    public Comment() {
    }

    public Comment(String userEmail, Product product, Date dateTime, String comment) {
        this.userEmail = userEmail;
        this.product = product;
        this.dateTime = dateTime;
        this.comment = comment;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Date getDateTime() {
        return dateTime;
    }

    public void setDateTime(Date dateTime) {
        this.dateTime = dateTime;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

}