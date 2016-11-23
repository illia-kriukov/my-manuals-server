package se.lnu.agile.mymanuals.dto;

import se.lnu.agile.mymanuals.model.Company;

import java.util.List;

/**
 * Created by ToMeg on 2016-11-23.
 */
public class ProductListDto {

    private String name;

    private String Model;

    private List<CategoryDto> categories;

    private String companyName;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getModel() {
        return Model;
    }

    public void setModel(String model) {
        Model = model;
    }

    public List<CategoryDto> getCategories() {
        return categories;
    }

    public void setCategories(List<CategoryDto> categories) {
        this.categories = categories;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }
}
