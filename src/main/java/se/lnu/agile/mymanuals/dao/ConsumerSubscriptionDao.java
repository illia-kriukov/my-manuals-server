package se.lnu.agile.mymanuals.dao;

import org.springframework.data.repository.CrudRepository;
import se.lnu.agile.mymanuals.model.Consumer;
import se.lnu.agile.mymanuals.model.ConsumerSubscription;
import se.lnu.agile.mymanuals.model.Product;
import se.lnu.agile.mymanuals.model.Subscription;

import java.util.List;

/**
 * Created by ilyakruikov on 12/15/16.
 */
public interface ConsumerSubscriptionDao extends CrudRepository<ConsumerSubscription, Long> {

    ConsumerSubscription findByConsumerAndProductAndSubscription(Consumer consumer, Product product, Subscription subscription);

    List<ConsumerSubscription> findAllByConsumerAndProduct(Consumer consumer, Product product);

}