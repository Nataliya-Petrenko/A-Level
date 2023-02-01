package com.petrenko.util;

import com.petrenko.model.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class MapToObject {

    public static Optional<Car> createCar(Map<String, String> map) {
        if (map == null) {
            return Optional.empty();
        }
            if (map.get("type_engine").equals("CAR")) {
                return createPassengerCar(map);
            } else if (map.get("type_engine").equals("TRUCK")) {
                return createTruck(map);
            } else {
                throw new NullPointerException("Type of car not exist");
            }
    }

    private static Optional<Car> createPassengerCar(Map<String, String> map){
        PassengerCar passengerCar = new PassengerCar();
        passengerCar.setEngine(new Engine(Type.CAR));
        passengerCar.setPassengerCount(Integer.parseInt(map.get("passenger_count")));
        setFieldsOfCar(map, passengerCar);
        return Optional.of(passengerCar);
    }

    private static Optional<Car> createTruck(Map<String, String> map){
        Truck truck = new Truck();
        truck.setEngine(new Engine(Type.TRUCK));
        truck.setLoadCapacity(Integer.parseInt(map.get("load_capacity")));
        setFieldsOfCar(map, truck);
        return Optional.of(truck);
    }

    private static void setFieldsOfCar(Map<String, String> map, Car car) {
        car.getEngine().setId(map.get("id_engine"));
        car.getEngine().setPower(Integer.parseInt(map.get("power")));
        car.setUuidOfCar(map.get("id_car"));
        car.setManufacturer(map.get("manufacturer"));
        car.setColor(Color.valueOf(map.get("color")));
        car.setCount(Integer.parseInt(map.get("count")));
        car.setPrice(Integer.parseInt(map.get("price")));
    }

    public static Optional<Order> createOrder(Map<String, Object> map, List<Car> listOfCars) {
        if (map == null) {
            return Optional.empty();
        }
        Order order = new Order(listOfCars);
        order.setId(map.get("id_order").toString());
        order.setTime((LocalDateTime) map.get("date"));
        return Optional.of(order);
    };

}
