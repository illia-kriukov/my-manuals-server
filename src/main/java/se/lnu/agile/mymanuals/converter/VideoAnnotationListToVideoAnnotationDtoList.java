package se.lnu.agile.mymanuals.converter;

import org.springframework.beans.BeanUtils;
import org.springframework.cglib.core.internal.Function;
import org.springframework.stereotype.Component;
import se.lnu.agile.mymanuals.dto.annotation.VideoAnnotationDto;
import se.lnu.agile.mymanuals.model.VideoAnnotation;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by Daniel on 16.12.2016.
 *
 */
@Component
public class VideoAnnotationListToVideoAnnotationDtoList
        implements Function<List<VideoAnnotation>, List<VideoAnnotationDto>> {

    @Override
    public List<VideoAnnotationDto> apply(List<VideoAnnotation> videoAnnotations) {

        List<VideoAnnotationDto> videoAnnotationDtoList = new LinkedList<>();

        for (VideoAnnotation videoAnnotation : videoAnnotations){
            VideoAnnotationDto videoAnnotationDto = new VideoAnnotationDto();
            BeanUtils.copyProperties(videoAnnotation, videoAnnotationDto);
            videoAnnotationDtoList.add(videoAnnotationDto);
        }

        return videoAnnotationDtoList;
    }
}