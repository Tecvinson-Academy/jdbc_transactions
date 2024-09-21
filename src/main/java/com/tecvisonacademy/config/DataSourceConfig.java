package com.tecvisonacademy.config;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import javax.sql.DataSource;

public class DataSourceConfig {
    private static HikariDataSource dataSource;

    static {

        HikariConfig config = new HikariConfig();
        config.setJdbcUrl("jdbc:mysql://localhost:3306/jdbc_transactions");
        config.setUsername("root");
        config.setPassword("");
        config.setMaximumPoolSize(10); //Set the maximum number of connection
        config.setMinimumIdle(5); //Set the minimum number of idle connection
        config.setIdleTimeout(30000);// Set the minimum idle time in milli seconds

        dataSource = new HikariDataSource(config);
    }

    public static DataSource getDataSource()
    {
        return dataSource;
    }

}