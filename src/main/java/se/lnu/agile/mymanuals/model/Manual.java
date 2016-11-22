package se.lnu.agile.mymanuals.model;

import javax.persistence.*;

/**
 * Created by ilyakruikov on 11/21/16.
 */
@Entity
public class Manual {

    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne(cascade = CascadeType.ALL)
    private Product product;

    @Column(nullable = false, unique = true)
    private String name;

    @Column(nullable = false)
    private byte[] file;

    public Manual(String name, byte[] file) {
        this.name = name;
        this.file = file;
    }

    public Manual() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public byte[] getFile() {
        return file;
    }

    public void setFile(byte[] file) {
        this.file = file;
    }

}