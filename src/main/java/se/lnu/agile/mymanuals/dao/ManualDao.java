package se.lnu.agile.mymanuals.dao;

import org.springframework.data.repository.CrudRepository;
import se.lnu.agile.mymanuals.model.Manual;

/**
 * Created by ilyakruikov on 12/7/16.
 */
public interface ManualDao extends CrudRepository<Manual, Long> {
}