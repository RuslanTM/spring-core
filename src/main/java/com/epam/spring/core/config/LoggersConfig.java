package com.epam.spring.core.config;

import com.epam.spring.core.logger.CacheFileEventLogger;
import com.epam.spring.core.logger.CombinedEventLogger;
import com.epam.spring.core.logger.FileEventLogger;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;

import java.util.Arrays;

@ComponentScan(basePackages = "com.epam.spring.core.logger")
public class LoggersConfig {

    @Bean
    public FileEventLogger fileEventLogger() {
        return new FileEventLogger("logs.txt");
    }

    @Bean
    public CacheFileEventLogger cacheFileEventLogger() {
        return new CacheFileEventLogger(3, "cache_logs.txt");
    }

    @Bean
    public CombinedEventLogger combinedEventLogger() {
        return new CombinedEventLogger(Arrays.asList(fileEventLogger(), cacheFileEventLogger()));
    }
}
