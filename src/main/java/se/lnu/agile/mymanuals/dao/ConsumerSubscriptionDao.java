package se.lnu.agile.mymanuals.dao;

import org.springframework.data.repository.CrudRepository;
import se.lnu.agile.mymanuals.model.ConsumerSubscription;

/**
 * Created by ilyakruikov on 12/15/16.
 */
interface ConsumerSubscriptionDao extends CrudRepository<ConsumerSubscription, Long> {
}