CREATE TABLE users
(
    id        SERIAL PRIMARY KEY,
    user_name VARCHAR(25) UNIQUE,
    email VARCHAR(255) UNIQUE,
    password  VARCHAR(200),
    is_active BOOLEAN DEFAULT TRUE
);

CREATE INDEX users_index ON users(id, user_name);

INSERT INTO users VALUES (1, 'Admin', 'admin@mail.com', '$2a$10$WeVR/Cd68PiLvodrehbqgO67mNKS7JquORFUTT/1cKcGXYQgUnHCK');
INSERT INTO users VALUES (2, 'User', 'user@mail.com', '$2a$10$.HEmz6f60zGy6Pvuzmwsfujrur0iFajlFCsrf1KGRBi/ep7pRjfCa');

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
    remainder        DOUBLE PRECISION CHECK (remainder >= 0)
);

CREATE INDEX products_index ON products(id, name_product);

INSERT INTO products VALUES (1, 'Milk', 'ml', 10.5);

CREATE TABLE user_target
(
    id         SERIAL PRIMARY KEY,
    user_id    INTEGER REFERENCES users (id),
    product_id INTEGER REFERENCES products (id),
    remainder  DOUBLE PRECISION CHECK (remainder >= 0)
);

INSERT INTO user_target VALUES (1,1,1,10.5);
INSERT INTO user_target VALUES (2,2,1,10.5);

CREATE TABLE history_pick_products
(
    id          SERIAL PRIMARY KEY,
    pick     DOUBLE PRECISION,
    product_id  INTEGER REFERENCES products (id),
    user_id     INTEGER REFERENCES users (id),
    pick_at TIMESTAMP
);
