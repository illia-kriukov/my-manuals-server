package se.lnu.agile.mymanuals.dto.subscription;

/**
 * Created by ToMeg on 2016-12-15.
 */
public class SubscriptionDto {

    private Long id;

    private String name;

    public String getName() {
        return name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }
}
