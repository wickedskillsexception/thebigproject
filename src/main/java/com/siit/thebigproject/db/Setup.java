package com.siit.thebigproject.db;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class Setup {

    public void setupDb() throws DbException, SQLException {
        ConnectionDb db = new ConnectionDb();
        try (
                Connection connection = db.connect()) {
            Statement statement = connection.createStatement();
            statement.execute("CREATE DATABASE the_big_project;");
        }

        try (Connection connection = db.connect()) {
            StringBuilder builder = new StringBuilder();
            builder.append("CREATE SEQUENCE accomodation_ids;");
            builder.append("CREATE TABLE accomodation(id INT PRIMARY KEY DEFAULT NEXTVAL('accomodation_ids'), " +
                    "type VARCHAR(32), bed_type VARCHAR(32), max_guests INT, description VARCHAR(512));");
            builder.append("CREATE SEQUENCE room_fair_ids;");
            builder.append("CREATE TABLE room_fair(id INT PRIMARY KEY DEFAULT NEXTVAL('room_fair_ids'), " +
                    "value DOUBLE PRECISION, season VARCHAR(32));");
            builder.append("CREATE SEQUENCE accomodation_fair_relation_ids;");
            builder.append("CREATE TABLE accomodation_fair_relation(id INT PRIMARY KEY DEFAULT NEXTVAL('accomodation_fair_relation_ids'), " +
                    " id_accomodation INT REFERENCES accomodation(id), id_room_fair INT REFERENCES room_fair(id));");

            Statement statement = connection.createStatement();
            statement.execute(builder.toString());
        }
    }

}
