package se.lnu.agile.mymanuals.converter;

import org.springframework.beans.BeanUtils;
import org.springframework.cglib.core.internal.Function;
import org.springframework.stereotype.Component;
import se.lnu.agile.mymanuals.dto.consumer.ConsumerInfoDto;
import se.lnu.agile.mymanuals.model.Consumer;

/**
 * Created by ilyakruikov on 11/26/16.
 */
@Component
public class ConsumerToConsumerInfoDto implements Function<Consumer, ConsumerInfoDto> {

    @Override
    public ConsumerInfoDto apply(Consumer consumer) {
        ConsumerInfoDto consumerInfoDto = new ConsumerInfoDto();
        BeanUtils.copyProperties(consumer, consumerInfoDto);
        return consumerInfoDto;
    }

}