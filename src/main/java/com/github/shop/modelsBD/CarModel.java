package com.github.shop.modelsBD;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
public class CarModel {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(length = 1000)
    private String description;
    private String name, color, fuel;
    private double mileage, engine, price;

    @Lob
    private byte[] picture;

    public CarModel(String name, String color, String description, String fuel, double mileage, double engine, double price, byte[] picture) {
        this.color = color;
        this.description = description;
        this.name = name;
        this.mileage = mileage;
        this.engine = engine;
        this.price = price;
        this.fuel = fuel;
        this.picture = picture;
    }

    public CarModel() {
    }
}
