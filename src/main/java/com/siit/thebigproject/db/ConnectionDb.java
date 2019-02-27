package com.siit.thebigproject.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class ConnectionDb {

    public void setup() throws DbException, SQLException {
        setupDb();
        setupTables();
    }

    public void setupDb() throws DbException, SQLException {
        ConnectionDb db = new ConnectionDb();
        try (
                Connection connection = db.connect()) {
            Statement statement = connection.createStatement();
            statement.execute("CREATE DATABASE the_big_project;");
        }
    }

    public void setupTables() throws DbException, SQLException {
        ConnectionDb db = new ConnectionDb();

        try (Connection connection = db.connectToMyDb()) {
            StringBuilder builder = new StringBuilder();

            builder.append("CREATE SEQUENCE recipes_ids;");
            builder.append("CREATE TABLE recipes(id INT PRIMARY KEY DEFAULT NEXTVAL('recipes_ids'), " +
                    "name VARCHAR(32), preparation TEXT, calories INTEGER, recipe_type VARCHAR(32));");

            builder.append("CREATE SEQUENCE users_ids;");
            builder.append("CREATE TABLE users(id INT PRIMARY KEY DEFAULT NEXTVAL('users_ids'), " +
                    "username VARCHAR(10), password VARCHAR(10), email VARCHAR(32), desired_calories INTEGER);");

            builder.append("CREATE SEQUENCE ingredients_ids;");
            builder.append("CREATE TABLE ingredients(id INT PRIMARY KEY DEFAULT NEXTVAL('ingredients_ids'), " +
                    "name VARCHAR(32), measurement_unit VARCHAR(32));");

            builder.append("CREATE SEQUENCE fridges_ids;");
            builder.append("CREATE TABLE fridges(id INT PRIMARY KEY DEFAULT NEXTVAL('fridges_ids'), " +
                    "user_id INT REFERENCES users(id));");

            builder.append("CREATE SEQUENCE recipe_ingredients_ids;");
            builder.append("CREATE TABLE recipe_ingredients(id INT PRIMARY KEY DEFAULT NEXTVAL('recipe_ingredients_ids'), " +
                    " recipe_id INT REFERENCES recipes(id), ingredient_id INT REFERENCES ingredients(id), quantity DOUBLE PRECISION);");

            builder.append("CREATE SEQUENCE fridge_ingredients_ids;");
            builder.append("CREATE TABLE fridge_ingredients(id INT PRIMARY KEY DEFAULT NEXTVAL('fridge_ingredients_ids'), " +
                    " fridge_id INT REFERENCES fridges(id), ingredient_id INT REFERENCES ingredients(id), quantity DOUBLE PRECISION);");

            Statement statement = connection.createStatement();
            statement.execute(builder.toString());
        }
    }

    public Connection connect() throws DbException, SQLException {
        try {
            Class.forName("org.postgresql.Driver").newInstance();
            Connection connection = null;
            DriverManager.setLoginTimeout(60);
            String url = new StringBuilder()
                    .append("jdbc:")
                    .append("postgresql")       // “mysql” / “db2” / “mssql” / “oracle” / ...
                    .append("://")
                    .append("localhost")
                    .append(":")
                    .append(5432)
                    .append("/")
//                    .append("the_big_project")
                    .append("?user=")
                    .append("postgres")
                    .append("&password=")
                    .append("postgres10").toString();
            return DriverManager.getConnection(url);
        } catch (ClassNotFoundException | IllegalAccessException | InstantiationException e) {
            throw new DbException("Could not load DB driver.", e);
        }
    }


    public void dropTablesAndSeq() throws DbException, SQLException {
        ConnectionDb db = new ConnectionDb();
        try (
                Connection connection = db.connectToMyDb()) {

            StringBuilder builder = new StringBuilder();

            builder.append("DROP TABLE recipes, users, ingredients, fridges, recipe_ingredients, fridge_ingredients;");
            builder.append("DROP SEQUENCE recipes_ids, users_ids, ingredients_ids, fridges_ids, recipe_ingredients_ids, fridge_ingredients_ids;");

            Statement statement = connection.createStatement();
            statement.execute(builder.toString());
        }
    }

    public Connection connectToMyDb() throws DbException, SQLException {
        try {
            Class.forName("org.postgresql.Driver").newInstance();
            Connection connection = null;
            DriverManager.setLoginTimeout(60);
            String url = new StringBuilder()
                    .append("jdbc:")
                    .append("postgresql")       // “mysql” / “db2” / “mssql” / “oracle” / ...
                    .append("://")
                    .append("localhost")
                    .append(":")
                    .append(5432)
                    .append("/")
                    .append("the_big_project")
                    .append("?user=")
                    .append("postgres")
                    .append("&password=")
                    .append("postgres10").toString();
            return DriverManager.getConnection(url);
        } catch (ClassNotFoundException | IllegalAccessException | InstantiationException e) {
            throw new DbException("Could not load DB driver.", e);
        }
    }

    public void populateDb() throws DbException, SQLException {
        populateUsers();
        populateRecipes();
        populateIngredients();
        populateFridges();
        populateFridgeIngredients();
        populateRecipeIngredients();
    }

    public void populateUsers() throws DbException, SQLException {
        ConnectionDb db = new ConnectionDb();
        try (
                Connection connection = db.connectToMyDb()) {

            StringBuilder builder = new StringBuilder();

            builder.append("INSERT INTO users(username, password, email, desired_calories)\n" +
                    "VALUES \n" +
                    "('user1', 'parola1', 'user1@gmail.com', 2000), \n" +
                    "('user2', 'parola2', 'user2@gmail.com', 2002),\n" +
                    "('user3', 'parola3', 'user3@gmail.com', 2003),\n" +
                    "('user4', 'parola4', 'user4@gmail.com', 2000);");

            Statement statement = connection.createStatement();
            statement.execute(builder.toString());
        }
    }

    public void populateRecipes() throws DbException, SQLException {
        ConnectionDb db = new ConnectionDb();
        try (
                Connection connection = db.connectToMyDb()) {

            StringBuilder builder = new StringBuilder();

            builder.append("insert into recipes(name, preparation, calories, recipe_type)\n" +
                    "values \n" +
                    "('tocanita de vita', 'aaaaaaaaaaaa', 500, 'meal'), \n" +
                    "('paste cu somon', 'bbbbbbbbbbbbbbb', 700, ''),\n" +
                    "('supa de pui', 'cccccccccccccc', 200, 'baby friendly, low calories'),\n" +
                    "('cheesy hamburger', 'ddddddddddd', 1000, 'cheat-day');");

            Statement statement = connection.createStatement();
            statement.execute(builder.toString());
        }
    }

    public void populateIngredients() throws DbException, SQLException {
        ConnectionDb db = new ConnectionDb();
        try (
                Connection connection = db.connectToMyDb()) {

            StringBuilder builder = new StringBuilder();

            builder.append("insert into ingredients(name, measurement_unit, quantity)\n" +
                    "values ('potato', 'PCS'), ('tomato', 'PCS'),('beaf meat', 'GRAMS'),\n" +
                    "('onion', 'PCS'), ('carrot', 'PCS'),\n" +
                    "('salmon', 'MILILITRES'), ('heavy cream', 'PCS'), ('onion', 'PCS'),\n" +
                    "('chicken breast', 'PCS'), ('onion', 'PCS'), ('carrot', 'PCS'),\n" +
                    "('burger buns', 'PCS'), ('hamburger meat', 'PCS'), ('lettuce', 'GRAMS'),\n" +
                    "('tomato', 'PCS'), ('bacon', 'GRAMS'0), ('onion', 'PCS');");

            Statement statement = connection.createStatement();
            statement.execute(builder.toString());
        }
    }

    public void populateRecipeIngredients() throws DbException, SQLException {
        ConnectionDb db = new ConnectionDb();
        try (
                Connection connection = db.connectToMyDb()) {

            StringBuilder builder = new StringBuilder();

            builder.append("insert into recipe_ingredients (recipe_id, ingredient_id, quantity) values (1,1, 5), (1,2, 2), " +
                    "(1,3, 700), (1,4, 1), (1,5, 1), (2,6, 100), (2,7, 1), (2,8, 1), (3,9, 1), (3, 10, 1), (3,11, 1), (4, 12, 2), (4, 13, 2), " +
                    "(4, 14, 50), (4, 15, 1), (4,16, 2), (4, 17, 1);");

            Statement statement = connection.createStatement();
            statement.execute(builder.toString());
        }
    }

    public void populateFridges() throws DbException, SQLException {
        ConnectionDb db = new ConnectionDb();
        try (
                Connection connection = db.connectToMyDb()) {

            StringBuilder builder = new StringBuilder();

            builder.append("INSERT INTO fridges (user_id) values (1), (2), (3), (4);");

            Statement statement = connection.createStatement();
            statement.execute(builder.toString());
        }
    }

    public void populateFridgeIngredients() throws DbException, SQLException {
        ConnectionDb db = new ConnectionDb();
        try (
                Connection connection = db.connectToMyDb()) {

            StringBuilder builder = new StringBuilder();

            builder.append("insert into fridge_ingredients (fridge_id, ingredient_id, quantity) values (1,1, 5), (1,2, 2), " +
                    "(1,3, 700), (1,4, 1), (1,5, 1), (2,6, 100), (2,7, 1), (2,8, 1), (3,9, 1), (3, 10, 1), (3,11, 1), (4, 12, 2), (4, 13, 2), " +
                    "(4, 14, 50), (4, 15, 1), (4,16, 2), (4, 17, 1);");

            Statement statement = connection.createStatement();
            statement.execute(builder.toString());
        }
    }


}
