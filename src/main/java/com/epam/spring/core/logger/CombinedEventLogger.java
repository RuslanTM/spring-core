package com.epam.spring.core.logger;

import com.epam.spring.core.model.Event;
import org.springframework.stereotype.Component;

import java.util.List;


@Component
public class CombinedEventLogger implements EventLogger {
    private List<EventLogger> loggers;

    public CombinedEventLogger(List<EventLogger> loggers) {
        this.loggers = loggers;
    }

    @Override
    public void logEvent(Event event) {
        loggers.forEach(logger -> logger.logEvent(event));
    }
}
