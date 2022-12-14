package com.petrenko;

import com.petrenko.action.Actions;
import com.petrenko.model.*;
import com.petrenko.repository.CarRepository;
import com.petrenko.service.CarService;
import com.petrenko.util.AlgorithmUtil;
import com.petrenko.util.UserInput;
import lombok.SneakyThrows;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.nio.Buffer;

public class Main {
//    @SneakyThrows
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
