package com.petrenko.service;

import com.petrenko.model.Car;
import com.petrenko.model.Order;
import com.petrenko.repository.Crud;
import com.petrenko.repository.HibernateRepository.HibernateOrderRepository;
import com.petrenko.repository.JdbcOrderRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

public class OrderService {
    private static OrderService instance;
    private final Crud<Order> orderRepository;

    private static final Logger LOGGER = LoggerFactory.getLogger(OrderService.class);

    private OrderService(final Crud<Order> orderRepository) {
        this.orderRepository = orderRepository;
    }

    public static OrderService getInstance() {
        if (instance == null) {
            instance = new OrderService(HibernateOrderRepository.getInstance());
            LOGGER.info("OrderService was created");
        }
        return instance;
    }

    public Order create() {
        List<Car> listOfCars = Arrays.asList(CarService.getInstance().create((int) (Math.random() * 10 + 1)));
        Order order = new Order(listOfCars);
        listOfCars.forEach(c -> c.getOrders().add(order));
        orderRepository.save(order);
        LOGGER.info("Order was created: {}", order.getId());
        return order;
    }

    public List<Order> create(int numberOfOrders) {
        if (numberOfOrders < 0) {
            numberOfOrders = 0;
        }
        List<Order> listOfOrders = new LinkedList<>();
        for (int i = 0; i < numberOfOrders; i++) {
            Order order = create();
            listOfOrders.add(order);
        }
        return listOfOrders;
    }

    public void deleteById(String id) {
        orderRepository.deleteByUuid(id);
    }

    public Optional<Order> getByID(String id) {
        return orderRepository.getByUuid(id);
    }

    public void printByID(String id) {
        System.out.println(orderRepository.getByUuid(id));
    }

    public void printAll() {
        orderRepository.getAll().forEach(System.out::println);
    }

    public List<Order> getAll() {
        return orderRepository.getAll();
    }


}
