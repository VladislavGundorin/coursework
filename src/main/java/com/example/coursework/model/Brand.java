package com.example.coursework.model;

import jakarta.persistence.*;
import org.hibernate.annotations.Cascade;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "brands")
public class Brand extends BaseEntity {
    @Column(name="name")
    private String name;

    private LocalDateTime created;

    private LocalDateTime modified;

    @Cascade(org.hibernate.annotations.CascadeType.ALL)
    @OneToMany(mappedBy = "brand",fetch = FetchType.LAZY)
    List<Model> models;

    public Brand(String name, LocalDateTime created, LocalDateTime modified) {
        this.name = name;
    }
    public Brand(){

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Model> getModels() {
        return models;
    }

    public void setModels(List<Model> models) {
        this.models = models;
    }

    @Override
    public String toString() {
        return "Brand{" +
                "name='" + name + '\'' +
                ", id=" + id +
                "} " + super.toString();
    }
}
