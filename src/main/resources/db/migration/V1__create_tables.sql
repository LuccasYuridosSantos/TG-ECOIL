-- V1__create_tables.sql

CREATE TABLE collection_point (
    id SERIAL PRIMARY KEY,
    city VARCHAR(255),
    state VARCHAR(255),
    latitude VARCHAR(255),
    longitude VARCHAR(255),
    name VARCHAR(255)
);

CREATE TABLE container (
    id SERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    description VARCHAR(255),
    unit_of_measure VARCHAR(255) NOT NULL,
    max_capacity DOUBLE PRECISION NOT NULL CHECK (max_capacity > 0),
    image VARCHAR(255) NOT NULL
);

CREATE TABLE schedule (
    id SERIAL PRIMARY KEY,
    user_id VARCHAR(255) NOT NULL,
    name VARCHAR(255) NOT NULL,
    time VARCHAR(8) NOT NULL,
    location VARCHAR(255) NOT NULL,
    description VARCHAR(255),
    date DATE NOT NULL,
    completed BOOLEAN
);