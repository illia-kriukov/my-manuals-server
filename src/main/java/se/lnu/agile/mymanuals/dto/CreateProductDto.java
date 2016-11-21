package se.lnu.agile.mymanuals.dto;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.util.List;

/**
 * Created by ilyakruikov on 11/20/16.
 */
public class CreateProductDto {

    @NotNull(message="Name should not be empty")
    @Pattern(regexp = "^[\\d \\w \\s]+$")
    private String name;

    @NotNull(message="Model should not be empty")
    @Pattern(regexp = "^[\\d \\w \\s]+$")
    private String model;

    private List<Long> category;

    private List<String> video;

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

    public List<Long> getCategory() {
        return category;
    }

    public void setCategory(List<Long> category) {
        this.category = category;
    }

    public List<String> getVideo() {
        return video;
    }

    public void setVideo(List<String> video) {
        this.video = video;
    }

}