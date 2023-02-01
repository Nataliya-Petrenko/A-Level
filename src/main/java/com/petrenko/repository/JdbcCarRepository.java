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

public class JdbcCarRepository implements Crud<Car> {

    private static JdbcCarRepository instance;

    private JdbcCarRepository() {
    }

    public static JdbcCarRepository getInstance() {
        if (instance == null) {
            instance = new JdbcCarRepository();
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
                ");" +

                "CREATE TABLE IF NOT EXISTS Car" +
                "(" +
                "id_car VARCHAR NOT NULL PRIMARY KEY," +
                "manufacturer VARCHAR NOT NULL," +
                "id_engine VARCHAR NOT NULL REFERENCES Engine(id_engine)," +
                "color VARCHAR NOT NULL," +
                "count INTEGER NOT NULL," +
                "price INTEGER NOT NULL" +
                ");" +

                "CREATE TABLE IF NOT EXISTS Passenger_car" +
                "(" +
                "id_car VARCHAR NOT NULL PRIMARY KEY REFERENCES Car(id_car)," +
                "passenger_count INTEGER NOT NULL" +
                ");" +

                "CREATE TABLE IF NOT EXISTS Truck" +
                "(" +
                "id_car VARCHAR NOT NULL PRIMARY KEY REFERENCES Car(id_car)," +
                "load_capacity INTEGER NOT NULL" +
                ");";

        JdbcManager.sendStatementForUpdate(stringSql);
    }

    @SneakyThrows
    @Override
    public void save(Car car) {
        final Connection connection = JdbcManager.getConnection();
        connection.setAutoCommit(false);

        String prepareEngine = "INSERT INTO Engine(id_engine, power, type_engine) VALUES (?, ?, ?)";
        PreparedStatement preparedStatementEngine = connection.prepareStatement(prepareEngine);
        preparedStatementEngine.setString(1, car.getEngine().getId());
        preparedStatementEngine.setInt(2, car.getEngine().getPower());
        preparedStatementEngine.setString(3, car.getEngine().getType().toString());
        preparedStatementEngine.executeUpdate();
        preparedStatementEngine.close();

        String prepareCar = "INSERT INTO Car(id_car, manufacturer, id_engine, color, count, price) VALUES (?, ?, ?, ?, ?, ?)";
        PreparedStatement preparedStatementCar = connection.prepareStatement(prepareCar);
        preparedStatementCar.setString(1, car.getUuidOfCar());
        preparedStatementCar.setString(2, car.getManufacturer());
        preparedStatementCar.setString(3, car.getEngine().getId());
        preparedStatementCar.setString(4, car.getColor().toString());
        preparedStatementCar.setInt(5, car.getCount());
        preparedStatementCar.setInt(6, car.getPrice());
        preparedStatementCar.executeUpdate();
        preparedStatementCar.close();

        if (car.getEngine().getType() == Type.CAR) {
            String preparePassengerCar = "INSERT INTO Passenger_car(id_car, passenger_count) VALUES (?, ?)";
            PreparedStatement preparedStatementPassengerCar = connection.prepareStatement(preparePassengerCar);
            preparedStatementPassengerCar.setString(1, car.getUuidOfCar());
            PassengerCar passengerCar = (PassengerCar) car;
            preparedStatementPassengerCar.setInt(2, passengerCar.getPassengerCount());
            preparedStatementPassengerCar.executeUpdate();
            preparedStatementPassengerCar.close();
        }

        if (car.getEngine().getType() == Type.TRUCK) {
            String prepareTruck = "INSERT INTO Truck(id_car, load_capacity) VALUES (?, ?)";
            PreparedStatement preparedStatementTruck = connection.prepareStatement(prepareTruck);
            preparedStatementTruck.setString(1, car.getUuidOfCar());
            Truck truck = (Truck) car;
            preparedStatementTruck.setInt(2, truck.getLoadCapacity());
            preparedStatementTruck.executeUpdate();
            preparedStatementTruck.close();
        }

        connection.setAutoCommit(true);
        connection.close();
    }

    @SneakyThrows
    @Override
    public List<Car> getAll() {
        String stringSql = "SELECT * FROM Car " +
                "LEFT JOIN Engine ON Car.id_engine = Engine.id_engine " +
                "LEFT JOIN Passenger_car ON Car.id_car =  Passenger_car.id_car " +
                "LEFT JOIN Truck ON Car.id_car = Truck.id_car";

        final Connection connection = JdbcManager.getConnection();
        final Statement statement = connection.createStatement();
        final ResultSet resultSet = statement.executeQuery(stringSql);

        List<Map<String, String>> listOfMaps = new LinkedList<>();
        while (resultSet.next()) {
            Map<String, String> map = new HashMap<>();
            map.put("id_engine", resultSet.getString("id_engine"));
            map.put("power", resultSet.getString("power"));
            map.put("type_engine", resultSet.getString("type_engine"));
            map.put("id_car", resultSet.getString("id_car"));
            map.put("manufacturer", resultSet.getString("manufacturer"));
            map.put("color", resultSet.getString("color"));
            map.put("count", resultSet.getString("count"));
            map.put("price", resultSet.getString("price"));
            if (resultSet.getString("type_engine").equals("CAR")) {
                map.put("passenger_count", resultSet.getString("passenger_count"));
            }
            if (resultSet.getString("type_engine").equals("TRUCK")) {
                map.put("load_capacity", resultSet.getString("load_capacity"));
            }
            listOfMaps.add(map);

//            System.out.printf("id_engine %s, power %s, type_engine %s, id_car %s, manufacturer %s, color %s, count %s, price %s%n",
//                    resultSet.getString("id_engine"),
//                    resultSet.getString("power"),
//                    resultSet.getString("type_engine"),
//                    resultSet.getString("id_car"),
//                    resultSet.getString("manufacturer"),
//                    resultSet.getString("color"),
//                    resultSet.getString("count"),
//                    resultSet.getString("price"));
        }

        statement.close();
        connection.close();

        return listOfMaps.stream()
                .map(MapToObject::createCar)
                .filter(Optional::isPresent)
                .map(Optional::get)
                .collect(Collectors.toList());
    }


    @SneakyThrows
    @Override
    public Optional<Car> getByUuid(String id) {

        String stringSql = "SELECT * FROM Car " +
                "LEFT JOIN Engine ON Car.id_engine = Engine.id_engine " +
                "LEFT JOIN Passenger_car ON Car.id_car =  Passenger_car.id_car " +
                "LEFT JOIN Truck ON Car.id_car = Truck.id_car " +
                "WHERE Car.id_car = '" + id + "';";

        final Connection connection = JdbcManager.getConnection();
        final Statement statement = connection.createStatement();
        final ResultSet resultSet = statement.executeQuery(stringSql);

        Map<String, String> map = new HashMap<>();
        resultSet.next();
        map.put("id_engine", resultSet.getString("id_engine"));
        map.put("power", resultSet.getString("power"));
        map.put("type_engine", resultSet.getString("type_engine"));
        map.put("id_car", resultSet.getString("id_car"));
        map.put("manufacturer", resultSet.getString("manufacturer"));
        map.put("color", resultSet.getString("color"));
        map.put("count", resultSet.getString("count"));
        map.put("price", resultSet.getString("price"));
        if (resultSet.getString("type_engine").equals("CAR")) {
            map.put("passenger_count", resultSet.getString("passenger_count"));
        }
        if (resultSet.getString("type_engine").equals("TRUCK")) {
            map.put("load_capacity", resultSet.getString("load_capacity"));
        }

        statement.close();
        connection.close();

        return MapToObject.createCar(map);

    }

    @SneakyThrows
    @Override
    public void deleteByUuid(String id) {
        String stringSql = "DELETE FROM Car WHERE id_car = '" + id + "';";
        JdbcManager.sendStatementForUpdate(stringSql);
    }

    @SneakyThrows
    public static void dropTable() {
        String stringSql = "DROP TABLE IF EXISTS Passenger_car;" +
                "DROP TABLE IF EXISTS Truck;"+
                "DROP TABLE IF EXISTS Car;" +
                "DROP TABLE IF EXISTS Engine;";
        JdbcManager.sendStatementForUpdate(stringSql);
    }
}
