package com.petrenko;

import ch.qos.logback.classic.LoggerContext;
import ch.qos.logback.classic.joran.JoranConfigurator;
import ch.qos.logback.core.joran.spi.JoranException;
import com.petrenko.config.HibernateFactoryUtil;
import com.petrenko.config.MongoUtil;
import com.petrenko.model.*;
import com.petrenko.service.CarService;
import com.petrenko.service.EngineService;
import com.petrenko.service.OrderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

public class Main {
    public static void main(String[] args) throws FileNotFoundException, JoranException {

//        hibernate();
//        carFactory();

        MongoUtil.deleteDatabase();

//        mongoEngine();
//        mongoCar();
        mongoOrder();
    }

    private static void hibernate() {
        HibernateFactoryUtil.getSessionFactory();
        OrderService orderService = OrderService.getInstance();
        orderService.create(5);
        orderService.printAll();
        String id = orderService.getAll()
                .stream()
                .findFirst()
                .map(Order::getId)
                .get();

        System.out.println("Delete: " + id);
        orderService.deleteById(id);

        orderService.printAll();
    }

    private static void carFactory() {
        CarService carService = CarService.getInstance();
        Optional<Car> optionalCar = carService.createByCarFactory(Type.CAR);
        optionalCar.ifPresent(System.out::println);
    }

    private static void mongoEngine() {
        EngineService engineService = EngineService.getInstance();

        engineService.createAndSave(Type.randomType());
        engineService.createAndSave(Type.randomType());
        engineService.createAndSave(Type.randomType());

        List<Engine> engines = engineService.getAll();
        System.out.println("Created engines:");
        engines.forEach(System.out::println);

        String id = engines.stream()
                .map(Engine::getId)
                .findFirst().get();
        System.out.println("Next actions with id: " + id);

        Optional<Engine> byId = engineService.getByUuid(id);
        System.out.println("GetById: " + byId.get());

        engineService.deleteById(id);
        List<Engine> enginesAfterDelete = engineService.getAll();
        System.out.println("All engines after deleted:");
        enginesAfterDelete.forEach(System.out::println);
    }

    private static void mongoCar() {
        CarService carService = CarService.getInstance();

        carService.create(5);

        List<Car> cars = carService.getAll();
        System.out.println("Created cars:");
        cars.forEach(System.out::println);

        String id = cars.stream()
                .map(Car::getUuidOfCar)
                .findFirst().get();
        System.out.println("Next actions with id: " + id);

        Optional<Car> byId = carService.getByUuid(id);
        System.out.println("GetById: " + byId.get());

        carService.deleteByUuid(id);
        List<Car> carsAfterDelete = carService.getAll();
        System.out.println("All cars after deleted:");
        carsAfterDelete.forEach(System.out::println);
    }

    private static void mongoOrder() {
        OrderService orderService = OrderService.getInstance();

        orderService.create(5);

        List<Order> orders = orderService.getAll();
        System.out.println("Created orders:");
        orders.forEach(System.out::println);

        String id = orders.stream()
                .map(Order::getId)
                .findFirst().get();
        System.out.println("Next actions with id: " + id);

        Optional<Order> byId = orderService.getByID(id);
        System.out.println("GetById: " + byId.get());

        orderService.deleteById(id);
        List<Order> ordersAfterDelete = orderService.getAll();
        System.out.println("All orders after deleted:");
        ordersAfterDelete.forEach(System.out::println);
    }

}

