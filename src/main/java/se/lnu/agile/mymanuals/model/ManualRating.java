package se.lnu.agile.mymanuals.model;

import javax.persistence.*;

/**
 * Created by Daniel on 21.12.2016.
 */
@Entity
public class ManualRating {

    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne
    @JoinColumn(name = "manual.id")
    private Manual manual;

    @ManyToOne
    @JoinColumn(name = "consumer.id")
    private Consumer consumer;

    @Column(nullable = false)
    private int rating;

    public ManualRating(){
    }

    public ManualRating(Manual manual, Consumer consumer, int rating) {
        this.manual = manual;
        this.consumer = consumer;
        this.rating = rating;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Manual getManual() {
        return manual;
    }

    public void setManual(Manual manual) {
        this.manual = manual;
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