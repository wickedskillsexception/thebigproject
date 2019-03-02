package com.siit;

import com.siit.thebigproject.db.ConnectionDb;
import com.siit.thebigproject.db.DbException;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.sql.SQLException;

@SpringBootApplication
public class ThebigprojectApplication {
    public static void main(String[] args) throws DbException, SQLException {

        SpringApplication.run(ThebigprojectApplication.class, args);

        ConnectionDb conn = new ConnectionDb();
        conn.setup();
    }
}


