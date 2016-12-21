package se.lnu.agile.mymanuals.dao;

import org.springframework.data.repository.CrudRepository;
import se.lnu.agile.mymanuals.model.ManualRating;

import java.util.List;

/**
 * Created by Daniel on 21.12.2016.
 */
public interface ManualRatingDao extends CrudRepository<ManualRating, Long> {

    ManualRating findByManual_idAndConsumer_id(Long manual_id, Long consumer_id);

}