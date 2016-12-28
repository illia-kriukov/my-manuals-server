package se.lnu.agile.mymanuals.dto.product;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.Pattern;
import java.util.List;

/**
 * Created by ilyakruikov on 11/20/16.
 */
public class ProductCreateDto {

    @NotBlank(message="Name should not be empty")
    @Pattern(regexp = "^[A-Z]([a-zA-Z0-9]|[- @\\.#&!])*$", message = "Incorrect product name format.")
    private String name;

    @NotBlank(message="Model should not be empty")
    @Pattern(regexp = "^[A-Z]([a-zA-Z0-9]|[- @\\.#&!])*$", message = "Incorrect product model format.")
    private String model;

    @NotEmpty(message="Product should be at least in one category")
    private List<Long> category;

    private List<String> video;

    @NotEmpty(message="At least one manual should be uploaded")
    private List<MultipartFile> file;

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

}