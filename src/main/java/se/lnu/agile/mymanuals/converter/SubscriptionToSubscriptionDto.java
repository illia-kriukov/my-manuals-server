package se.lnu.agile.mymanuals.converter;

import org.springframework.beans.BeanUtils;
import org.springframework.cglib.core.internal.Function;
import org.springframework.stereotype.Component;
import se.lnu.agile.mymanuals.dto.subscription.SubscriptionDto;
import se.lnu.agile.mymanuals.model.Subscription;

/**
 * Created by ToMeg on 2016-12-15.
 */

@Component
public class SubscriptionToSubscriptionDto implements Function<Subscription,SubscriptionDto> {

    @Override
    public SubscriptionDto apply(Subscription subscription) {
        SubscriptionDto subscriptionDto = new SubscriptionDto();
        BeanUtils.copyProperties(subscription, subscriptionDto);
        return subscriptionDto;
    }
}
