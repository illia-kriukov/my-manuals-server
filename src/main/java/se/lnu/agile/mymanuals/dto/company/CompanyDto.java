package se.lnu.agile.mymanuals.dto.company;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * Created by ilyakruikov on 11/11/16.
 */
public class CompanyDto {

    private Long id;

    @JsonIgnore
    private String email;

    @JsonIgnore
    private String password;

    private String name;

    @JsonIgnore
    private String description;

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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

}