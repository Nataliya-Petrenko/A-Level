package com.petrenko.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Table;

@Getter
@Setter
@Entity
@Table(name = "passenger_car")
@DiscriminatorValue("passenger_car")
public class PassengerCar extends Car {

    private int passengerCount;

    public PassengerCar() {

    };

//    public PassengerCar() {
//        this.setType(Type.CAR);
//    }
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
