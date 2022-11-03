package com.petrenko;

import java.util.Random;
import java.util.Arrays;

public class Homework5 {
    public static void main(String[] args) {
        exercise1();

    }
//    Створіть масив з 12 випадкових цілих чисел з відрізка [-15;15]. Визначте який
//    елемент є в цьому масиві максимальним і повідомте індекс його останнього
//    входження в масив.  (приклад: 11, 0, 14, -4, 0, 14, 3 -> index = 5).
    private static void exercise1() {
        Random random = new Random();
        int[] myArray = new int[12];
        for (int i = 0; i < 12; i++) {
            myArray[i] = random.nextInt(30) - 15;
        }
        System.out.println(Arrays.toString(myArray));
        int maxValue = myArray[0];
        int indexForMaxValue = 0;
        for (int i = 0; i < myArray.length; i++) {
            if (myArray[i] >= maxValue) {
                maxValue = myArray[i];
                indexForMaxValue = i;
            }
        }
        System.out.printf("The last maximum element of the array is %d, its index is %d", maxValue, indexForMaxValue);
    }
//    Створіть масив із 8 випадкових цілих чисел з відрізка [1;10]. Виведіть масив на
//    екран у рядок. Замініть кожен елемент з непарним індексом на нуль. Знову
//    виведіть масив на екран в окремому рядку

//    Створіть масив з 4 випадкових цілих чисел з відрізка [10;99]. Виведіть його на
//    екран у рядок. Далі визначте та виведіть на екран повідомлення про те, чи є
//    масив строго зростаючою послідовністю (приклад: 11, 23, 45, 66).

//    Створіть 2 масиви з 5 випадкових цілих чисел з відрізка [0;5] кожен. Виведіть
//    масиви на екран у двох окремих рядках . Порахуйте середнє арифметичне
//    елементів кожного масиву і повідомте, для якого з масивів це значення виявилося
//    більшим (або повідомте, що їхні середні арифметичні рівні)

//    Дополнительное задание - доделать алгоритм сортировки из лекции

}
