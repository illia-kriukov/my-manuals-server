package se.lnu.agile.mymanuals.dto.annotation;

import se.lnu.agile.mymanuals.dto.consumer.ConsumerDto;

/**
 * Created by Daniel on 16.12.2016.
 */
public class VideoAnnotationDto {

    private Long id;

    private String annotation;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAnnotation() {
        return annotation;
    }

    public void setAnnotation(String annotation) {
        this.annotation = annotation;
    }
}