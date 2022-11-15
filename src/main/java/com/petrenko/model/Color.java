package com.petrenko.model;

import java.util.Random;

public enum Color {
    RED,
    ORANGE,
    YELLOW,
    GREEN,
    BLUE,
    VIOLET,
    BLACK,
    WHITE;

    private static final Random random = new Random();
    public static Color randomColor() {
        return Color.values()[random.nextInt(Color.values().length)];
    }
}
