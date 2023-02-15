package com.petrenko.repository.mongo;

import com.google.gson.*;
import com.petrenko.model.Car;
import com.petrenko.model.Order;

import java.lang.reflect.Type;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.LinkedList;
import java.util.List;

public class OrderDeserializer implements JsonDeserializer<Order> {
    @Override
    public Order deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext)
            throws JsonParseException {

        JsonObject jsonObject = jsonElement.getAsJsonObject();

        LocalDateTime time = LocalDateTime.parse(jsonObject.get("time").getAsString(),
                DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));

        JsonArray carsIdJsonArray = jsonObject.getAsJsonArray("cars");
        List<String> carsID = new LinkedList<>();
        carsIdJsonArray.forEach(c -> carsID.add(c.getAsString()));

        List<Car> cars = new LinkedList<>();
        MongoCarRepository carRepository = MongoCarRepository.getInstance();
        carsID.forEach(c -> carRepository.getByUuid(c).ifPresent(cars::add));

        Order order = new Order();
        order.setCars(cars);
        order.setTime(time);

        return order;
    }
}
