package com.example.coursework.dto;


import java.util.UUID;

public class BrandDTO {
    private UUID id;
    private String name;

    public BrandDTO(UUID id, String name) {
        this.id = id;
        this.name = name;

    }
    public BrandDTO(){

    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "BrandDTO{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
