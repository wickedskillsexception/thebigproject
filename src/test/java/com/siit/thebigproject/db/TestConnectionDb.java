package com.siit.thebigproject.db;

import org.springframework.beans.factory.annotation.Autowired;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class TestConnectionDb {

    @Autowired
    ConnectionDb db;

    public void setup() throws DbException, SQLException {
        setupDb();
        setupTables();
    }

    public void setupDb() throws DbException, SQLException {
        try (Connection connection = db.connect()) {
            Statement statement = connection.createStatement();
            statement.execute("CREATE DATABASE the_big_project_test;");
        }
    }

    public void setupTables() throws DbException, SQLException {
        try (Connection connection = db.connectToMyDb()) {
            StringBuilder builder = new StringBuilder();

            builder.append("CREATE SEQUENCE recipes_ids;");
            builder.append("CREATE TABLE recipes(id INT PRIMARY KEY DEFAULT NEXTVAL('recipes_ids'), " +
                    "name VARCHAR(32), preparation TEXT, preparation_time INTEGER, image VARCHAR(32), smart_points INTEGER);");

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
                    " recipe_id INT REFERENCES recipes(id), name VARCHAR(32), measurement_unit VARCHAR(32), quantity DOUBLE PRECISION);");

            builder.append("CREATE SEQUENCE fridge_ingredients_ids;");
            builder.append("CREATE TABLE fridge_ingredients(id INT PRIMARY KEY DEFAULT NEXTVAL('fridge_ingredients_ids'), " +
                    " fridge_id INT REFERENCES fridges(id), name VARCHAR(32), measurement_unit VARCHAR(32), quantity DOUBLE PRECISION);");

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
                    .append("?user=")
                    .append("postgres")
                    .append("&password=")
                    .append("postgres10").toString();
            return DriverManager.getConnection(url);
        } catch (ClassNotFoundException | IllegalAccessException | InstantiationException e) {
            throw new DbException("Could not load DB driver.", e);
        }
    }

    public void dropDb() throws DbException, SQLException {
        try (Connection connection = db.connectToMyDb()) {

            StringBuilder builder = new StringBuilder();

            builder.append("DROP DATABASE the_big_project_test;");

            Statement statement = connection.createStatement();
            statement.execute(builder.toString());
        }
    }


    public void dropTablesAndSeq() throws DbException, SQLException {
        try (Connection connection = db.connectToMyDb()) {

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
                    .append("the_big_project_test")
                    .append("?user=")
                    .append("postgres")
                    .append("&password=")
                    .append("postgres10").toString();
            return DriverManager.getConnection(url);
        } catch (ClassNotFoundException | IllegalAccessException | InstantiationException e) {
            throw new DbException("Could not load DB driver.", e);
        }
    }

}
