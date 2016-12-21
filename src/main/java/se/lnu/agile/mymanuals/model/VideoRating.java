package se.lnu.agile.mymanuals.model;

import javax.persistence.*;

/**
 * Created by Daniel on 21.12.2016.
 */
@Entity
public class VideoRating {

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
    private int rating;

    public VideoRating(){
    }

    public VideoRating(Video video, Consumer consumer, int rating) {
        this.video = video;
        this.consumer = consumer;
        this.rating = rating;
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

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }
}