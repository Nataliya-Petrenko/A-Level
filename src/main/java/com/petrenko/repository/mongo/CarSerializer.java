package com.petrenko.repository.mongo;

import com.google.gson.*;
import com.petrenko.model.Car;
import com.petrenko.model.Order;
import com.petrenko.model.PassengerCar;
import com.petrenko.model.Truck;

import java.lang.reflect.Type;

public class CarSerializer implements JsonSerializer<Car> {

    @Override
    public JsonElement serialize(Car car, Type type, JsonSerializationContext jsonSerializationContext) {
        JsonObject jsonObject = new JsonObject();

        if (car.getType().equals(com.petrenko.model.Type.CAR)) {
            jsonObject.addProperty("passengerCount", ((PassengerCar) car).getPassengerCount());
        }
        if (car.getType().equals((com.petrenko.model.Type.TRUCK))) {
            jsonObject.addProperty("loadCapacity", ((Truck) car).getLoadCapacity());
        }

        JsonArray ordersIdArray = new JsonArray();
        car.getOrders().stream()
                .map(Order::getId)
                .forEach(ordersIdArray::add);

        jsonObject.add("orders", ordersIdArray);

        return jsonObject;
    }
}
