package com.petrenko.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import java.util.Set;

@Setter
@Getter
@Entity
public class Truck extends Car {

    private int loadCapacity;
    public Truck() {

    };

//    public Truck() {
//        this.setType(Type.TRUCK);
//    }
    public Truck(String manufacturer, Engine engine, Color color){
        super(manufacturer, engine, color);
        this.loadCapacity = loadCapacity;
        this.setType(Type.TRUCK);
    }

    public Truck(String manufacturer, Engine engine, Color color, int count, int price, Type type, String uuidOfCar, Set<Order> orders, int loadCapacity) {
        super(manufacturer, engine, color, count, price, type, uuidOfCar, orders);
        this.loadCapacity = loadCapacity;
    }

    @Override
    public void restore() {
        this.setCount(50);
//        System.out.println("Count: " + this.getCount());
    }
}
