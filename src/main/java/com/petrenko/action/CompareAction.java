package com.petrenko.action;

import com.petrenko.model.Car;
import com.petrenko.util.UserInput;

public class CompareAction implements Action {
    @Override
    public void execute() {
//        final Car[] all= CAR_SERVICE.create(5);
//        for (int i = 0; i < all.length - 1; i++) {
//            Car currentCar = all[i];
//            Car nextCar = all[i + 1];
//            final boolean compare = CAR_SERVICE.carEquals(currentCar, nextCar);
//            System.out.println("Current car: " + currentCar.getUuidOfCar());
//            System.out.println("Next car: " + nextCar.getUuidOfCar());
//            System.out.println("Compare: " + compare);
//            System.out.println();
//        }

        final String idOfCar1 = UserInput.getString("Write ID of car1");
        Car car1 = CAR_SERVICE.getByUuid(idOfCar1);
        final String idOfCar2 = UserInput.getString("Write ID of car2");
        Car car2 = CAR_SERVICE.getByUuid(idOfCar2);

        final boolean compare = CAR_SERVICE.carEquals(car1, car2);
        System.out.println("Current car: " + car1.getUuidOfCar());
        System.out.println("Next car: " + car2.getUuidOfCar());
        System.out.println("Compare: " + compare);
        System.out.println();
    }
}
