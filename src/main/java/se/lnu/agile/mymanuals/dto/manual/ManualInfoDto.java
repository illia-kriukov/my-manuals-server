package se.lnu.agile.mymanuals.dto.manual;

/**
 * Created by ilyakruikov on 12/8/16.
 */
public class ManualInfoDto {

    private Long id;

    private String name;

    public ManualInfoDto(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public ManualInfoDto() {
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