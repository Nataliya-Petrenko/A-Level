package com.petrenko.builder;

import com.petrenko.model.*;

import java.util.LinkedHashSet;
import java.util.Set;

public class TruckBuilder {
    private String manufacturer;
    private Engine engine;
    private Color color;
    private int count;
    private int price;
    private Type type;
    private String uuidOfCar;
    private Set<Order> orders = new LinkedHashSet<>();
    private int loadCapacity;

    public TruckBuilder withManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
        return this;
    }

    public TruckBuilder withEngine(Engine engine) {
        this.engine = engine;
        return this;
    }

    public TruckBuilder withColor(Color color) {
        this.color = color;
        return this;
    }

    public TruckBuilder withCount(int count) {
        this.count = count;
        return this;
    }

    public TruckBuilder withPrice(int price) {
        if (count > 1000) {
            throw new IllegalArgumentException("Price more than 1000");
        }
        this.price = price;
        return this;
    }

    public TruckBuilder withType(Type type) {
        this.type = type;
        return this;
    }

    public TruckBuilder withUuidOfCar(String uuidOfCar) {
        this.uuidOfCar = uuidOfCar;
        return this;
    }

    public TruckBuilder withOrders(Set<Order> orders) {
        this.orders = orders;
        return this;
    }

    public TruckBuilder withLoadCapacity(int loadCapacity) {
        this.loadCapacity = loadCapacity;
        return this;
    }

    public Truck build() {
        if (count <= 0) {
            throw new IllegalArgumentException("Count must be more than 0");
        }
        return new Truck(manufacturer, engine, color, count, price, type, uuidOfCar, orders, loadCapacity);
    }
}
