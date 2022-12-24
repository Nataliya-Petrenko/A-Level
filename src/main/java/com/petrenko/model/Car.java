package com.petrenko.model;

import java.util.Objects;
import java.util.Random;
import java.util.UUID;
import com.petrenko.model.Engine;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
public abstract class Car implements CountRestore {
    private String manufacturer;
    private Engine engine;
    private Color color;
    private int count;
    private int price;
    private Type type;

    @Setter(AccessLevel.NONE)
    private String uuidOfCar;

    public Car() {
        this.uuidOfCar = UUID.randomUUID().toString();
    };

    private final Random random = new Random();
    public Car(String manufacturer, Engine engine, Color color) {
        this.manufacturer = manufacturer;
        this.engine = engine;
        this.color = color;
        this.count = 1;
        this.price = Math.abs(random.nextInt());
        this.uuidOfCar = UUID.randomUUID().toString();
        this.type = type;
    }

    @Override
    public String toString() {
        return "UUID: " + uuidOfCar;
    }

    @Override
    public int hashCode() {
        return Objects.hash(manufacturer, engine, color, count, price, type, uuidOfCar);
    }
}
