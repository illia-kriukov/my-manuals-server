package se.lnu.agile.mymanuals.model;

import javax.persistence.*;

/**
 * Created by Lo.Gas_2 on 23/11/2016.
 */

@Entity
public class Consumer {
    @Id
    @GeneratedValue
    private Long id;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String name;


    public Consumer(String email, String password, String name) {
        this.email = email;
        this.password = password;
        this.name = name;
    }

    public Consumer(Consumer consumer) {
        super();
        this.id = consumer.getId();
        this.email = consumer.getEmail();
        this.password = consumer.getPassword();
        this.name = consumer.getName();
    }

    public Consumer() {
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
}
