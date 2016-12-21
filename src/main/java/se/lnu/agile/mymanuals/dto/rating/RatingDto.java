package se.lnu.agile.mymanuals.dto.rating;

/**
 * Created by Daniel on 21.12.2016.
 */
public class RatingDto {

    private Long id;

    private int rating;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }
}