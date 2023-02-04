package com.petrenko.repository;

import com.petrenko.model.*;
import com.petrenko.util.MapToObject;
import lombok.SneakyThrows;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.*;
import java.util.stream.Collectors;

public class JdbcEngineRepository implements Crud<Engine>{
    private static JdbcEngineRepository instance;

    private JdbcEngineRepository() {
    }

    public static JdbcEngineRepository getInstance() {
        if (instance == null) {
            instance = new JdbcEngineRepository();
            createTable();
        }
        return instance;
    }

    @SneakyThrows
    private static void createTable() {
        String stringSql = "CREATE TABLE IF NOT EXISTS Engine" +
                "(" +
                "id_engine VARCHAR NOT NULL PRIMARY KEY," +
                "power INTEGER NOT NULL," +
                "type_engine VARCHAR NOT NULL" +
                ");";

        JdbcManager.sendStatementForUpdate(stringSql);
    }

    @SneakyThrows
    @Override
    public void save(Engine engine) {
        final Connection connection = JdbcManager.getConnection();

        String prepareEngine = "INSERT INTO Engine(id_engine, power, type_engine) VALUES (?, ?, ?)";
        PreparedStatement preparedStatementEngine = connection.prepareStatement(prepareEngine);
        preparedStatementEngine.setString(1, engine.getId());
        preparedStatementEngine.setInt(2, engine.getPower());
        preparedStatementEngine.setString(3, engine.getType().toString());
        preparedStatementEngine.executeUpdate();
        preparedStatementEngine.close();

        connection.close();
    }

    @SneakyThrows
    @Override
    public List<Engine> getAll() {
        String stringSql = "SELECT * FROM Engine";

        final Connection connection = JdbcManager.getConnection();
        final Statement statement = connection.createStatement();
        final ResultSet resultSet = statement.executeQuery(stringSql);

        List<Map<String, String>> listOfMaps = new LinkedList<>();
        while (resultSet.next()) {
            Map<String, String> map = new HashMap<>();
            map.put("id_engine", resultSet.getString("id_engine"));
            map.put("power", resultSet.getString("power"));
            map.put("type_engine", resultSet.getString("type_engine"));
            listOfMaps.add(map);
        }

        statement.close();
        connection.close();

        return listOfMaps.stream()
                .map(MapToObject::createEngine)
                .filter(Optional::isPresent)
                .map(Optional::get)
                .collect(Collectors.toList());
    }


    @SneakyThrows
    @Override
    public Optional<Engine> getByUuid(String id) {

        String stringSql = "SELECT * FROM Engine " +
                "WHERE Engine.id_engine = '" + id + "';";

        final Connection connection = JdbcManager.getConnection();
        final Statement statement = connection.createStatement();
        final ResultSet resultSet = statement.executeQuery(stringSql);

        Map<String, String> map = new HashMap<>();
        resultSet.next();
        map.put("id_engine", resultSet.getString("id_engine"));
        map.put("power", resultSet.getString("power"));
        map.put("type_engine", resultSet.getString("type_engine"));

        statement.close();
        connection.close();

        return MapToObject.createEngine(map);

    }

    @SneakyThrows
    @Override
    public void deleteByUuid(String id) {
        String stringSql = "DELETE FROM Engine WHERE id_engine = '" + id + "';";
        JdbcManager.sendStatementForUpdate(stringSql);
    }

    @SneakyThrows
    public static void dropTable() {
        String stringSql = "DROP TABLE IF EXISTS Engine;";
        JdbcManager.sendStatementForUpdate(stringSql);
    }
}
