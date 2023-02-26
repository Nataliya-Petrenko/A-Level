package com.petrenko.repository.mongo;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.petrenko.config.MongoUtil;
import com.petrenko.model.Car;
import com.petrenko.model.Order;
import com.petrenko.repository.Crud;
import org.bson.Document;
import org.bson.types.ObjectId;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

import static com.mongodb.client.model.Filters.eq;

public class MongoOrderRepository implements Crud<Order> {
    private final MongoDatabase database = MongoUtil.DATABASE;     // TODO find better approach
    private final Gson gson = new GsonBuilder()
            .registerTypeAdapter(Order.class, new OrderSerializer())
            .registerTypeAdapter(Order.class, new OrderDeserializer())
            .create();
    private final MongoCollection<Document> orders = database.getCollection("orders");
    private static MongoOrderRepository instance;

    private MongoOrderRepository() {
    }

    public static MongoOrderRepository getInstance() {
        if (instance == null) {
            instance = new MongoOrderRepository();
        }
        return instance;
    }

    @Override
    public void save(Order order) {
        Document document = Document.parse(gson.toJson(order));
        orders.insertOne(document);
        order.setId(String.valueOf(document.get("_id", Object.class)));
    }

    @Override
    public List<Order> getAll() {
        FindIterable<Document> documents = orders.find();
        List<Order> orderList = new LinkedList<>();
        for (Document document : documents) {
            Order order = gson.fromJson(document.toJson(), Order.class);
            order.setId(String.valueOf(document.get("_id", Object.class)));
            orderList.add(order);
        }
        return orderList;
    }

    @Override
    public Optional<Order> getByUuid(String id) {
        FindIterable<Document> documents = orders.find(eq("_id", new ObjectId(id)));
        Document document = documents.first();
        Order order;
        if (document != null) {
            order = gson.fromJson(document.toJson(), Order.class);
            order.setId(String.valueOf(document.get("_id", Object.class)));
        } else {
            order = null;
        }
        return Optional.ofNullable(order);
    }

    @Override
    public void deleteByUuid(String id) {
        orders.deleteOne(eq("_id", new ObjectId(id)));
    }

}
