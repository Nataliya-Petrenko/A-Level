package com.petrenko.model;

import java.util.Random;

public class Engine {
    final private Random random = new Random();
    private int power;
    private String type;

    public Engine() {
    }

    public Engine(String type) {
        this.power = random.nextInt(1000);
        this.type = type;
    }

    public int getPower() {
        return power;
    }

    public String getType() {
        return type;
    }

    public void setPower(int power) {
        this.power = power;
    }

    public void setType(String type) {
        this.type = type;
    }
}
