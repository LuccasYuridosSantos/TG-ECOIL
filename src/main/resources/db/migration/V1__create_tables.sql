-- V1__create_tables.sql

CREATE TABLE address
(
    id             SERIAL PRIMARY KEY,
    road           VARCHAR(255),
    municipality   VARCHAR(255),
    county         VARCHAR(255),
    state          VARCHAR(255),
    region         VARCHAR(255),
    postcode       VARCHAR(20),
    country        VARCHAR(100),
    city_district  VARCHAR(255),
    state_district VARCHAR(255),
    iso3166lvl4    VARCHAR(10),
    country_code   VARCHAR(10)
);

CREATE TABLE collection_point
(
    id         SERIAL PRIMARY KEY,
    city       VARCHAR(255),
    state      VARCHAR(255),
    latitude   VARCHAR(255),
    longitude  VARCHAR(255),
    name       VARCHAR(255),
    address_id BIGINT,
    CONSTRAINT fk_address
        FOREIGN KEY (address_id)
            REFERENCES address (id)
);

CREATE TABLE container
(
    id              SERIAL PRIMARY KEY,
    name            VARCHAR(255)     NOT NULL,
    description     VARCHAR(255),
    unit_of_measure VARCHAR(255)     NOT NULL,
    max_capacity    DOUBLE PRECISION NOT NULL CHECK (max_capacity > 0),
    image           VARCHAR(255)     NOT NULL
);

CREATE TABLE schedule
(
    id                SERIAL PRIMARY KEY,
    user_id           VARCHAR(255) NOT NULL,
    name              VARCHAR(255) NOT NULL,
    time              VARCHAR(8)   NOT NULL,
    location          VARCHAR(255) NOT NULL,
    description       VARCHAR(255),
    date              DATE         NOT NULL,
    collector_user_id VARCHAR(255),
    status            VARCHAR(255) NOT NULL,
    recipient_id      BIGINT,
    CONSTRAINT fk_recipient
        FOREIGN KEY (recipient_id)
            REFERENCES container (id)
);

CREATE TABLE user_claim
(
    id      SERIAL PRIMARY KEY,
    user_id VARCHAR(255) NOT NULL,
    admin   BOOLEAN DEFAULT FALSE
);