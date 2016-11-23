package se.lnu.agile.mymanuals.model;

import javax.persistence.*;
import java.util.List;

/**
 * Created by ilyakruikov on 11/20/16.
 */
@Entity
public class Product {

    @Id
    @GeneratedValue
    private Long id;

    @Column(nullable = false, unique = true)
    private String name;

    @Column(nullable = false)
    private String model;

    @ManyToMany
    private List<Category> category;

    @ManyToOne
    @JoinColumn(name = "company.id")
    private Company company;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "product")
    private List<Manual> manual;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "product")
    private List<Video> video;

    public Product(String name, String model, List<Category> category, Company company, List<Manual> manual, List<Video> video) {
        this.name = name;
        this.model = model;
        this.category = category;
        this.company = company;
        this.manual = manual;
        this.video = video;
    }

    public Product() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public List<Category> getCategory() {
        return category;
    }

    public void setCategory(List<Category> category) {
        this.category = category;
    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    public List<Manual> getManual() {
        return manual;
    }

    public void setManual(List<Manual> manual) {
        this.manual = manual;
    }

    public List<Video> getVideo() {
        return video;
    }

    public void setVideo(List<Video> video) {
        this.video = video;
    }

}