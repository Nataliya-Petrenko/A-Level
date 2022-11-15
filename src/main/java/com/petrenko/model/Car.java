package com.petrenko.model;

import java.util.Random;
import java.util.UUID;

public class Car {
    private String manufacturer;
    private String engine;
    private Color color;
    private int count;
    private int price;
    private UUID uuidOfCar;

    public Car() {
    };

    Random random = new Random();
    public Car(String manufacturer, String engine, Color color) {
        this.manufacturer = manufacturer;
        this.engine = engine;
        this.color = color;
        this.count = 1;
        this.price = Math.abs(random.nextInt());
        this.uuidOfCar = UUID.randomUUID();
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public String getEngine() {
        return engine;
    }

    public void setEngine(String engine) {
        this.engine = engine;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
    public UUID getUuidOfCar () {
        return uuidOfCar;
    }
}
