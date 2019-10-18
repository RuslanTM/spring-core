package com.epam.spring.core.config;

import com.epam.spring.core.logger.CacheFileEventLogger;
import com.epam.spring.core.logger.CombinedEventLogger;
import com.epam.spring.core.logger.FileEventLogger;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;

@Configuration
@ComponentScan(basePackages = {"com.epam.spring.core.logger", "com.epam.spring.core.util"})
public class LoggersConfig {

    @Bean
    public FileEventLogger fileEventLogger() {
        return new FileEventLogger("logs.txt");
    }

    @Bean
    public CacheFileEventLogger cacheFileEventLogger() {
        return new CacheFileEventLogger(3, "cache_logs.txt");
    }
}
