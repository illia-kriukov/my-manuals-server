package se.lnu.agile.mymanuals.model;

import javax.persistence.*;

/**
 * Created by Daniel on 16.12.2016.
 */
@Entity
public class VideoAnnotation {

    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne
    @JoinColumn(name = "video.id")
    private Video video;

    @ManyToOne
    @JoinColumn(name = "consumer.id")
    private Consumer consumer;

    @Column(nullable = false)
    private String annotation;

    public VideoAnnotation(){

    }

    public VideoAnnotation(Video video, Consumer consumer, String annotation) {
        this.video = video;
        this.consumer = consumer;
        this.annotation = annotation;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Video getVideo() {
        return video;
    }

    public void setVideo(Video video) {
        this.video = video;
    }

    public Consumer getConsumer() {
        return consumer;
    }

    public void setConsumer(Consumer consumer) {
        this.consumer = consumer;
    }

    public String getAnnotation() {
        return annotation;
    }

    public void setAnnotation(String annotation) {
        this.annotation = annotation;
    }
}
