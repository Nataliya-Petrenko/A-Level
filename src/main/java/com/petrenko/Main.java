package com.petrenko;

import ch.qos.logback.classic.LoggerContext;
import ch.qos.logback.classic.joran.JoranConfigurator;
import ch.qos.logback.core.joran.spi.JoranException;
import com.petrenko.config.HibernateFactoryUtil;
import com.petrenko.model.*;
import com.petrenko.service.OrderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Main {
    public static void main(String[] args) throws FileNotFoundException, JoranException {

        startLogger();
        hibernate();

    }

    private static void startLogger() throws FileNotFoundException, JoranException {
        LoggerContext loggerContext = (LoggerContext) LoggerFactory.getILoggerFactory();
        loggerContext.reset();
        JoranConfigurator configurator = new JoranConfigurator();
        configurator.setContext(loggerContext);
        configurator.doConfigure(new FileInputStream("src/main/java/com/petrenko/logback.xml"));
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

