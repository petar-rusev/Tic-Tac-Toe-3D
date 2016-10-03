package com.tictac.config;

/**
 * Created by petar on 9/30/2016.
 */

import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

@Configuration
@EnableJpaRepositories("com.tictac.repository")
public class DatabaseConfig {

    @Autowired
    private Environment env;

    @Bean
    public DataSource getDataSource(){
        DriverManagerDataSource ds = new DriverManagerDataSource("jdbc:postgresql://localhost:5432/tictactoe","tictactoe","tictactoe");

        return ds;
    }
}
