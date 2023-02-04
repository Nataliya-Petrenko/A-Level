package com.petrenko.service;

import com.petrenko.model.*;
import com.petrenko.repository.CarRepository;
import com.petrenko.util.RandomGenerator;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.Mockito;

class CarServiceTest {
    private CarService target;
    private CarRepository repository;
    private RandomGenerator randomGenerator;
    private Car car;

/*    @BeforeEach
    void setUp() {
        repository = Mockito.mock(CarRepository.class);
        randomGenerator = Mockito.mock(RandomGenerator.class);
        target = new CarService(repository);
        car = new PassengerCar("abc", new Engine(Type.CAR), Color.BLUE);
    }

    @Test
    public void check_Test() {
        car.setCount(1);
        car.getEngine().setPower(201);
        Assertions.assertDoesNotThrow(() -> CarService.check(car));
    }

    @Test
    public void check_CarIsNull() {
        Assertions.assertDoesNotThrow(() -> CarService.check(null));
    }

    @Test
    public void check_CheckCountIsZero() {
        car.setCount(0);
        car.getEngine().setPower(201);
        Assertions.assertDoesNotThrow(() -> CarService.check(car));
    }

    @Test
    public void check_CheckPowerIsZero() {
        car.setCount(5);
        car.getEngine().setPower(0);
        Assertions.assertDoesNotThrow(() -> CarService.check(car));
    }

    @Test
    public void check_CheckCountIsNegative() {
        car.setCount(-20);
        car.getEngine().setPower(201);
        Assertions.assertDoesNotThrow(() -> CarService.check(car));
    }

    @Test
    public void check_CheckPowerIsNegative() {
        car.setCount(5);
        car.getEngine().setPower(-200);
        Assertions.assertDoesNotThrow(() -> CarService.check(car));
    }

    @Test
    public void createRandomGenerator_Test() {
        Mockito.when(randomGenerator.randomGenerator()).thenReturn(5);
        final int expected = 5;
        final int actual = target.create(randomGenerator);
        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void createRandomGenerator_Zero() {
        Mockito.when(randomGenerator.randomGenerator()).thenReturn(0);
        final int expected = -1;
        final int actual = target.create(randomGenerator);
        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void createRandomGenerator_Negative() {
        Mockito.when(randomGenerator.randomGenerator()).thenReturn(-10);
        final int expected = -1;
        final int actual = target.create(randomGenerator);
        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void createRandomGenerator_More10() {
        Mockito.when(randomGenerator.randomGenerator()).thenReturn(20);
        final int expected = -1;
        final int actual = target.create(randomGenerator);
        Assertions.assertEquals(expected, actual);
    }
    @Test
    public void create_PassengerCar() {
        final Car car = target.create(Type.CAR);
        Assertions.assertNotNull(car);
        Assertions.assertNotNull(car.getUuidOfCar());
    }

    @Test
    public void create_Truck() {
        final Car car = target.create(Type.TRUCK);
        Assertions.assertNotNull(car);
        Assertions.assertNotNull(car.getUuidOfCar());
    }
    @Test
    public void create_Test() {
        final Car car = target.create();
        Assertions.assertNotNull(car);
        Assertions.assertNotNull(car.getUuidOfCar());
    }

    @Test
    public void createNumberOfCars_Test() {
        final int numberOfCars = 5;
        final Car[] cars = target.create(numberOfCars);
        Assertions.assertNotNull(cars);
    }

    @Test
    public void createNumberOfCars_Zero() {
        final int numberOfCars = 0;
        final Car[] cars = target.create(numberOfCars);
        Assertions.assertNotNull(cars);
    }

    @Test
    public void createNumberOfCars_Negative() {
        final int numberOfCars = -1;
        final Car[] cars = target.create(numberOfCars);
        Assertions.assertNotNull(cars);
    }

    @Test
    public void insertCarIndexInsertCar_Test() {
        final int indexInsertCar = 5;
        Assertions.assertDoesNotThrow(() -> target.insert(car, indexInsertCar));
    }

    @Test
    public void insertCarIndexInsertCar_IndexInsertCarIsBig() {
        final int indexInsertCar = 5555;
        Assertions.assertDoesNotThrow(() -> target.insert(car, indexInsertCar));
    }

    @Test
    public void insertCarIndexInsertCar_CarIsNull() {
        final PassengerCar car = null;
        final int indexInsertCar = 5;
        Assertions.assertDoesNotThrow(() -> target.insert(car, indexInsertCar));
    }

    @Test
    public void insertCarIndexInsertCar_IndexInsertCarIsNegative() {
        int indexInsertCar = -5;
        Assertions.assertDoesNotThrow(() -> target.insert(car, indexInsertCar));
    }

    @Test
    public void insertCarIndexInsertCar_IndexInsertCarIsZero() {
        int indexInsertCar = 0;
        Assertions.assertDoesNotThrow(() -> target.insert(car, indexInsertCar));
    }

    @Test
    public void insertIndexInsertCar_Test() {
        int indexInsertCar = 5;
        Assertions.assertDoesNotThrow(() -> target.insert(indexInsertCar));
    }

    @Test
    public void insertIndexInsertCar_IndexInsertCarIsBig() {
        int indexInsertCar = 5555;
        Assertions.assertDoesNotThrow(() -> target.insert(indexInsertCar));
    }

    @Test
    public void insertIndexInsertCar_IndexInsertCarIsZero() {
        int indexInsertCar = 0;
        Assertions.assertDoesNotThrow(() -> target.insert(indexInsertCar));
    }

    @Test
    public void insertIndexInsertCar_IndexInsertCarIsNegative() {
        int indexInsertCar = -5;
        Assertions.assertDoesNotThrow(() -> target.insert(indexInsertCar));
    }

    @Test
    public void print_Test() {
        Assertions.assertDoesNotThrow(() -> target.print(car));
    }

    @Test
    public void print_CarIsNull() {
        final PassengerCar car = null;
        Assertions.assertDoesNotThrow(() -> target.print(car));
    }

    @Test
    public void print_SomethingFieldsOfCarIsNull() {
        final PassengerCar car = new PassengerCar();
        Assertions.assertDoesNotThrow(() -> target.print(car));
    }

    @Test
    public void printByUuid_Test() {
        final String uuidOfCar = "123";
        Mockito.when(repository.getByUuid(uuidOfCar)).thenReturn(car);
        Assertions.assertDoesNotThrow(() -> target.printByUuid(uuidOfCar));
    }

    @Test
    public void printByUuid_CarIsNull() {
        final String uuidOfCar = "123";
        Mockito.when(repository.getByUuid(uuidOfCar)).thenReturn(null);
        Assertions.assertDoesNotThrow(() -> target.printByUuid(uuidOfCar));
    }

    @Test
    public void printByUuid_IdIsNull() {
        final String uuidOfCar = null;
        Assertions.assertDoesNotThrow(() -> target.printByUuid(uuidOfCar));
    }

    @Test
    public void printAll_Test() {
        final Car[] cars = target.create(5);
        Mockito.when(repository.getAll()).thenReturn(cars);
        Assertions.assertDoesNotThrow(() -> target.printAll());
    }

    @Test
    public void printAllRepository_RepositoryIsEmpty() {
        final PassengerCar[] cars = new PassengerCar[0];
        Mockito.when(repository.getAll()).thenReturn(cars);
        Assertions.assertDoesNotThrow(() -> target.printAll());
    }

    @Test
    public void printAllArray_Test() {
        final Car[] cars = target.create(5);
        Assertions.assertDoesNotThrow(() -> target.printAll(cars));
    }

    @Test
    public void printAllArray_ArrayIsEmpty() {
        final PassengerCar[] cars = new PassengerCar[0];
        Assertions.assertDoesNotThrow(() -> target.printAll(cars));
    }

    @Test
    public void printAllArray_ArrayIsNull() {
        final PassengerCar[] cars = null;
        Assertions.assertDoesNotThrow(() -> target.printAll(cars));
    }

    @Test
    public void updateColor_Test() {
        final String uuidOfCar = car.getUuidOfCar();
        final Color color = Color.BLUE;
        Assertions.assertDoesNotThrow(() -> target.updateColor(uuidOfCar, color));
    }

    @Test
    public void updateColor_IdIsNull() {
        final String uuidOfCar = null;
        final Color color = Color.BLUE;
        Assertions.assertDoesNotThrow(() -> target.updateColor(uuidOfCar, color));
    }

    @Test
    public void updateColor_ColorIsNull() {
        final String uuidOfCar = car.getUuidOfCar();
        final Color color = null;
        Assertions.assertDoesNotThrow(() -> target.updateColor(uuidOfCar, color));
    }

    @Test
    public void updateColorRandom_Test() {
        final String uuidOfCar = car.getUuidOfCar();
        Assertions.assertDoesNotThrow(() -> target.updateColorRandom(uuidOfCar));
    }

    @Test
    public void updateColorRandom_IdIsNull() {
        final String uuidOfCar = null;
        Assertions.assertDoesNotThrow(() -> target.updateColorRandom(uuidOfCar));
    }

    @Test
    public void updatePrice_Test() {
        final String uuidOfCar = car.getUuidOfCar();
        final int price = 100_000;
        Assertions.assertDoesNotThrow(() -> target.updatePrice(uuidOfCar, price));
    }

    @Test
    public void updatePrice_IdIsNull() {
        final String uuidOfCar = car.getUuidOfCar();
        final int price = 100_000;
        Assertions.assertDoesNotThrow(() -> target.updatePrice(uuidOfCar, price));
    }

    @Test
    public void updatePrice_PriceIsNegative() {
        final String uuidOfCar = car.getUuidOfCar();
        final int price = -100_000;
        Assertions.assertDoesNotThrow(() -> target.updatePrice(uuidOfCar, price));
    }

    @Test
    public void deleteByUuid_Test() {
        final String uuidOfCar = car.getUuidOfCar();
        Assertions.assertDoesNotThrow(() -> target.deleteByUuid(uuidOfCar));
    }

    @Test
    public void deleteByUuid_IdIsNull() {
        final String uuidOfCar = null;
        Assertions.assertDoesNotThrow(() -> target.deleteByUuid(uuidOfCar));
    }*/
}