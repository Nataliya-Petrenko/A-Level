package com.petrenko.repository.mongo;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.petrenko.config.MongoUtil;
import com.petrenko.model.Car;
import com.petrenko.repository.Crud;
import org.bson.Document;
import org.bson.types.ObjectId;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

import static com.mongodb.client.model.Filters.eq;

public class MongoCarRepository implements Crud<Car> {
    private final MongoDatabase database = MongoUtil.DATABASE;     // TODO find better approach
    private final Gson gson = new GsonBuilder()
            .registerTypeAdapter(Car.class, new CarSerializer())
            .registerTypeAdapter(Car.class, new CarDeserializer())
            .create();
    private final MongoCollection<Document> cars = database.getCollection("cars");
    private static MongoCarRepository instance;

    private MongoCarRepository() {
    }

    public static MongoCarRepository getInstance() {
        if (instance == null) {
            instance = new MongoCarRepository();
        }
        return instance;
    }

    @Override
    public void save(Car car) {
        Document document = Document.parse(gson.toJson(car));
        cars.insertOne(document);
        car.setUuidOfCar(String.valueOf(document.get("_id", Object.class)));
    }

    @Override
    public List<Car> getAll() {
        FindIterable<Document> documents = cars.find();
        List<Car> engineList = new LinkedList<>();
        for (Document document : documents) {
            Car car = gson.fromJson(document.toJson(), Car.class);
            car.setUuidOfCar(String.valueOf(document.get("_id", Object.class)));
            engineList.add(car);
        }
        return engineList;
    }

    @Override
    public Optional<Car> getByUuid(String id) {
        FindIterable<Document> documents = cars.find(eq("_id", new ObjectId(id)));
        Document document = documents.first();
        Car car;
        if (document != null) {
            car = gson.fromJson(document.toJson(), Car.class);
            car.setUuidOfCar(String.valueOf(document.get("_id", Object.class)));
        } else {
            car = null;
        }
        return Optional.ofNullable(car);
    }

    @Override
    public void deleteByUuid(String id) {
        cars.deleteOne(eq("_id", new ObjectId(id)));
    }

}
