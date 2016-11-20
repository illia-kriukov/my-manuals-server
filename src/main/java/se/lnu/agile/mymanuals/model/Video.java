package se.lnu.agile.mymanuals.model;

import javax.persistence.*;

/**
 * Created by ilyakruikov on 11/20/16.
 */
@Entity
public class Video {

    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne(cascade = CascadeType.ALL)
    private Product product;

    @Column(nullable = false)
    private String link;

    public Video(String link) {
        this.link = link;
    }

    public Video() {
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

}