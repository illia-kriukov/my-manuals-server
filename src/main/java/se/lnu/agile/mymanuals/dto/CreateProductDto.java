package se.lnu.agile.mymanuals.dto;

import java.util.List;

/**
 * Created by ilyakruikov on 11/20/16.
 */
public class CreateProductDto {

    private String name;

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