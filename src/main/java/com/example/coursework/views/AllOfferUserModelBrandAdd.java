package com.example.coursework.views;

import com.example.coursework.enums.Engine;
import com.example.coursework.enums.Transmission;

public class AllOfferUserModelBrandAdd {
    private String id;
    private String imageUrl;
    private String brandName;
    private String modelName;
    private String firstName;
    private String lastName;
    private int price;
    private Engine engine;
    private int mileage;
    private int year;
    private Transmission transmission;
    private String username;

    public AllOfferUserModelBrandAdd(String id, String imageUrl, String brandName, String modelName, String firstName, String lastName, int price, Engine engine, int mileage, int year, Transmission transmission, String username) {
        this.id = id;
        this.imageUrl = imageUrl;
        this.brandName = brandName;
        this.modelName = modelName;
        this.firstName = firstName;
        this.lastName = lastName;
        this.price = price;
        this.engine = engine;
        this.mileage = mileage;
        this.year = year;
        this.transmission = transmission;
        this.username = username;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getBrandName() {
        return brandName;
    }

    public void setBrandName(String brandName) {
        this.brandName = brandName;
    }

    public String getModelName() {
        return modelName;
    }

    public void setModelName(String modelName) {
        this.modelName = modelName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public Engine getEngine() {
        return engine;
    }

    public void setEngine(Engine engine) {
        this.engine = engine;
    }

    public int getMileage() {
        return mileage;
    }

    public void setMileage(int mileage) {
        this.mileage = mileage;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public Transmission getTransmission() {
        return transmission;
    }

    public void setTransmission(Transmission transmission) {
        this.transmission = transmission;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public String toString() {
        return "AllOfferUserModelBrandAdd{" +
                "id='" + id + '\'' +
                ", imageUrl='" + imageUrl + '\'' +
                ", brandName='" + brandName + '\'' +
                ", modelName='" + modelName + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", price=" + price +
                ", engine=" + engine +
                ", mileage=" + mileage +
                ", year=" + year +
                ", transmission=" + transmission +
                ", username='" + username + '\'' +
                '}';
    }
}
