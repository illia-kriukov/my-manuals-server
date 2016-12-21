package se.lnu.agile.mymanuals.converter;

import org.springframework.beans.BeanUtils;
import org.springframework.cglib.core.internal.Function;
import org.springframework.stereotype.Component;
import se.lnu.agile.mymanuals.dto.annotation.VideoAnnotationDto;
import se.lnu.agile.mymanuals.dto.rating.RatingDto;
import se.lnu.agile.mymanuals.model.VideoAnnotation;
import se.lnu.agile.mymanuals.model.VideoRating;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by Daniel on 21.12.2016.
 */
@Component
public class VideoRatingToRatingDto
        implements Function<VideoRating, RatingDto> {

    @Override
    public RatingDto apply(VideoRating videoRating) {
        RatingDto ratingDto = new RatingDto();
        BeanUtils.copyProperties(videoRating, ratingDto);
        return ratingDto;
    }
}