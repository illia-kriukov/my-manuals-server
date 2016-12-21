package se.lnu.agile.mymanuals.model;

import javax.persistence.*;

/**
 * Created by Lo.Gas_2 on 21/12/2016.
 */
@Entity
public class Comment {
    @Id
    @GeneratedValue
    private Long id;

    private String consumerEmail;

    @ManyToOne
    @JoinColumn(name = "product.id")
    private Product product;

    @Column(nullable = false)
    private String comment;

    public Comment() {
    }

    public Comment(String consumerEmail, Product product, String comment) {
        this.consumerEmail = consumerEmail;
        this.product = product;
        this.comment = comment;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getConsumerEmail() {
        return consumerEmail;
    }

    public void setConsumerEmail(String consumerEmail) {
        this.consumerEmail = consumerEmail;
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
