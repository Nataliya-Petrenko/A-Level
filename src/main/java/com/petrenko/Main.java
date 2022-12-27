package com.petrenko;

import com.petrenko.action.Actions;
import com.petrenko.container.GenericContainer;
import com.petrenko.model.Car;
import com.petrenko.service.CarService;
import com.petrenko.util.UserInput;

public class Main {
    public static void main(String[] args) {
//        final Actions[] values = Actions.values();
//        final String[] names = mapActionsToName(values);
//
//        while (true) {
//            final int userChoice = UserInput.menu(names);
//            values[userChoice].execute();
//        }

        CarService carService = CarService.getInstance();
        Car car = carService.create();
        carService.printAll();
        System.out.println();

        GenericContainer<Car> genericContainer = new GenericContainer<>(car);
        genericContainer.print();
        genericContainer.increaseCount();
        genericContainer.print();
        genericContainer.increaseCount(1111.1);
        genericContainer.print();


    }

//    private static String[] mapActionsToName(final Actions[] values) {
//        String[] names = new String[values.length];
//        for (int i = 0; i < values.length; i++) {
//            names[i] = values[i].getName();
//        }
//        return names;
//    }

}
