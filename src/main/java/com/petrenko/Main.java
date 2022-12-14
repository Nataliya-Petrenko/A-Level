package com.petrenko;

import com.petrenko.action.Actions;
import com.petrenko.util.UserInput;

public class Main {
    public static void main(String[] args) {
        final Actions[] values = Actions.values();
        final String[] names = mapActionsToName(values);

        while (true) {
            final int userChoice = UserInput.menu(names);
            values[userChoice].execute();
        }

    }

    private static String[] mapActionsToName(final Actions[] values) {
        String[] names = new String[values.length];
        for (int i = 0; i < values.length; i++) {
            names[i] = values[i].getName();
        }
        return names;
    }

}
