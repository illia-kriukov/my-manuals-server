package se.lnu.agile.mymanuals.converter;

import org.springframework.beans.BeanUtils;
import org.springframework.cglib.core.internal.Function;
import org.springframework.stereotype.Component;
import se.lnu.agile.mymanuals.dto.rating.RatingDto;
import se.lnu.agile.mymanuals.model.ManualRating;
import se.lnu.agile.mymanuals.model.VideoRating;

/**
 * Created by Daniel on 21.12.2016.
 */
@Component
public class ManualRatingToRatingDto
        implements Function<ManualRating, RatingDto> {

    @Override
    public RatingDto apply(ManualRating manualRating) {
        RatingDto ratingDto = new RatingDto();
        BeanUtils.copyProperties(manualRating, ratingDto);
        return ratingDto;
    }
}