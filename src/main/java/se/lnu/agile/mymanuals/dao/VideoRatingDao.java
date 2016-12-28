package se.lnu.agile.mymanuals.dao;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import se.lnu.agile.mymanuals.model.AvgRating;
import se.lnu.agile.mymanuals.model.VideoRating;

/**
 * Created by Daniel on 21.12.2016.
 */
public interface VideoRatingDao extends CrudRepository<VideoRating, Long> {

    VideoRating findByVideo_idAndConsumer_id(Long video_id, Long consumer_id);

    @Query("SELECT new se.lnu.agile.mymanuals.model.AvgRating( " +
            "AVG(rating) AS avgRating, COUNT(rating) AS ratingCount) " +
            "FROM VideoRating " +
            "WHERE video.id = :videoId")
    AvgRating getAvgRatingAndRatingCount(@Param("videoId") long videoId);

}