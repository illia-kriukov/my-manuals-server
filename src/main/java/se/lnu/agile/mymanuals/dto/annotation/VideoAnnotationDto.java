package se.lnu.agile.mymanuals.dto.annotation;

import se.lnu.agile.mymanuals.dto.consumer.ConsumerDto;

/**
 * Created by Daniel on 16.12.2016.
 */
public class VideoAnnotationDto {

    private Long id;

    private String videoLink;

    private ConsumerDto consumer;

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

    public String getVideoLink() {
        return videoLink;
    }

    public void setVideoLink(String videoLink) {
        this.videoLink = videoLink;
    }

    public ConsumerDto getConsumer() {
        return consumer;
    }

    public void setConsumer(ConsumerDto consumer) {
        this.consumer = consumer;
    }
}