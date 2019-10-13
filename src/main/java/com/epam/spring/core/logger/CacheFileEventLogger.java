package com.epam.spring.core.logger;

import com.epam.spring.core.model.Event;

import java.util.ArrayList;
import java.util.List;

public class CacheFileEventLogger extends FileEventLogger {
    private int cacheSize;
    private List<Event> cache;

    public CacheFileEventLogger(int cacheSize, String fileName) {
        super(fileName);
        this.cacheSize = cacheSize;
        this.cache = new ArrayList<>();
    }

    @Override
    public void logEvent(Event event) {
        cache.add(event);

        if (cache.size() == cacheSize) {
            writeEventsToFile();
            cache.clear();
        }
    }

    private void writeEventsToFile() {
        cache.forEach(event -> super.logEvent(event));
    }

    private void destroy() {
        if (!cache.isEmpty()) {
            writeEventsToFile();
        }
    }
}
