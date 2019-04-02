CREATE DATABASE the_big_project;

CREATE SEQUENCE recipes_ids;
CREATE TABLE recipes(id INT PRIMARY KEY DEFAULT NEXTVAL('recipes_ids'), name VARCHAR(32), preparation TEXT, preparation_time INTEGER, image VARCHAR(32), smart_points INTEGER);

CREATE SEQUENCE users_ids;
CREATE TABLE users(id INT PRIMARY KEY DEFAULT NEXTVAL('users_ids'), username VARCHAR(10), password VARCHAR(10), email VARCHAR(32), active boolean NOT NULL);

CREATE SEQUENCE ingredients_ids;
CREATE TABLE ingredients(id INT PRIMARY KEY DEFAULT NEXTVAL('ingredients_ids'), name VARCHAR(32), measurement_unit VARCHAR(32), unitFactorTransformation INTEGER);

CREATE SEQUENCE fridges_ids;
CREATE TABLE fridges(id INT PRIMARY KEY DEFAULT NEXTVAL('fridges_ids'), user_id INT REFERENCES users(id));

CREATE SEQUENCE recipe_ingredients_ids;
CREATE TABLE recipe_ingredients(id INT PRIMARY KEY DEFAULT NEXTVAL('recipe_ingredients_ids'), recipe_id INT REFERENCES recipes(id), name VARCHAR(32), measurement_unit VARCHAR(32), quantity DOUBLE PRECISION);

CREATE SEQUENCE fridge_ingredients_ids;
CREATE TABLE fridge_ingredients(id INT PRIMARY KEY DEFAULT NEXTVAL('fridge_ingredients_ids'), fridge_id INT REFERENCES fridges(id), name VARCHAR(32), measurement_unit VARCHAR(32), quantity DOUBLE PRECISION);

CREATE SEQUENCE recipe_types_ids;
CREATE TABLE recipe_types (id INT PRIMARY KEY DEFAULT NEXTVAL('recipe_types_ids'), type VARCHAR(32));

CREATE SEQUENCE recipes_with_types_ids;
CREATE TABLE recipes_with_types (id INT PRIMARY KEY DEFAULT NEXTVAL('recipes_with_types_ids'), recipe_id INT REFERENCES recipes(id), type_id INT REFERENCES recipe_types(id));

CREATE SEQUENCE role_id_seq;
CREATE TABLE role (id numeric NOT NULL DEFAULT nextval('role_id_seq'::regclass) PRIMARY KEY,name varchar(255) DEFAULT NULL);

CREATE TABLE user_role (user_id numeric not null, role_id numeric not null, FOREIGN KEY ("user_id") REFERENCES app_user ("id"),FOREIGN KEY ("role_id") REFERENCES role ("id"));


DROP DATABASE the_big_project;


DROP TABLE recipes, users, ingredients, fridges, recipe_ingredients, fridge_ingredients, recipe_types, recipes_with_types;
DROP SEQUENCE recipes_ids, users_ids, ingredients_ids, fridges_ids, recipe_ingredients_ids, fridge_ingredients_ids, recipe_types_ids, recipes_with_types_ids;
