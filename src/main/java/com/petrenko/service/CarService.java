package com.petrenko.service;

import com.petrenko.model.*;
import com.petrenko.repository.CarListRepository;
import com.petrenko.repository.CarMapRepository;
import com.petrenko.repository.CarRepository;
import com.petrenko.util.RandomGenerator;
import org.junit.Assert;

import java.io.*;
import java.util.*;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toCollection;
import static java.util.stream.Collectors.toMap;

public class CarService {
    private static CarService instance;
    private final Random random = new Random();
    private final CarMapRepository carRepository;
    private String string;

    private CarService(final CarMapRepository carRepository) {
        this.carRepository = carRepository;
    }

    public static CarService getInstance() {
        if (instance == null) {
            instance = new CarService(CarMapRepository.getInstance());
        }
        return instance;
    }

    public static void check(final Car car) {
        if (car == null) {
            System.out.println("Car is null");
            return;
        }
        final boolean checkCount = car.getCount() > 0;
        final boolean checkPower = car.getEngine().getPower() > 200;
        if (checkCount && checkPower) {
            System.out.println("Car is fully ready for sell.");
            return;
        }
        if (!checkCount) {
            System.out.print("Count <= 0. ");
        }
        if (!checkPower) {
            System.out.println("Power <= 200.");
        }
    }

    public Car carFromFile(String file) {
        Map<String, String> map;
        if (file.endsWith(".xml")) {
            map = xmlToMap(file);
        } else if (file.endsWith(".json")) {
            map = jsonToMap(file);
        } else {
            throw new UserInputException("Unknown file format");
        }
        return mapToObject(map);
    }

    public Map<String, String> xmlToMap(String file) {
        InputStream input = input(file);
        if (input == null) {
            throw new UserInputException("File not exist");
        }
        return putMatchedPairToMapFromInput(input, "<(.*)>(.*)<.*");
    }

    public Map<String, String> jsonToMap(String file) {
        InputStream input = input(file);
        if (input == null) {
            throw new UserInputException("File not exist");
        }
        return putMatchedPairToMapFromInput(input, "\"(.*)\".*\"(.*)\".*");
    }

    private InputStream input(String file) {
        ClassLoader loader = Thread.currentThread().getContextClassLoader();
        return loader.getResourceAsStream(file);
    }

    private Map<String, String> putMatchedPairToMapFromInput(InputStream input, String regex) {
        Map<String, String> map = new HashMap<>();
        Scanner scanner = new Scanner(input);
        while (scanner.hasNext()) {
            String s = scanner.nextLine();
            Pattern pattern = Pattern.compile(regex);
            Matcher matcher = pattern.matcher(s);
            if (matcher.find()) {
                map.put(matcher.group(1), matcher.group(2));
            }
        }
        return map;
    }

    public List<String> findManufacturerByPrice(Car[] cars, int price) {
        System.out.println("Manufacturers of cars more expensive than:" + price);
        return Arrays.stream(cars)
                .filter(c -> c != null)
                .filter(c -> c.getPrice() > price)
                .map(Car::getManufacturer)
                .peek(System.out::println)
                .collect(toCollection(LinkedList::new));
    }

    public int countSum(Car[] cars) {
        return Arrays.stream(cars)
                .filter(c -> c != null)
                .map(Car::getCount)
                .reduce(0, (subtotal, element) -> subtotal + element);
    }

    public Map<String, Type> mapToMap(Car[] cars) {
        return Arrays.stream(cars)
                .filter(c -> c != null)
                .sorted(Comparator.comparing(Car::getManufacturer))
                .collect(toMap((Car::getUuidOfCar), (Car::getType), (c1, c2) -> c1, LinkedHashMap::new));
    }

    public IntSummaryStatistics statistic(Car[] cars) {
        return Arrays.stream(cars)
                .filter(c -> c != null)
                .mapToInt(Car::getPrice)
                .summaryStatistics();
    }

    public boolean priceCheck(Car[] cars, int price) {
        Predicate<Integer> predicate = p -> p > price;
        return Arrays.stream(cars)
                .filter(c -> c != null)
                .map(Car::getPrice)
                .allMatch(predicate);
    }

    public Car mapToObject(Map<String, String> mapByFile) {
        if (mapByFile == null) {
            throw new NullPointerException("Map not exist");
        }

        final Function<Map<String, String>, Car> function = m -> {
            if (m.get("type").equals("CAR")) {
                PassengerCar passengerCar = new PassengerCar();
                passengerCar.setEngine(new Engine(Type.CAR));
                setFieldsOfCar(m, passengerCar);
                return passengerCar;
            } else if (m.get("type").equals("TRUCK")) {
                Truck truck = new Truck();
                truck.setEngine(new Engine(Type.TRUCK));
                setFieldsOfCar(m, truck);
                return truck;
            } else {
                throw new NullPointerException("Type of car not exist");
            }
        };

        return function.apply(mapByFile);
    }

    private void setFieldsOfCar(Map<String, String> mapByFile, Car car) {
        Optional.ofNullable(mapByFile.get("manufacturer")).
                ifPresent(car::setManufacturer);
        Optional.ofNullable(mapByFile.get("power")).
                ifPresent(s -> car.getEngine().setPower(Integer.parseInt(s)));
        Optional.ofNullable(mapByFile.get("color")).
                ifPresent(s -> car.setColor(Color.valueOf(s)));
        Optional.ofNullable(mapByFile.get("count")).
                ifPresent(s -> car.setCount(Integer.parseInt(s)));
        Optional.ofNullable(mapByFile.get("price")).
                ifPresent(s -> car.setPrice(Integer.parseInt(s)));
        Optional.ofNullable(mapByFile.get("id")).
                ifPresent(car::setUuidOfCar);
        Optional.ofNullable(mapByFile.get("passengerCount")).
                ifPresent(s -> ((PassengerCar) car).setPassengerCount(Integer.parseInt(s)));
        Optional.ofNullable(mapByFile.get("loadCapacity")).
                ifPresent(s -> ((Truck) car).setLoadCapacity(Integer.parseInt(s)));
    }

    public Map<Color, Long> innerList(List<List<Car>> carsLists, int price) {
        return carsLists.stream()
                .flatMap(l -> l.stream())
                .filter(c -> c != null)
                .sorted(Comparator.comparing(c -> c.getColor().toString()))
                .peek(c -> {
                    System.out.println(c.getColor());
                    System.out.println(c.getUuidOfCar());
                })
                .filter(c -> c.getPrice() > price)
                .collect(Collectors.groupingBy(Car::getColor, Collectors.counting()));
    }

    public Map<String, Integer> mapManufacturerCount(Car[] cars) {
        Map<String, Integer> map = new HashMap<>();
        for (int i = 0; i < cars.length; i++) {
            String carManufacturer = cars[i].getManufacturer();
            Integer carCount = cars[i].getCount();
            if (map.containsKey(carManufacturer)) {
                carCount += map.get(carManufacturer);
            }
            map.put(carManufacturer, carCount);
        }
        return map;
    }

    public Map<Integer, List<Car>> mapPowerCar(Car[] cars) {
        Map<Integer, List<Car>> map = new HashMap<>();
        for (int i = 0; i < cars.length; i++) {
            Integer powerOfEngine = cars[i].getEngine().getPower();
            List<Car> listCar;
            if (map.containsKey(powerOfEngine)) {
                listCar = map.get(powerOfEngine);
            } else {
                listCar = new ArrayList<>();
            }
            listCar.add(cars[i]);
            map.put(powerOfEngine, listCar);
        }
        return map;
    }

    public void printManufacturerAndCount(final Car car) {
        Optional.ofNullable(car)
                .ifPresent(c -> {
                    System.out.printf("Manufacturer: %s. Count: %d.%n",
                            c.getManufacturer(), c.getCount());
                });
    }

    public void printColor(final Car car) {
        final Car checkedCar = Optional.ofNullable(car)
                .orElse(create());
        System.out.println("Color of car: " + checkedCar.getColor());
    }

    public void checkCount(final Car car) {
        final Car carOptional = Optional.ofNullable(car)
                .filter(c -> {
                    return c.getCount() > 10;
                })
                .orElseThrow(() -> new UserInputException("Count <= 10"));
        printManufacturerAndCount(carOptional);
    }

    public void printEngineInfo(final Car car) {
        final Car newCarOptional = Optional.ofNullable(car).orElseGet(() -> {
            System.out.println("Created new random car");
            return create();
        });
        Optional.of(newCarOptional)
                .map(c -> {
                    return c.getEngine().getPower();
                })
                .ifPresent(power -> {
                    System.out.println("Power of engine: " + power);
                });
    }

    public void printInfo(final Car car) {
        Optional.ofNullable(car).ifPresentOrElse(
                c -> {
                    print(c);
                },
                () -> {
                    final Car newCar = create();
                    print(newCar);
                }
        );
    }

    public boolean carEquals(final Car car1, final Car car2) {
        if (car1 == null || car2 == null) {
            return false;
        }
        if (carIsEqualsByType(car1, car2)) {
            return carTwoStepEquals(car1, car2);
        }
        return false;
    }

    private boolean carIsEqualsByType(final Car car1, final Car car2) {
        return car1.getEngine().getType().equals(car2.getEngine().getType());
    }

    private boolean carTwoStepEquals(final Car car1, final Car car2) {
        if (carIsEqualsByHashCode(car1, car2)) {
            return carIsEqualsByUuid(car1, car2);
        }
        return false;
    }

    private boolean carIsEqualsByHashCode(final Car car1, final Car car2) {
        return car1.hashCode() == car2.hashCode();
    }

    private boolean carIsEqualsByUuid(final Car car1, final Car car2) {
        return car1.getUuidOfCar().equals(car2.getUuidOfCar());
    }

    public Car create(final Type type) {
        Car car;
        if (type == null) {
            return null;
        }
        if (type.equals(Type.CAR)) {
            car = new PassengerCar(randomText(5), new Engine(type), Color.randomColor());
        } else if (type.equals(Type.TRUCK)) {
            car = new Truck(randomText(5), new Engine(type), Color.randomColor());
        } else {
            car = null;
        }
        carRepository.save(car);
        return car;
    }

    public int create(final RandomGenerator randomGenerator) {
        final int numberCars = randomGenerator.randomGenerator();
        if (numberCars <= 0 || numberCars > 10) {
            return -1;
        }
        final Car[] cars = create(numberCars);
        printAll(cars);
        System.out.println("Number of created cars: " + numberCars);
        return numberCars;
    }

    public Car create() {
        final Car car = create(Type.randomType());
        return car;
    }

    public Car[] create(final int numberOfCars) {
        if (numberOfCars < 0) {
            return new Car[0];
        }
        Car[] cars = new Car[numberOfCars];
        for (int i = 0; i < numberOfCars; i++) {
            cars[i] = create();
        }
        return cars;
    }

//    public void insert(final Car car, final int indexInsertCar) {
//        carRepository.insert(car, indexInsertCar);
//    }
//
//    public void insert(final int indexInsertCar) {
//        carRepository.insert(create(), indexInsertCar);
//    }

    public void print(final Car car) {
        if (car == null) {
            return;
        }
        if (car.getManufacturer() != null) {
            System.out.print("Manufacturer: " + car.getManufacturer() + ". ");
        }
        if (car.getEngine() != null) {
            System.out.print("Engine: type - " + car.getEngine().getType() +
                    ", power - " + car.getEngine().getPower() + ". ");
        }
        if (car.getColor() != null) {
            System.out.print("Color: " + car.getColor() + ". ");
        }
        if (car.getCount() >= 0) {
            System.out.print("Count: " + car.getCount() + ". ");
        }
        if (car.getPrice() >= 0) {
            System.out.print("Price: " + car.getPrice() + ". ");
        }
        if (car.getType() == Type.CAR && ((PassengerCar) car).getPassengerCount() != 0) {
            System.out.print("Passenger count: " +
                    ((PassengerCar) car).getPassengerCount() + ". ");
        }
        if (car.getType() == Type.TRUCK && ((Truck) car).getLoadCapacity() != 0) {
            System.out.print("Load capacity: " + ((Truck) car).getLoadCapacity() + ". ");
        }
        if (car.getUuidOfCar() != null) {
            System.out.print("UUID: " + car.getUuidOfCar() + ". ");
        }
        System.out.println();
    }

    public Optional<Car> getByUuid(final String uuidOfCar) {
        return carRepository.getByUuid(uuidOfCar);
    }

    public void printByUuid(final String uuidOfCar) {
        carRepository.getByUuid(uuidOfCar).ifPresentOrElse(c ->
                print(c),
                () -> System.out.println("Car with ID \"" + uuidOfCar + "\" not found")
        );

    }

    public void printAll() {
        for (Car car : carRepository.getAll()) {
            print(car);
        }
    }

    public void printAll(final Car[] cars) {
        if (cars == null) {
            return;
        }
        for (Car car : cars) {
            print(car);
        }
    }

//    public void updateColor(final String uuidOfCar, final Color color) {
//        carRepository.updateColor(uuidOfCar, color);
//    }
//
//    public void updateColorRandom(final String uuidOfCar) {
//        carRepository.updateColorRandom(uuidOfCar);
//    }

//    public void updatePrice(final String uuidOfCar, final int price) {
//        if (price < 0) {
//            return;
//        }
//        carRepository.updatePrice(uuidOfCar, price);
//    }

    public void deleteByUuid(final String uuidOfCar) {
        carRepository.deleteByUuid(uuidOfCar);
    }

    private String randomText(final int lengthRandomText) {
        String abc = "abcdefghijklmnopqrstuvwxyz";
        StringBuilder sb = new StringBuilder("");
        for (int i = 0; i < lengthRandomText; i++) {
            sb.append(abc.charAt(random.nextInt(abc.length() - 1)));
        }
        return sb.toString();
    }
}
