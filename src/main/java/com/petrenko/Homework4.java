package com.petrenko;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;

public class Homework4 {
    public static void main(String[] args) {
//        Створіть рядок "Hello world!"
//        Виведіть перший символ
//        Виведіть останній символ
//        Важливо: Номер елемента повинен обчислювати динамічно залежно від
//        рядка (не можна вказати 0 і 11, та не можно вказати “!”)
        String str = "Hello world!";
//        System.out.println(str.charAt(0));
        System.out.println("First element of string \"" + str + "\": " + str.repeat(2).charAt(str.length()));
        System.out.println("Last element of string \"" + str + "\": " + str.charAt(str.length() - 1));

        System.out.println();

//        Напишіть програму на Java, щоб перевірити, чи закінчується дана строка
//        вказаною підстрокою. Приклад:
//        "Java Exercises" закінчується на "se"? // false
//        "Java Exercise" закінчується на "se"? // true
        String str11 = "Java Exercises";
        String str12 = "Java Exercise";
        String strForCompare1 = "se";

        System.out.printf("String \"%s\" has substring \"%s\" in the end: ", str11, strForCompare1);
        System.out.println(str11.endsWith(strForCompare1));

        System.out.printf("String \"%s\" has substring \"%s\" in the end: ", str12, strForCompare1);
        System.out.println(str12.endsWith(strForCompare1));

        System.out.println();

//        Напишіть програму на Java, щоб перевірити, чи два об’єкти String містять
//        однакові дані:
//        "Stephen Edwin King" містить "Walter Winchell"? //false
//        "Stephen Edwin King" містить "Edwin"? true
        String str2 = "Stephen Edwin King";
        String strForCompare21 = "Walter Winchell";
        String strForCompare22 = "Edwin";

        System.out.printf("String \"%s\" contains substring \"%s\": ", str2, strForCompare21);
        System.out.println(str2.matches(".*" + strForCompare21 + ".*"));

        System.out.printf("String \"%s\" contains substring \"%s\": ", str2, strForCompare22);
        System.out.println(str2.matches(".*" + strForCompare22 + ".*"));

        System.out.println();

//        Напишіть програму Java для порівняння даного рядка з іншим рядком, ігноруючи
//        регістр
//        "Stephen Edwin King" містить "Walter Winchell"? // false
//        "Stephen Edwin King" містить "stephen edwin king"? // True
        String str3 = "Stephen Edwin King";
        String strForCompare31 = "Walter Winchell";
        String strForCompare32 = "stephen edwin king";

        System.out.printf("String \"%s\" and string \"%s\" are equals ignore case:: ", str3, strForCompare31);
        System.out.println(str3.equalsIgnoreCase(strForCompare31));

        System.out.printf("String \"%s\" and string \"%s\" are equals ignore case:: ", str3, strForCompare32);
        System.out.println(str3.equalsIgnoreCase(strForCompare32));

        System.out.println();

//        Напишіть програму Java, щоб перевірити, чи даний рядок починається з вмісту іншого
//        рядка.
//        Red is favorite color. Чи починається із Red? //true
//        Orange is also my favorite color. Чи починається із Red? //false
        String str41= "Red is favorite color";
        String strForCompare41 = "Red";
        String str42 = "Orange is also my favorite color";
        String strForCompare42 = "Red";

        System.out.printf("String \"%s\" has substring \"%s\" in the start: ", str41, strForCompare41);
        System.out.println(str41.startsWith(strForCompare41));

        System.out.printf("String \"%s\" has substring \"%s\" in the start: ", str42, strForCompare42);
        System.out.println(str42.startsWith(strForCompare42));

        System.out.println();

//        Додатково
//        Напишіть програму Java, щоб перевірити, чи даний рядок починається з вмісту іншого
//        рядка використовуючи рядки введені з консолі
        String str5 = "";
        String strForCompare5 = "";
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            System.out.println("Enter string");
            str5 = reader.readLine();
            System.out.println("Enter substring for compare");
            strForCompare5 = reader.readLine();
        }
        catch (IOException e) {
            e.printStackTrace();
        }

        System.out.printf("String \"%s\" has substring \"%s\" in the start: ", str5, strForCompare5);
        System.out.println(str5.startsWith(strForCompare5));
    }
}
