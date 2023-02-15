package com.petrenko.repository.mongo;

import com.google.gson.*;
import com.petrenko.model.Car;
import com.petrenko.model.PassengerCar;
import com.petrenko.model.Truck;

import java.lang.reflect.Type;

public class CarDeserializer implements JsonDeserializer<Car> {
    @Override
    public Car deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
        JsonObject jsonObject = jsonElement.getAsJsonObject();

        Car car;
        String typeCar = jsonObject.get("type").getAsString();
        if (typeCar.equals("CAR")) {
            car = new PassengerCar();
        } else if (typeCar.equals("TRUCK")) {
            car = new Truck();
        } else {
            throw new NullPointerException();
        }

//        JsonArray ordersIdJsonArray = jsonObject.getAsJsonArray("orders");
//        List<String> ordersID = new LinkedList<>();
//        ordersIdJsonArray.forEach(c -> ordersID.add(c.getAsString()));
//
//        Set<Order> orders = new LinkedHashSet<>();
//        MongoOrderRepository orderRepository = MongoOrderRepository.getInstance();
//        ordersID.forEach(c -> orderRepository.getByUuid(c).ifPresent(orders::add));

        return jsonDeserializationContext.deserialize(jsonObject, car.getClass());

    }
}
