package se.lnu.agile.mymanuals.converter;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;
import se.lnu.agile.mymanuals.dto.rating.AvgRatingDto;
import se.lnu.agile.mymanuals.model.AvgRating;

import java.util.function.Function;

/**
 * Created by Daniel on 23.12.2016.
 */
@Component
public class AvgRatingToAvgRatingDto implements Function<AvgRating, AvgRatingDto> {

    @Override
    public AvgRatingDto apply(AvgRating avgRating) {
        AvgRatingDto avgRatingDto = new AvgRatingDto();
        BeanUtils.copyProperties(avgRating, avgRatingDto);
        return avgRatingDto;
    }
}
