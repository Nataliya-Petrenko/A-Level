CREATE TABLE IF NOT EXISTS Engine (
id_engine VARCHAR NOT NULL PRIMARY KEY,
power INTEGER,
type_engine VARCHAR
);
CREATE TABLE IF NOT EXISTS Car (
id_car VARCHAR NOT NULL PRIMARY KEY,
manufacturer VARCHAR,
id_engine VARCHAR NOT NULL REFERENCES Engine(id_engine),
color VARCHAR,
count INTEGER,
price INTEGER
);
CREATE TABLE IF NOT EXISTS Passenger_car (
id_car VARCHAR NOT NULL PRIMARY KEY REFERENCES Car(id_car),
passenger_count INTEGER
);
CREATE TABLE IF NOT EXISTS Truck (
id_car VARCHAR NOT NULL PRIMARY KEY REFERENCES Car(id_car),
load_capacity INTEGER
);
CREATE TABLE IF NOT EXISTS OrderCars (
id_order VARCHAR NOT NULL PRIMARY KEY,
date TIMESTAMP
);
CREATE TABLE IF NOT EXISTS OrderCars_Car (
id_order VARCHAR NOT NULL REFERENCES OrderCars(id_order),
id_car VARCHAR NOT NULL REFERENCES Car(id_car)
);