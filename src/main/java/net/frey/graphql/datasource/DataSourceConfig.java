package net.frey.graphql.datasource;

import net.datafaker.Faker;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DataSourceConfig {
    @Bean
    public Faker faker() {
        return new Faker();
    }
}
