package se.lnu.agile.mymanuals.dao;

import se.lnu.agile.mymanuals.model.Consumer;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by Lo.Gas_2 on 23/11/2016.
 */
public interface ConsumerDao extends CrudRepository<Consumer, Long> {
    Consumer findByEmail(String email);
}
