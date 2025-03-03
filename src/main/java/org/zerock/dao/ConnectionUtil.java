package org.zerock.dao;

import java.sql.Connection;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

public enum ConnectionUtil {
	
    INSTANCE;

    private HikariDataSource ds;

    ConnectionUtil()  {
        HikariConfig config = new HikariConfig();
        config.setDriverClassName("org.mariadb.jdbc.Driver");
        config.setJdbcUrl("jdbc:mariadb://localhost:3306/webdb");
        config.setUsername("webdbuser");
        config.setPassword("webdbuser");
        config.addDataSourceProperty("cachePrepStmts", "true");
        config.addDataSourceProperty("prepStmtCacheSize", "250");
        config.addDataSourceProperty("prepStmtCacheSqlLimit", "2048");

        config.setMinimumIdle(2);
        config.setMaximumPoolSize(5);
        config.setConnectionTimeout(10000); //10초
        
        ds = new HikariDataSource(config);
    }

    public Connection getConnection()throws Exception {
        return ds.getConnection();
    }

}
