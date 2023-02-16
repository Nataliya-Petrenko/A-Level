package com.petrenko.model;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@Entity
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    private String id;

    @ManyToMany(cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
    @JoinTable(name = "Order_Car",
        joinColumns = @JoinColumn(name = "order_id"),
        inverseJoinColumns = @JoinColumn(name = "car_id"))
    private List<Car> cars;
    private LocalDateTime time;

    public Order() {

    }

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
