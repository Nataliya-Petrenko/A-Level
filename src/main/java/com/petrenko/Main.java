package com.petrenko;

import ch.qos.logback.classic.LoggerContext;
import ch.qos.logback.classic.joran.JoranConfigurator;
import ch.qos.logback.core.joran.spi.JoranException;
import com.petrenko.config.HibernateFactoryUtil;
import com.petrenko.model.*;
import com.petrenko.service.CarService;
import com.petrenko.service.OrderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

public class Main {
    public static void main(String[] args) throws FileNotFoundException, JoranException {

        hibernate();

        CarService carService = CarService.getInstance();
        Optional<Car> optionalCar = carService.createByCarFactory(Type.CAR);
        optionalCar.ifPresent(System.out::println);
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

}

