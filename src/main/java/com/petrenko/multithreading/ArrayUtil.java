package com.petrenko.multithreading;

public class ArrayUtil {
    private static final Object PRIVATE_LOCK_OBJECT_1 = new Object();
    private static final Object PRIVATE_LOCK_OBJECT_2 = new Object();

    public static void setUniqueNumber(int[] array, int number) {
        synchronized (PRIVATE_LOCK_OBJECT_1) {
            for (int i = 0; i < 100; i++) {
                if (array[i] == 0) {
                    array[i] = number;
                    return;
                } else if (number == array[i]) {
                    return;
                }
            }
        }
    }

    public static boolean isFull(int[] array) {
        synchronized (PRIVATE_LOCK_OBJECT_2) {
            return array[array.length - 1] != 0;
        }
    }
}
