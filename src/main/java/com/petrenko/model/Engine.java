package com.petrenko.model;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Random;
import java.util.UUID;

@Getter
@Setter
@Entity
public class Engine {
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    private String id;
    @Transient
    final transient private Random random = new Random();
    private int power;

    @Enumerated(EnumType.STRING)
    private Type type;

//    public Engine() {
//        this.id = UUID.randomUUID().toString();
//    }

    public Engine() {

    };

    public Engine(Type type) {
        this.power = random.nextInt(1000);
        this.type = type;
//        this.id = UUID.randomUUID().toString();
    }

    @Override
    public String toString() {
        return "Engine{" +
                "id='" + id + '\'' +
                ", power=" + power +
                ", type=" + type +
                '}';
    }
}
