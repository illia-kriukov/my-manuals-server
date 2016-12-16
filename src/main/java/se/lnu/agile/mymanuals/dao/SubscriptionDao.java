package se.lnu.agile.mymanuals.dao;

import org.springframework.data.repository.CrudRepository;
import se.lnu.agile.mymanuals.model.Subscription;

/**
 * Created by ilyakruikov on 12/15/16.
 */
public interface SubscriptionDao extends CrudRepository<Subscription, Long> {
}