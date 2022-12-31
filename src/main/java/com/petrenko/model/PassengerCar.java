package com.petrenko.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PassengerCar extends Car {
    private int passengerCount;

    public PassengerCar() {
        this.setType(Type.CAR);
    }
    public PassengerCar(String manufacturer, Engine engine, Color color){
        super(manufacturer, engine, color);
        this.passengerCount = passengerCount;
        this.setType(Type.CAR);
    }

    @Override
    public void restore() {
        this.setCount(100);
//        System.out.println("Count: " + this.getCount());

    }
}
