package com.carrental.model;

import jakarta.persistence.*;

@Entity
public class Car {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String type;
    private String imageFilename;
    private Double amountPerDay;
    private boolean available;

    public Car() {}
    public Car(String name, String type, String imageFilename, Double amountPerDay, boolean available) {
        this.name = name; this.type = type; this.imageFilename = imageFilename; this.amountPerDay = amountPerDay; this.available = available;
    }
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getType() { return type; }
    public void setType(String type) { this.type = type; }
    public String getImageFilename() { return imageFilename; }
    public void setImageFilename(String imageFilename) { this.imageFilename = imageFilename; }
    public Double getAmountPerDay() { return amountPerDay; }
    public void setAmountPerDay(Double amountPerDay) { this.amountPerDay = amountPerDay; }
    public boolean isAvailable() { return available; }
    public void setAvailable(boolean available) { this.available = available; }
}
