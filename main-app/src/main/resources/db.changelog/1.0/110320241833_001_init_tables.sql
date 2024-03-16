CREATE TABLE users
(
    id        SERIAL PRIMARY KEY,
    user_name VARCHAR(25) UNIQUE,
    email VARCHAR(255) UNIQUE,
    password  VARCHAR(200),
    is_active BOOLEAN DEFAULT TRUE
);

CREATE TABLE roles
(
    id        SERIAL PRIMARY KEY,
    role_name VARCHAR(10)
);


CREATE TABLE user_roles
(
    user_id INTEGER REFERENCES users (id),
    role_id INTEGER REFERENCES roles (id)
);

CREATE TABLE products
(
    id               SERIAL PRIMARY KEY,
    name_product     VARCHAR(255),
    measurement_unit VARCHAR(50),
    pick        DOUBLE PRECISION CHECK (pick >= 0)
);

CREATE TABLE user_target
(
    id         SERIAL PRIMARY KEY,
    user_id    INTEGER REFERENCES users (id),
    product_id INTEGER REFERENCES products (id),
    pick  DOUBLE PRECISION CHECK (pick >= 0)
);

CREATE TABLE history_pick_products
(
    id          SERIAL PRIMARY KEY,
    pick     DOUBLE PRECISION,
    product_id  INTEGER REFERENCES products (id),
    user_id     INTEGER REFERENCES users (id),
    pick_at TIMESTAMP
);
