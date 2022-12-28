package com.petrenko.container;

import com.petrenko.model.Car;
// car1 == car2 => 0
// car1 > car2 => +
// car1 < car2 => -
public class CarComparator {

    public int compare(final Car car1, final Car car2) {
        return car1.getCount() - (car2.getCount());
    }

//    public int compareById(final Car car1, final Car car2) {
//        return car1.getUuidOfCar().compareTo(car2.getUuidOfCar());
//    }
}
