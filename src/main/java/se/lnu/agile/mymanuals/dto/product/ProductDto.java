package se.lnu.agile.mymanuals.dto.product;

import se.lnu.agile.mymanuals.dto.category.CategoryDto;
import se.lnu.agile.mymanuals.dto.company.CompanyInfoDto;
import se.lnu.agile.mymanuals.dto.manual.ManualDto;

import java.util.List;

/**
 * Created by ToMeg on 2016-11-18.
 */
public class ProductDto {

    private Long id;

    private String name;

    private String model;

    private List<CategoryDto> categories;

    private CompanyInfoDto company;

    private List<String> videos;

    private List<ManualDto> manuals;

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

    public List<CategoryDto> getCategories() {
        return categories;
    }

    public void setCategories(List<CategoryDto> categories) {
        this.categories = categories;
    }

    public CompanyInfoDto getCompany() {
        return company;
    }

    public void setCompany(CompanyInfoDto company) {
        this.company = company;
    }

    public List<String> getVideos() {
        return videos;
    }

    public void setVideos(List<String> videos) {
        this.videos = videos;
    }

    public List<ManualDto> getManuals() {
        return manuals;
    }

    public void setManuals(List<ManualDto> manuals) {
        this.manuals = manuals;
    }

}