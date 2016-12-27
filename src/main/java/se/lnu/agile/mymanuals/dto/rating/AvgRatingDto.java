package se.lnu.agile.mymanuals.dto.rating;

/**
 * Created by Daniel on 22.12.2016.
 */
public class AvgRatingDto {

    private Double avgRating;

    private Long ratingCount;

    public Double getAvgRating() {
        return avgRating;
    }

    public void setAvgRating(Double avgRating) {
        this.avgRating = avgRating;
    }

    public Long getRatingCount() {
        return ratingCount;
    }

    public void setRatingCount(Long ratingCounter) {
        this.ratingCount = ratingCounter;
    }

}