package com.siit.thebigproject.service.recipesManager.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class TheBigProjectDB {

    public Connection connect() throws TheBigProjectDBException, SQLException {
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
                    .append("the_big_project").toString();
            return DriverManager.getConnection(url);
        } catch (ClassNotFoundException | IllegalAccessException | InstantiationException e) {
            throw new TheBigProjectDBException("Could not load DB driver.", e);
        }
    }
}
