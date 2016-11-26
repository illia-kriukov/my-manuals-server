package se.lnu.agile.mymanuals.dto.consumer;

import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.Pattern;

/**
 * Created by Lo.Gas_2 on 23/11/2016.
 */
public class ConsumerSignUpDto {

    @NotBlank(message = "Email should not be empty.")
    @Pattern(regexp = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$",
            message = "Incorrect email format.")
    private String email;

    @NotBlank(message = "Password should not be empty.")
    @Pattern(regexp = "^[\\d\\w@#$%\\{\\}\\(\\)]{6,20}$", message = "Password should be at least 6 symbols.")
    private String password;

    @NotBlank(message = "Name should not be empty.")
    @Pattern(regexp = "^[a-zA-Z- ]*$", message = "Name can contain only letters and '-'.")
    private String name;

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