package com.petrenko.config;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoDatabase;

public class MongoUtil {
    private static MongoClient mongoClient;

    public static final MongoDatabase DATABASE = MongoUtil.connect("mongo"); // TODO find better approach with delete

    public static MongoDatabase connect(String databaseName) {
        return getMongoClient().getDatabase(databaseName);
    }

    private static MongoClient getMongoClient() {
        if (mongoClient == null) {
            mongoClient = new MongoClient("localhost", 27017);
        }
        return mongoClient;
    }

    public static void deleteDatabase() {
        DATABASE.drop();
    } // TODO find better approach with delete
}
