package se.lnu.agile.mymanuals.dao;

import org.springframework.data.repository.CrudRepository;
import se.lnu.agile.mymanuals.model.Manual;
import se.lnu.agile.mymanuals.model.ManualAnnotation;
import se.lnu.agile.mymanuals.model.VideoAnnotation;

import java.util.List;

/**
 * Created by Daniel on 16.12.2016.
 */
public interface VideoAnnotationDao extends CrudRepository<VideoAnnotation, Long> {

    List<VideoAnnotation> findByVideo_idAndConsumer_id(Long video_id, Long consumer_id);

}