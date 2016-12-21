package se.lnu.agile.mymanuals.model;

import javax.persistence.*;

/**
 * Created by Lo.Gas_2 on 21/12/2016.
 */
@Entity
public class ProductComment {

    @Id
    @GeneratedValue
    private Long id;

    @Column(nullable = false)
    private String userEmail;

    @ManyToOne
    @JoinColumn(name = "product.id")
    private Product product;

    @Column(nullable = false)
    private String comment;

    public ProductComment() {
    }

    public ProductComment(String userEmail, Product product, String comment) {
        this.userEmail = userEmail;
        this.product = product;
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

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

}