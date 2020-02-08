CREATE TABLE machines (
    id SERIAL PRIMARY KEY,
    user_code_id BIGINT REFERENCES user_codes(id)
);

CREATE TABLE network_interfaces (
    id SERIAL PRIMARY KEY,
    machine_id BIGINT REFERENCES machines(id) NOT NULL,
    name VARCHAR(20) NOT NULL,
    description VARCHAR(1000),
    loopback BOOLEAN NOT NULL,
    primary_interface BOOLEAN NOT NULL
);

CREATE TABLE interface_address (
    id SERIAL PRIMARY KEY,
    network_interface_id BIGINT REFERENCES network_interfaces(id) NOT NULL,
    hostname VARCHAR(1000) NOT NULL,
    address VARCHAR(100) NOT NULL
);
