package com.petrenko.service;

import com.petrenko.model.Car;
import com.petrenko.model.Order;
import com.petrenko.repository.Crud;
import com.petrenko.repository.JdbcOrderRepository;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class OrderService {
    private static OrderService instance;
    private final Crud<Order> orderRepository;

    private OrderService(final Crud<Order> orderRepository) {
        this.orderRepository = orderRepository;
    }

    public static OrderService getInstance() {
        if (instance == null) {
            instance = new OrderService(JdbcOrderRepository.getInstance());
        }
        return instance;
    }

    public Order create() {
        List<Car> listOfCars = Arrays.asList(CarService.getInstance().create((int) (Math.random() * 10)));
        Order order = new Order(listOfCars);
        orderRepository.save(order);
        return order;
    }

    public List<Order> create(int numberOfOrders) {
        if (numberOfOrders < 0) {
            numberOfOrders = 0;
        }
        List<Order> listOfOrders = new LinkedList<>();
        for (int i = 0; i < numberOfOrders; i++) {
            List<Car> listOfCars = Arrays.asList(CarService.getInstance().create((int) (Math.random() * 10)));
            Order order = new Order(listOfCars);
            orderRepository.save(order);
            listOfOrders.add(order);
        }
        return listOfOrders;
    }

    public void deleteById(String id) {
        orderRepository.deleteByUuid(id);
    }

    public void printByID(String id) {
        System.out.println(orderRepository.getByUuid(id));
    }

    public void printAll() {
        orderRepository.getAll().forEach(System.out::println);
    }

}
