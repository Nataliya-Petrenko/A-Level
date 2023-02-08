package com.petrenko.model;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
public class Order {
    private String id;
    private List<Car> cars;
    private LocalDateTime time;

    public Order(List<Car> cars) {
        this.cars = cars;
        this.id = UUID.randomUUID().toString();
        this.time = LocalDateTime.now();
    }

    @Override
    public String toString() {
        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("yyyy.MM.dd HH:mm");
        String carsString = cars.toString();
        return "Order{" +
                "id='" + id + '\'' +
                ", cars=" + carsString +
                ", time=" + timeFormatter.format(time) +
                '}';
    }
}
