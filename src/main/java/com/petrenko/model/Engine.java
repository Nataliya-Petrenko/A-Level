package com.petrenko.model;

import lombok.Getter;
import lombok.Setter;

import java.util.Random;
import java.util.UUID;

@Getter
@Setter
public class Engine {
    private String id;
    final private Random random = new Random();
    private int power;
    private Type type;

    public Engine() {
        this.id = UUID.randomUUID().toString();
    }

    public Engine(Type type) {
        this.power = random.nextInt(1000);
        this.type = type;
        this.id = UUID.randomUUID().toString();
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
