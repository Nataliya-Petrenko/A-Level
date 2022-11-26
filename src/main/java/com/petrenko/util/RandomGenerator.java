package com.petrenko.util;

import java.util.Random;

// ● Створити клас com.{lastname}.util.RandomGenerator та метод який
//генерує випадкове число в діапазоні 0 - 10.

public class RandomGenerator {
    private final Random random = new Random();

    private int maxValue = 10;

    public RandomGenerator() {
    }

    public RandomGenerator(int maxValue) {
        this.maxValue = maxValue;
    }
    public int randomGenerator() {
        return random.nextInt(maxValue + 1);
    }

    public int getMaxValue() {
        return maxValue;
    }

    public void setMaxValue(int maxValue) {
        this.maxValue = maxValue;
    }
}
