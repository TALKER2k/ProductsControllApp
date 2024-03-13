CREATE TABLE users
(
    id        SERIAL PRIMARY KEY,
    user_name VARCHAR(25),
    password  VARCHAR(200)
);

INSERT INTO users VALUES (1, 'User', '$2a$10$WeVR/Cd68PiLvodrehbqgO67mNKS7JquORFUTT/1cKcGXYQgUnHCK');

CREATE TABLE roles
(
    id        SERIAL PRIMARY KEY,
    role_name VARCHAR(10)
);

INSERT INTO roles VALUES (0, 'ADMIN'),
                         (1, 'USER');

CREATE TABLE user_roles
(
    user_id INTEGER REFERENCES users (id),
    role_id INTEGER REFERENCES roles (id)
);

INSERT INTO user_roles VALUES (1, 0);

CREATE TABLE products
(
    id               SERIAL PRIMARY KEY,
    name_product     VARCHAR(255),
    measurement_unit VARCHAR(50),
    remainder        DOUBLE PRECISION
);

INSERT INTO products VALUES (1, 'Milk', 'ml', 10.5);

CREATE TABLE user_products
(
    id         SERIAL PRIMARY KEY,
    user_id    INTEGER REFERENCES users (id),
    product_id INTEGER REFERENCES products (id),
    remainder  DOUBLE PRECISION
);

CREATE TABLE history_products
(
    id          SERIAL PRIMARY KEY,
    collect     DOUBLE PRECISION,
    product_id  INTEGER REFERENCES products (id),
    user_id     INTEGER REFERENCES users (id),
    collected_at TIMESTAMP
);
