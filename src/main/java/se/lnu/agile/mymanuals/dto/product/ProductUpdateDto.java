package se.lnu.agile.mymanuals.dto.product;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.util.List;

/**
 * Created by ToMeg on 2016-12-22.
 */
public class ProductUpdateDto {

    @NotNull(message = "Id should not be empty")
    private Long id;

    @NotBlank(message = "Name should not be empty")
    @Pattern(regexp = "^[A-Z]([a-zA-Z0-9]|[- @\\.#&!])*$", message = "Incorrect product name format.")
    private String name;

    @NotBlank(message = "Model should not be empty")
    @Pattern(regexp = "^[A-Z]([a-zA-Z0-9]|[- @\\.#&!])*$", message = "Incorrect product model format.")
    private String model;

    @NotEmpty(message = "Product should be at least in one category")
    private List<Long> category;

    private List<String> video;

    private List<MultipartFile> file;

    private List<Long> removedVideos;

    private List<Long> removedManuals;

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

    public List<MultipartFile> getFile() {
        return file;
    }

    public void setFile(List<MultipartFile> file) {
        this.file = file;
    }

    public List<Long> getRemovedVideos() {
        return removedVideos;
    }

    public void setRemovedVideos(List<Long> removedVideos) {
        this.removedVideos = removedVideos;
    }

    public List<Long> getRemovedManuals() {
        return removedManuals;
    }

    public void setRemovedManuals(List<Long> removedManuals) {
        this.removedManuals = removedManuals;
    }

}