package com.petrenko.repository;

import com.petrenko.model.*;
import com.petrenko.util.MapToObject;
import lombok.SneakyThrows;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.*;

public class JdbcOrderRepository implements Crud<Order> {
    private static JdbcOrderRepository instance;

    private JdbcOrderRepository() {
    }

    public static JdbcOrderRepository getInstance() {
        if (instance == null) {
            JdbcCarRepository.getInstance();
            instance = new JdbcOrderRepository();
            createTable();
        }
        return instance;
    }

    @SneakyThrows
    private static void createTable() {
        String stringSql = "CREATE TABLE IF NOT EXISTS OrderCars" +
                "(" +
                "id_order VARCHAR NOT NULL PRIMARY KEY," +
                "date TIMESTAMP NOT NULL" +
                ");" +

                "CREATE TABLE IF NOT EXISTS OrderCars_Car" +
                "(" +
                "id_order VARCHAR NOT NULL REFERENCES OrderCars(id_order), " +
                "id_car VARCHAR NOT NULL REFERENCES Car(id_car)" +
                ");";

        JdbcManager.sendStatementForUpdate(stringSql);
    }

    @SneakyThrows
    @Override
    public void save(Order order) {
        final Connection connection = JdbcManager.getConnection();
        connection.setAutoCommit(false);

        String prepareEngine = "INSERT INTO OrderCars(id_order, date) VALUES (?, ?)";
        PreparedStatement preparedStatementEngine = connection.prepareStatement(prepareEngine);
        preparedStatementEngine.setString(1, order.getId());
        preparedStatementEngine.setTimestamp(2, Timestamp.valueOf(order.getTime()));
        preparedStatementEngine.executeUpdate();
        preparedStatementEngine.close();

        order.getCars().forEach(car -> {
            String prepareCar = "INSERT INTO OrderCars_Car(id_order, id_car) VALUES (?, ?)";
            PreparedStatement preparedStatementCar = null;
            try {
                preparedStatementCar = connection.prepareStatement(prepareCar);
                preparedStatementCar.setString(1, order.getId());
                preparedStatementCar.setString(2, car.getUuidOfCar());
                preparedStatementCar.executeUpdate();
                preparedStatementCar.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        });

        connection.setAutoCommit(true);
        connection.close();
    }

    @SneakyThrows
    @Override
    public List<Order> getAll() {

        String stringSql = "SELECT id_order FROM OrderCars";

        final Connection connection = JdbcManager.getConnection();
        final Statement statement = connection.createStatement();
        connection.setAutoCommit(false);

        final ResultSet resultSet = statement.executeQuery(stringSql);

        List<Order> list = new LinkedList<>();
        while (resultSet.next()) {
            String id = resultSet.getString("id_order");
            Map<String, Object> map = getOrderMap(connection, id);
            List<String> listIdOfCars = listIdOfCars(connection, id);
            Order order = MapToObject.createOrder(map, listOfCars(listIdOfCars)).get();
            list.add(order);
        }

        statement.close();
        connection.setAutoCommit(true);
        connection.close();

        return list;
    }

    @SneakyThrows
    @Override
    public Optional<Order> getByUuid(String id) {

        final Connection connection = JdbcManager.getConnection();
        connection.setAutoCommit(false);

        Map<String, Object> map = getOrderMap(connection, id);
        List<String> listIdOfCars = listIdOfCars(connection, id);

        connection.setAutoCommit(true);
        connection.close();

        return MapToObject.createOrder(map, listOfCars(listIdOfCars));

    }

    private List<Car> listOfCars(List<String> listIdOfCars) {
        return listIdOfCars.stream()
                .map(s -> JdbcCarRepository.getInstance().getByUuid(s))
                .filter(Optional::isPresent)
                .map(Optional::get)
                .toList();
    }

    @SneakyThrows
    private Map<String, Object> getOrderMap(Connection connection, String id) {

        final Statement statement = connection.createStatement();
        String stringSql = "SELECT * FROM OrderCars " +
                "WHERE id_order = '" + id + "';";
        final ResultSet resultSet = statement.executeQuery(stringSql);

        Map<String, Object> map = new HashMap<>();
        resultSet.next();
        map.put("id_order", resultSet.getString("id_order"));
        map.put("date", resultSet.getObject("date", LocalDateTime.class));

        statement.close();

        return map;
    }

    @SneakyThrows
    private List<String> listIdOfCars(Connection connection, String id) {

        String stringSql = "SELECT * FROM OrderCars_Car " +
                "WHERE id_order = '" + id + "';";

        final Statement statement = connection.createStatement();
        final ResultSet resultSet = statement.executeQuery(stringSql);

        List<String> listIdOfCars = new LinkedList<>();
        while (resultSet.next()) {
            listIdOfCars.add(resultSet.getString("id_car"));
        }

        statement.close();

        return listIdOfCars;

    }

    @SneakyThrows
    @Override
    public void deleteByUuid(String id) {
        String stringSql = "DELETE FROM OrderCars WHERE id_order = '" + id + "';";
        JdbcManager.sendStatementForUpdate(stringSql);
    }

    @SneakyThrows
    public static void dropTable() {
        String stringSql = "DROP TABLE IF EXISTS OrderCars_Car;" +
                "DROP TABLE IF EXISTS OrderCars;";
        JdbcManager.sendStatementForUpdate(stringSql);
    }
}
