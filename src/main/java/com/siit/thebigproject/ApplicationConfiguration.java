package com.siit.thebigproject;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.SingleConnectionDataSource;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.sql.DataSource;


@Configuration
public class ApplicationConfiguration {

    @Value("localhost")
    private String dbHost;

    @Value("the_big_project")
    private String dbPassword;

    @Value("postgres")
    private String dbUser;

    @Value("the_big_project")
    private String dbName;

    @Bean
    public DataSource dataSource() {
        String url = new StringBuilder()
                .append("jdbc:")
                .append("postgresql")
                .append("://")
                .append(dbHost)
                .append(":")
                .append("5432")
                .append("/")
                .append(dbName)
                .append("?user=")
                .append(dbUser)
                .append("&password=")
                .append(dbPassword).toString();

        return new SingleConnectionDataSource(url, true);
    }

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

}