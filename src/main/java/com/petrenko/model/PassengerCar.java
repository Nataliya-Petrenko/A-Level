package com.petrenko.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "passenger_car")
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

    public PassengerCar(String manufacturer, Engine engine, Color color, int count, int price, Type type, String uuidOfCar, Set<Order> orders, int passengerCount) {
        super(manufacturer, engine, color, count, price, type, uuidOfCar, orders);
        this.passengerCount = passengerCount;
    }

    @Override
    public void restore() {
        this.setCount(100);
//        System.out.println("Count: " + this.getCount());

    }
}
