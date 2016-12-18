package se.lnu.agile.mymanuals.model;

import javax.persistence.*;

/**
 * Created by Daniel on 16.12.2016.
 */
@Entity
public class ManualAnnotation {

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
    private String annotation;

    public ManualAnnotation(){
    }

    public ManualAnnotation(Manual manual, Consumer consumer, String annotation) {
        this.manual = manual;
        this.consumer = consumer;
        this.annotation = annotation;
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

    public String getAnnotation() {
        return annotation;
    }

    public void setAnnotation(String annotation) {
        this.annotation = annotation;
    }

}