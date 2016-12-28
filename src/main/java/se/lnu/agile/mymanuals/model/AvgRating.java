package se.lnu.agile.mymanuals.model;

/**
 * Created by Daniel on 22.12.2016.
 */
public class AvgRating {

    private double avgRating;

    private long ratingCount;

    public AvgRating() {
    }

    public AvgRating(Double avgRating, Long ratingCount) {
        if (avgRating == null || ratingCount == null) {
            return;
        }
        this.avgRating = avgRating;
        this.ratingCount = ratingCount;
    }

    public double getAvgRating() {
        return avgRating;
    }

    public void setAvgRating(double avgRating) {
        this.avgRating = avgRating;
    }

    public long getRatingCount() {
        return ratingCount;
    }

    public void setRatingCount(long ratingCount) {
        this.ratingCount = ratingCount;
    }

}