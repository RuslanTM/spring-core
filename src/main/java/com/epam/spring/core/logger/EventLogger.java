package com.epam.spring.core.logger;

import com.epam.spring.core.model.Event;

public interface EventLogger {
    public void logEvent(Event event);
}
