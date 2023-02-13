package com.petrenko.model;

import java.util.LinkedHashSet;
import java.util.Objects;
import java.util.Random;
import java.util.Set;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Car implements CountRestore {
    private String manufacturer;
    @OneToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "engine_id")
    private Engine engine;

    @Enumerated(EnumType.STRING)
    private Color color;
    private int count;
    private int price;

    @Enumerated(EnumType.STRING)
    private Type type;

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name = "car_id")
    private String uuidOfCar;

    @ManyToMany(mappedBy = "cars")
    private Set<Order> orders = new LinkedHashSet<>();

    @Transient
    private final Random random = new Random();

    public Car() {
    };

//    public Car() {
//        this.uuidOfCar = UUID.randomUUID().toString();
//    };

    public Car(String manufacturer, Engine engine, Color color) {
        this.manufacturer = manufacturer;
        this.engine = engine;
        this.color = color;
        this.count = 1;
        this.price = Math.abs(random.nextInt());
//        this.uuidOfCar = UUID.randomUUID().toString();
//        this.type = type;
    }

    @Override
    public String toString() {
        return "Car{" +
                ", uuidOfCar='" + uuidOfCar +
                "manufacturer='" + manufacturer + '\'' +
                ", engine=" + engine +
                ", color=" + color +
                ", count=" + count +
                ", price=" + price +
                ", type=" + type + '\'' +
                '}';
    }

    @Override
    public int hashCode() {
        return Objects.hash(manufacturer, engine, color, count, price, type, uuidOfCar);
    }
}
