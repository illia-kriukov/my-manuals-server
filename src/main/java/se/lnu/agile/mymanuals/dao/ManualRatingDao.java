package se.lnu.agile.mymanuals.dao;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import se.lnu.agile.mymanuals.model.AvgRating;
import se.lnu.agile.mymanuals.model.ManualRating;

import java.util.HashMap;
import java.util.List;

/**
 * Created by Daniel on 21.12.2016.
 */
public interface ManualRatingDao extends CrudRepository<ManualRating, Long> {

//    List<ManualRating> findByManual_id(Long manual_id);

    ManualRating findByManual_idAndConsumer_id(Long manual_id, Long consumer_id);

    @Query("SELECT new se.lnu.agile.mymanuals.model.AvgRating( " +
            "AVG(rating) AS avgRating, COUNT(rating) AS ratingCount) " +
            "FROM ManualRating " +
            "WHERE manual.id = :manualId")
    AvgRating getAvgRatingAndRatingCount(@Param("manualId") long manualId);

}