package com.petrenko.repository.mongo;

import com.google.gson.Gson;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.petrenko.config.MongoUtil;
import com.petrenko.model.Engine;
import com.petrenko.repository.Crud;
import org.bson.Document;
import org.bson.types.ObjectId;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

import static com.mongodb.client.model.Filters.eq;

public class MongoEngineRepository implements Crud<Engine> {

    private final MongoDatabase database = MongoUtil.DATABASE;     // TODO find better approach
    private final Gson gson = new Gson();
    private final MongoCollection<Document> engines = database.getCollection("engines");
    private static MongoEngineRepository instance;

    private MongoEngineRepository() {
    }

    public static MongoEngineRepository getInstance() {
        if (instance == null) {
            instance = new MongoEngineRepository();
        }
        return instance;
    }

    @Override
    public void save(Engine engine) {
        Document document = Document.parse(gson.toJson(engine));
        engines.insertOne(document);
        engine.setId(String.valueOf(document.get("_id", Object.class)));
    }

    @Override
    public List<Engine> getAll() {
        FindIterable<Document> documents = engines.find();
        List<Engine> engineList = new LinkedList<>();
        for (Document document : documents) {
            Engine engine = gson.fromJson(document.toJson(), Engine.class);
            engine.setId(String.valueOf(document.get("_id", Object.class)));
            engineList.add(engine);
        }
        return engineList;
    }

    @Override
    public Optional<Engine> getByUuid(String id) {
        FindIterable<Document> documents = engines.find(eq("_id", new ObjectId(id)));
        Document document = documents.first();
        Engine engine;
        if (document != null) {
            engine = gson.fromJson(document.toJson(), Engine.class);
            engine.setId(String.valueOf(document.get("_id", Object.class)));
        } else {
            engine = null;
        }
        return Optional.ofNullable(engine);
    }

    @Override
    public void deleteByUuid(String id) {
        engines.deleteOne(eq("_id", new ObjectId(id)));
    }
}
