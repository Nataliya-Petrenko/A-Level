package com.petrenko.builder;

import com.petrenko.model.*;

import java.util.LinkedHashSet;
import java.util.Set;

public class PassengerCarBuilder {
    private String manufacturer;
    private Engine engine;
    private Color color;
    private int count;
    private int price;
    private Type type;
    private String uuidOfCar;
    private Set<Order> orders = new LinkedHashSet<>();
    private int passengerCount;

    public PassengerCarBuilder withManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
        return this;
    }

    public PassengerCarBuilder withEngine(Engine engine) {
        this.engine = engine;
        return this;
    }

    public PassengerCarBuilder withColor(Color color) {
        this.color = color;
        return this;
    }

    public PassengerCarBuilder withCount(int count) {
        this.count = count;
        return this;
    }

    public PassengerCarBuilder withPrice(int price) {
        if (count > 1000) {
            throw new IllegalArgumentException("Price more than 1000");
        }
        this.price = price;
        return this;
    }

    public PassengerCarBuilder withType(Type type) {
        this.type = type;
        return this;
    }

    public PassengerCarBuilder withUuidOfCar(String uuidOfCar) {
        this.uuidOfCar = uuidOfCar;
        return this;
    }

    public PassengerCarBuilder withOrders(Set<Order> orders) {
        this.orders = orders;
        return this;
    }

    public PassengerCarBuilder withPassengerCount(int passengerCount) {
        this.passengerCount = passengerCount;
        return this;
    }

    public PassengerCar build() {
        if (count <= 0) {
            throw new IllegalArgumentException("Count must be more than 0");
        }
        return new PassengerCar(manufacturer, engine, color, count, price, type, uuidOfCar, orders, passengerCount);
    }
}
