package se.lnu.agile.mymanuals.dto.manual;

/**
 * Created by Lo.Gas_2 on 7/12/2016.
 */
public class ManualDto {

    private Long id;

    private String name;

    public ManualDto(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public ManualDto() {
    }

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

}