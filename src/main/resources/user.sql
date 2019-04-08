CREATE DATABASE the_big_project;

CREATE SEQUENCE recipes_ids;
CREATE TABLE recipes(id INT PRIMARY KEY DEFAULT NEXTVAL('recipes_ids'), name VARCHAR(32), preparation TEXT, preparation_time INTEGER, image VARCHAR(32), smart_points INTEGER);

CREATE SEQUENCE users_ids;
CREATE TABLE users(id INT PRIMARY KEY DEFAULT NEXTVAL('users_ids'), full_name VARCHAR(32), username VARCHAR(10), password VARCHAR(100), email VARCHAR(32), active boolean NOT NULL);

CREATE SEQUENCE ingredients_ids;
CREATE TABLE ingredients(id INT PRIMARY KEY DEFAULT NEXTVAL('ingredients_ids'), name VARCHAR(32), picture_url VARCHAR(32));

CREATE SEQUENCE fridges_ids;
CREATE TABLE fridges(id INT PRIMARY KEY DEFAULT NEXTVAL('fridges_ids'), user_id INT REFERENCES users(id));

CREATE SEQUENCE recipe_ingredients_ids;
CREATE TABLE recipe_ingredients(id INT PRIMARY KEY DEFAULT NEXTVAL('recipe_ingredients_ids'), recipe_id INT REFERENCES recipes(id), ingredient_id INT REFERENCES ingredients(id));

CREATE SEQUENCE fridge_ingredients_ids;
CREATE TABLE fridge_ingredients(id INT PRIMARY KEY DEFAULT NEXTVAL('fridge_ingredients_ids'), fridge_id INT REFERENCES fridges(id), ingredient_id INT REFERENCES ingredients(id));

CREATE SEQUENCE roles_id_seq;
CREATE TABLE roles (id numeric NOT NULL DEFAULT nextval('roles_id_seq'::regclass) PRIMARY KEY, name varchar(255) DEFAULT NULL);

CREATE SEQUENCE user_roles_id_seq;
CREATE TABLE user_roles (id numeric NOT NULL DEFAULT nextval('user_roles_id_seq'::regclass) PRIMARY KEY, user_id INT not null, role_id INT not null, FOREIGN KEY ("user_id") REFERENCES users ("id"),FOREIGN KEY ("role_id") REFERENCES roles ("id"));

INSERT INTO users (full_name, username, password, email, active) VALUES ('FirstName LastName', 'admin', '$2a$10$9epmlLPfrwYO10cw9kw5AOcDH.G9Tw3I8QAytL8ZF1PRNp/RDCGVe', 'admin@gmail.com', 'true');
INSERT INTO users (full_name, username, password, email, active) VALUES ('FirstName LastName', 'user', '$2a$10$9epmlLPfrwYO10cw9kw5AOcDH.G9Tw3I8QAytL8ZF1PRNp/RDCGVe', 'user@gmail.com', 'true');
INSERT INTO roles(name) VALUES ('ADMIN'), ('USER');
INSERT INTO user_roles (user_id, role_id) VALUES ('1', '1');
INSERT INTO user_roles (user_id, role_id) VALUES ('2', '2');

------------------------------------------------------------------------------------------------------------------------

DROP DATABASE the_big_project;


DROP TABLE recipes, users, ingredients, fridges, recipe_ingredients, fridge_ingredients, roles, user_roles CASCADE;
DROP SEQUENCE recipes_ids, users_ids, ingredients_ids, fridges_ids, recipe_ingredients_ids, fridge_ingredients_ids, roles_id_seq, user_roles_id_seq CASCADE;
