package com.epam.spring.core.config;

import com.epam.spring.core.App;
import com.epam.spring.core.model.Client;
import com.epam.spring.core.model.Event;
import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.Scope;

import java.text.DateFormat;
import java.util.Date;

@Configuration
@EnableAspectJAutoProxy
@Import(LoggersConfig.class)
@PropertySource(value = {"classpath:client.properties"},
        ignoreResourceNotFound = true)
public class AppConfig {

    @Bean
    @Scope("prototype")
    public Event event() {
        return new Event(new Date(), DateFormat.getDateTimeInstance());
    }

    @Bean
    public static PropertyPlaceholderConfigurer propertyPlaceholderConfigurer() {
        return new PropertyPlaceholderConfigurer();
    }

    @Bean
    public Client client() {
        return new Client(1L, "Petrov Ivan");
    }

    @Bean
    public App app() {
        return new App();
    }

}
