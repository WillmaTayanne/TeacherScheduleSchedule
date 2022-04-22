package com.pdist.schedule;

import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
public class DatabaseConfig {

    @Bean
    public DataSource dataSource() {
        DataSourceBuilder dataSourceBuilder = DataSourceBuilder.create();
        dataSourceBuilder.driverClassName("org.postgresql.Driver");
        dataSourceBuilder.url("jdbc:postgresql://ec2-34-192-210-139.compute-1.amazonaws.com:5432/de83ms9p41v4h9");
        dataSourceBuilder.username("nkmzjdatzuusqx");
        dataSourceBuilder.password("f9a6dae3d1e53fecbf53406772acea1e7971ab12dda3a81a1f19c4e7481a8d91");
        return dataSourceBuilder.build();
    }
}
