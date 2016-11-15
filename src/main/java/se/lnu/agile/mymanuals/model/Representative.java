package se.lnu.agile.mymanuals.model;

import javax.persistence.*;

/**
 * Created by ilyakruikov on 11/10/16.
 */
@Entity
public class Representative {

    @Id
    @GeneratedValue
    private Long id;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String name;

    @ManyToOne
    private Company company;

    public Representative(String email, String password, String name, Company company) {
        this.email = email;
        this.password = password;
        this.name = name;
        this.company = company;
    }

    public Representative(Representative representative) {
        super();
        this.id = representative.getId();
        this.email = representative.getEmail();
        this.password = representative.getPassword();
        this.name = representative.getName();
        this.company = representative.getCompany();
    }

    public Representative() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

}