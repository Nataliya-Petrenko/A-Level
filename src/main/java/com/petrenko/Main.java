package com.petrenko;

import com.petrenko.model.Car;
import com.petrenko.service.CarService;

//У класі Main створити 3 машини і показати інформацію на консоль за
//допомогою класу CarService
public class Main {
    public static void main(String[] args) {
        CarService carService = new CarService();
        Car car1 = carService.create();
        Car car2 = carService.create();
        Car car3 = carService.create();
        carService.print(car1);
        carService.print(car2);
        carService.print(car3);
    }
}
