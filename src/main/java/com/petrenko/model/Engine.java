package com.petrenko.model;

import java.util.Random;

public class Engine {
    final private Random random = new Random();
    private int power;
    private Type type;

    public Engine() {
    }

    public Engine(Type type) {
        this.power = random.nextInt(1000);
        this.type = type;
    }

    public int getPower() {
        return power;
    }

    public Type getType() {
        return type;
    }

    public void setPower(int power) {
        this.power = power;
    }

    public void setType(Type type) {
        this.type = type;
    }
}
