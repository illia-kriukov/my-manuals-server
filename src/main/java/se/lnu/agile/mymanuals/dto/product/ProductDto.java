package se.lnu.agile.mymanuals.dto.product;

/**
 * Created by ToMeg on 2016-11-18.
 */
public class ProductDto {

    private Long id;

    private String name;

    private String model;

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

}