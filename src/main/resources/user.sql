CREATE DATABASE the_big_project;

CREATE SEQUENCE recipes_ids;
CREATE TABLE recipes(id INT PRIMARY KEY DEFAULT NEXTVAL('recipes_ids'), name VARCHAR(32), preparation TEXT, preparation_time INTEGER, image VARCHAR(32), smart_points INTEGER);

CREATE SEQUENCE users_ids;
CREATE TABLE users(id INT PRIMARY KEY DEFAULT NEXTVAL('users_ids'), username VARCHAR(10), password VARCHAR(10), email VARCHAR(32), active boolean NOT NULL);

CREATE SEQUENCE ingredients_ids;
CREATE TABLE ingredients(id INT PRIMARY KEY DEFAULT NEXTVAL('ingredients_ids'), name VARCHAR(32), picture_url VARCHAR(32));

CREATE SEQUENCE fridges_ids;
CREATE TABLE fridges(id INT PRIMARY KEY DEFAULT NEXTVAL('fridges_ids'), user_id INT REFERENCES users(id));

CREATE SEQUENCE recipe_ingredients_ids;
CREATE TABLE recipe_ingredients(id INT PRIMARY KEY DEFAULT NEXTVAL('recipe_ingredients_ids'), recipe_id INT REFERENCES recipes(id), ingredient_id INT REFERENCES ingredients(id));

CREATE SEQUENCE fridge_ingredients_ids;
CREATE TABLE fridge_ingredients(id INT PRIMARY KEY DEFAULT NEXTVAL('fridge_ingredients_ids'), fridge_id INT REFERENCES fridges(id), ingredient_id INT REFERENCES ingredients(id));

CREATE SEQUENCE roles_id_seq;
CREATE TABLE roles (id numeric NOT NULL DEFAULT nextval('role_id_seq'::regclass) PRIMARY KEY, name varchar(255) DEFAULT NULL);

CREATE SEQUENCE user_roles_id_seq;
CREATE TABLE user_roles (id numeric NOT NULL DEFAULT nextval('user_roles_id_seq'::regclass) PRIMARY KEY, user_id INT not null, role_id INT not null, FOREIGN KEY ("user_id") REFERENCES users ("id"),FOREIGN KEY ("role_id") REFERENCES roles ("id"));


DROP DATABASE the_big_project;


DROP TABLE recipes, users, ingredients, fridges, recipe_ingredients, fridge_ingredients, roles, user_roles;
DROP SEQUENCE recipes_ids, users_ids, ingredients_ids, fridges_ids, recipe_ingredients_ids, fridge_ingredients_ids, roles_id_seq, user_roles_id_seq;
