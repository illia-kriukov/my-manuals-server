package se.lnu.agile.mymanuals.dao;

import org.springframework.data.repository.CrudRepository;
import se.lnu.agile.mymanuals.model.VideoRating;

/**
 * Created by Daniel on 21.12.2016.
 */
public interface VideoRatingDao extends CrudRepository<VideoRating, Long> {

    VideoRating findByVideo_idAndConsumer_id(Long video_id, Long consumer_id);

}