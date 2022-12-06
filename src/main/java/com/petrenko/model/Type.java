package com.petrenko.model;

import java.util.Random;

public enum Type {
    CAR,
    TRUCK;
    private static final Random random = new Random();
    public static Type randomType() {
        return Type.values()[random.nextInt(Type.values().length)];
    }
}
