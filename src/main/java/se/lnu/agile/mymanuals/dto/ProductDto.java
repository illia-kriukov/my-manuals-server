package se.lnu.agile.mymanuals.dto;

/**
 * Created by ToMeg on 2016-11-18.
 */
public class ProductDto {

    private String name;
    private String number;

    public ProductDto(String name, String number) {
        this.name = name;
        this.number = number;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }



    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }
}
