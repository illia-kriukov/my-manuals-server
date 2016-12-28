package se.lnu.agile.mymanuals.dao;

import org.springframework.data.repository.CrudRepository;
import se.lnu.agile.mymanuals.model.Manual;
import se.lnu.agile.mymanuals.model.ManualAnnotation;

import java.util.List;

/**
 * Created by Daniel on 16.12.2016.
 */
public interface ManualAnnotationDao extends CrudRepository<ManualAnnotation, Long> {

    List<ManualAnnotation> findByManual_idAndConsumer_id(Long manual_id, Long consumer_id);

}