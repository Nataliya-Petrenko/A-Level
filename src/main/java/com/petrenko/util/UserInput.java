package com.petrenko.util;

import com.petrenko.action.Actions;
import lombok.SneakyThrows;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class UserInput {
    private static BufferedReader READER = new BufferedReader(new InputStreamReader(System.in));

    @SneakyThrows
    public static int getInt(final String question) {
        String line;
        do {
            System.out.println(question);
            line = READER.readLine();
        } while (!checkLineIsDigit(line));
        return Integer.parseInt(line);
    }
    @SneakyThrows
    public static String getString(final String question) {
        System.out.println(question);
        final String line = READER.readLine();
        return line;
    }

    public static int menu(final String[] names) {
        final Actions[] values = Actions.values();
        int userChoice;
        do {
            printNames(names);
            userChoice = getInt("Write what you want to do:");
        } while (userChoice < 0 || userChoice >= values.length);
        System.out.println("User choice: " + userChoice);
        System.out.println();
        return userChoice;
    }

    private static void printNames(String[] names) {
        for (int i = 0; i < names.length; i++) {
            System.out.println(i + " " + names[i]);
        }
    }

    private static boolean checkLineIsDigit(String line) {
        char[] lineChars = line.toCharArray();
        for (char letter : lineChars) {
            if (!Character.isDigit(letter)) {
                return false;
            }
        }
        return true;
    }

}
