package se.lnu.agile.mymanuals.dto.consumer;

/**
 * Created by Lo.Gas_2 on 23/11/2016.
 */
public class ConsumerInfoDto {

    private String email;

    private String name;

    public ConsumerInfoDto(Long id, String email, String password, String name) {
        this.email = email;
        this.name = name;
    }

    public ConsumerInfoDto() {
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}