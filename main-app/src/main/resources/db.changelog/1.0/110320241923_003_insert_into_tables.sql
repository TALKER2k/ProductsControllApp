INSERT INTO users VALUES (1, 'Admin', 'admin@mail.com', '$2a$10$WeVR/Cd68PiLvodrehbqgO67mNKS7JquORFUTT/1cKcGXYQgUnHCK');
INSERT INTO users VALUES (2, 'User', 'user@mail.com', '$2a$10$.HEmz6f60zGy6Pvuzmwsfujrur0iFajlFCsrf1KGRBi/ep7pRjfCa');

INSERT INTO roles VALUES (0, 'ADMIN'),
                         (1, 'USER');

INSERT INTO user_roles VALUES (1, 0);

INSERT INTO products VALUES (1, 'Milk', 'ml', 10.5);

INSERT INTO user_target VALUES (1,1,1,10.5);
INSERT INTO user_target VALUES (2,2,1,10.5);