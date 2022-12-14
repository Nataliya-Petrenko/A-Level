package com.petrenko.util;

import com.petrenko.action.Actions;
import lombok.SneakyThrows;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class UserInput {
    private static BufferedReader READER = new BufferedReader(new InputStreamReader(System.in));

    @SneakyThrows
    public static int menu(final String[] names) {
        final Actions[] values = Actions.values();
        int userChoice;
        do {
            String line;
            do {
                printQuestionAndNames(names);
                line = READER.readLine();
            } while (!checkLineIsDigit(line));
            userChoice = Integer.parseInt(line);
        } while (userChoice < 0 || userChoice >= values.length);
        System.out.println("User choice: " + userChoice);
        return userChoice;
    }

    private static void printQuestionAndNames(String[] names) {
        System.out.println("Write what you want to do:");
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
