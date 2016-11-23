package se.lnu.agile.mymanuals.dto;

import java.util.List;

/**
 * Created by ToMeg on 2016-11-23.
 */
public class ProductListDto {

    private Long id;

    private String name;

    private String model;

    private List<CategoryDto> categories;

    private CompanyDto company;

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

    public CompanyDto getCompany() {
        return company;
    }

    public void setCompany(CompanyDto company) {
        this.company = company;
    }

}