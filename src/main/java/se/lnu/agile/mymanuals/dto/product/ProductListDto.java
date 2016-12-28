package se.lnu.agile.mymanuals.dto.product;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import se.lnu.agile.mymanuals.dto.category.CategoryDto;
import se.lnu.agile.mymanuals.dto.company.CompanyInfoDto;

import java.util.List;

/**
 * Created by ToMeg on 2016-11-23.
 */
public class ProductListDto {

    private Long id;

    private String name;

    private String model;

    private List<CategoryDto> categories;

    private CompanyInfoDto company;

    private Boolean stored = false;

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

    public Boolean getStored() {
        return stored;
    }

    public void setStored(Boolean stored) {
        this.stored = stored;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (o == null || getClass() != o.getClass()) return false;

        ProductListDto that = (ProductListDto) o;

        return new EqualsBuilder()
                .append(id, that.id)
                .append(name, that.name)
                .append(model, that.model)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(id)
                .append(name)
                .append(model)
                .toHashCode();
    }

}