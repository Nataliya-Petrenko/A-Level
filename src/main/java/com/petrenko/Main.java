package com.petrenko;

import com.petrenko.model.PassengerCar;
import com.petrenko.model.Truck;
import com.petrenko.repository.CarRepository;
import com.petrenko.service.CarService;

public class Main {
    public static void main(String[] args) {
        final CarService carService = new CarService(new CarRepository());
        PassengerCar passengerCar = carService.createPassengerCar();
        passengerCar.restore();
        Truck truck = carService.createTruck();
        truck.restore();
    }
}
