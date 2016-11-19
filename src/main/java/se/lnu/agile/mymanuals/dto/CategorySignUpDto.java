package se.lnu.agile.mymanuals.dto;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

/**
 * Created by Daniel on 16.11.2016.
 */
public class CategorySignUpDto {

    @NotNull(message = "Name should not be empty.")
    @Pattern(regexp = "^[A-Z]([a-zA-Z0-9]|[- ])*$",
            message = "Incorrect category name format.")
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}