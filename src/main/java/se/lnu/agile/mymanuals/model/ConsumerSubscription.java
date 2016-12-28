package se.lnu.agile.mymanuals.model;

import javax.persistence.*;

/**
 * Created by ilyakruikov on 12/15/16.
 */
@Entity
public class ConsumerSubscription {

    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne
    @JoinColumn(name = "consumer.id")
    private Consumer consumer;

    @ManyToOne
    @JoinColumn(name = "product.id")
    private Product product;

    @ManyToOne
    @JoinColumn(name = "subscription.id")
    private Subscription subscription;

    public ConsumerSubscription() {
    }

    public ConsumerSubscription(Consumer consumer, Product product, Subscription subscription) {
        this.consumer = consumer;
        this.product = product;
        this.subscription = subscription;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Consumer getConsumer() {
        return consumer;
    }

    public void setConsumer(Consumer consumer) {
        this.consumer = consumer;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Subscription getSubscription() {
        return subscription;
    }

    public void setSubscription(Subscription subscription) {
        this.subscription = subscription;
    }

}