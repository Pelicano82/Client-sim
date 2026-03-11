-- Schema for TV Licence Payment System - Azure SQL Server Compatible

CREATE TABLE person (
    id BIGINT IDENTITY(1,1) PRIMARY KEY,
    first_name VARCHAR(100) NOT NULL,
    last_name VARCHAR(100) NOT NULL,
    full_name VARCHAR(200),
    address_line1 VARCHAR(255),
    city VARCHAR(100),
    postcode VARCHAR(20),
    country VARCHAR(100),
    email VARCHAR(255),
    phone VARCHAR(20),
    card_card_number VARCHAR(255)
);

CREATE TABLE card (
    card_number VARCHAR(255) PRIMARY KEY,
    card_holder VARCHAR(255),
    expiry_date VARCHAR(10),
    security_code VARCHAR(10),
    card_type VARCHAR(50)
);

CREATE TABLE licence (
    id BIGINT IDENTITY(1,1) PRIMARY KEY,
    licence_number VARCHAR(100) UNIQUE NOT NULL,
    fee NUMERIC(10,2),
    valid_until DATE,
    status VARCHAR(50),
    renewed BIT DEFAULT 0,
    person_id BIGINT NOT NULL,
    FOREIGN KEY (person_id) REFERENCES person(id)
);

CREATE TABLE fine (
    id BIGINT IDENTITY(1,1) PRIMARY KEY,
    amount NUMERIC(10,2),
    reason VARCHAR(255),
    status VARCHAR(50),
    licence_id BIGINT NOT NULL,
    FOREIGN KEY (licence_id) REFERENCES licence(id)
);

CREATE TABLE payment (
    id BIGINT IDENTITY(1,1) PRIMARY KEY,
    reference_number VARCHAR(50),
    amount NUMERIC(10,2),
    payment_method VARCHAR(100),
    status VARCHAR(50),
    payment_date DATETIME,
    transaction_id VARCHAR(255),
    person_id BIGINT,
    fine_id BIGINT,
    card_card_number VARCHAR(255),
    FOREIGN KEY (person_id) REFERENCES person(id),
    FOREIGN KEY (fine_id) REFERENCES fine(id),
    FOREIGN KEY (card_card_number) REFERENCES card(card_number)
);
