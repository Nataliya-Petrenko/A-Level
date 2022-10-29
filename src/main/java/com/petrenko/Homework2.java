package com.petrenko;

import java.util.Random;

public class Homework2 {
    public static void main(String[] args) {
//        1. Задано довжини трьох сторін трикутника - знайти його площу
        Random randomSide = new Random();
        int side1 = randomSide.nextInt(100);
        int side2 = randomSide.nextInt(100);
        int side3 = randomSide.nextInt(100);
        if ((side1 + side2 <= side3) || (side1 + side3 <= side2) || (side2 + side3 <= side1)) {
            System.out.println("Triangle with sides (" + side1 + "; " + side2 + "; " + side3 + ") does not exist");
        } else {
            double p = (double)(side1 + side2 + side3)/2;
            double s = Math.sqrt(p * (p - side1) * (p - side2) * (p - side3));
            System.out.println("Square of the triangle with sides (" + side1 + "; " + side2 + "; " + side3 + ") : " + s);
        }

//        2. Згенерувати три випадкових числа і знайти найменше за модулем, використовуючи тернарний оператор.
        Random randomNumbers = new Random();
        int num1 = randomNumbers.nextInt();
        int num2 = randomNumbers.nextInt();
        int num3 = randomNumbers.nextInt();
        int min = Math.abs(num1) < Math.abs(num2) ? Math.abs(num1) : Math.abs(num2);
        min = Math.abs(num3) < min ? Math.abs(num3) : min;
        System.out.println("Among the numbers (" + num1 + "; " + num2 + "; " + num3 + ") the smallest absolute number is " + min);

//        3. Згенерувати випадкове число - обчислити чи є воно парним
        Random randomNumber = new Random();
        int number = randomNumber.nextInt();
        if (number % 2 == 0) {
            System.out.println("Number " + number + " is even");
        } else {
            System.out.println("Number " + number + " is odd");
        }

//        Додатково:
//        1. Написати метод який переводить число з 10 системи числення в двійкову. Число положительное.
        Random randomNumber2 = new Random();
        int numberToBinary = Math.abs(randomNumber2.nextInt());
        System.out.print("Number " + numberToBinary + " to binary system: ");
        StringBuilder binaryNumberStringBuilder = new StringBuilder("");
        while (numberToBinary >= 1) {
            int remainder = numberToBinary % 2;
            binaryNumberStringBuilder.append(remainder);
            numberToBinary /= 2;
        }
        binaryNumberStringBuilder.reverse();
        System.out.println(binaryNumberStringBuilder);
    }
}
