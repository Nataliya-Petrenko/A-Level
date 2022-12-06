package com.petrenko.model;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class Truck extends Car {
    private int loadCapacity;

    public Truck() {
        this.setType(Type.TRUCK);
    }
    public Truck(String manufacturer, Engine engine, Color color){
        super(manufacturer, engine, color);
        this.loadCapacity = loadCapacity;
        this.setType(Type.TRUCK);
    }

    @Override
    public void restore() {
        this.setCount(50);
        System.out.println("Count: " + this.getCount());
    }
}
