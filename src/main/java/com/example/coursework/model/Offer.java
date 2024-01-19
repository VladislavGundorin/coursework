package com.example.coursework.model;

import com.example.coursework.enums.Engine;
import com.example.coursework.enums.Transmission;
import jakarta.persistence.*;

import java.time.LocalDateTime;


@Entity
@Table(name = "offers")
public class Offer extends BaseEntity implements ImageUrlProvider{
    @Column(name = "description", columnDefinition = "TEXT")
    private String description;

    @Enumerated(EnumType.ORDINAL)
    @Column(name = "engine")
    private Engine engine;

    @Column(name = "image_url")
    private String imageUrl;

    @Column(name = "mileage")
    private Integer mileage;

    @Column(name = "price")
    private int price;

    @Enumerated(EnumType.ORDINAL)
    @Column(name = "transmission")
    private Transmission transmission;

    @Column(name = "year")
    private int year;

    private int viewCount;

    @ManyToOne
    @JoinColumn(name = "seller_id", referencedColumnName = "id")
    private User seller;
    @ManyToOne
    @JoinColumn(name = "model_id", referencedColumnName = "id")
    private Model model;

    public Offer() {

    }

    public Offer(String description, Engine engine, String imageUrl, Integer mileage, int price, Transmission transmission, int year,User seller, Model model, int viewCount) {
        this.description = description;
        this.engine = engine;
        this.imageUrl = imageUrl;
        this.mileage = mileage;
        this.price = price;
        this.transmission = transmission;
        this.year = year;
        this.seller = seller;
        this.model = model;
        this.viewCount = viewCount;
    }

    public int getViewCount() {
        return viewCount;
    }

    public void setViewCount(int viewCount) {
        this.viewCount = viewCount;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Engine getEngine() {
        return engine;
    }

    public void setEngine(Engine engine) {
        this.engine = engine;
    }

    public Integer getMileage() {
        return mileage;
    }

    public void setMileage(Integer mileage) {
        this.mileage = mileage;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public Transmission getTransmission() {
        return transmission;
    }

    public void setTransmission(Transmission transmission) {
        this.transmission = transmission;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public User getSeller() {
        return seller;
    }

    public void setSeller(User seller) {
        this.seller = seller;
    }

    public Model getModel() {
        return model;
    }

    public void setModel(Model model) {
        this.model = model;
    }

    @Override
    public String toString() {
        return "Offer{" +
                "description='" + description + '\'' +
                ", engine=" + engine +
                ", imageUrl='" + imageUrl + '\'' +
                ", mileage=" + mileage +
                ", price=" + price +
                ", transmission=" + transmission +
                ", year=" + year +
                ", viewCount=" + viewCount +
                ", seller=" + seller +
                ", model=" + model +
                ", id=" + id +
                "} " + super.toString();
    }

    @Override
    public String getImageUrl() {
        return this.imageUrl;
    }

    @Override
    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}


