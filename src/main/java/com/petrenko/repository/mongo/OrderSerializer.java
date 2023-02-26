package com.petrenko.repository.mongo;

import com.google.gson.*;
import com.petrenko.model.Car;
import com.petrenko.model.Order;

import java.lang.reflect.Type;
import java.time.format.DateTimeFormatter;

public class OrderSerializer implements JsonSerializer<Order> {
    @Override
    public JsonElement serialize(Order order, Type type, JsonSerializationContext jsonSerializationContext) {
        JsonObject jsonObject = new JsonObject();

        String timeFormat = order.getTime().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
        jsonObject.add("time", new JsonPrimitive(timeFormat));

        JsonArray carsIdArray = new JsonArray();
        order.getCars().stream()
                .map(Car::getUuidOfCar)
                .forEach(carsIdArray::add);

        jsonObject.add("cars", carsIdArray);

        return jsonObject;
    }
}
