package com.petrenko.service;

import com.petrenko.model.Engine;
import com.petrenko.model.Type;
import com.petrenko.repository.Crud;
import com.petrenko.repository.JdbcEngineRepository;

import java.util.*;

public class EngineService {
    private static EngineService instance;
    private final Crud<Engine> engineRepository;

    private EngineService(final Crud<Engine> engineRepository) {
        this.engineRepository = engineRepository;
    }

    public static EngineService getInstance() {
        if (instance == null) {
            instance = new EngineService(JdbcEngineRepository.getInstance());
        }
        return instance;
    }

    public Engine createAndSave(Type type) {
        Engine engine = new Engine(type);
        engineRepository.save(engine);
        return engine;
    }

    public List<Engine> createAndSave(Type type, int numberOfEngine) {
        if (numberOfEngine < 0) {
            numberOfEngine = 0;
        }
        List<Engine> listOfEngine = new LinkedList<>();
        for (int i = 0; i < numberOfEngine; i++) {
            listOfEngine.add(createAndSave(type));
        }
        return listOfEngine;
    }

//    public Engine createAndSave() {
//        return createAndSave(Type.randomType());
//    }
//
//    public List<Engine> createAndSave(int numberOfEngine) {
//        return createAndSave(Type.randomType(), numberOfEngine);
//    }


    public List<Engine> getAll() {
        return engineRepository.getAll();
    }

    public Optional<Engine> getByUuid(String id) {
        return engineRepository.getByUuid(id);
    }

}
