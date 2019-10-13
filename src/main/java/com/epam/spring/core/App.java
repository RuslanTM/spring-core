package com.epam.spring.core;

import com.epam.spring.core.logger.CacheFileEventLogger;
import com.epam.spring.core.logger.ConsoleEventLogger;
import com.epam.spring.core.logger.FileEventLogger;
import com.epam.spring.core.model.Client;
import com.epam.spring.core.model.Event;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class App {

    private Client client;
    private ConsoleEventLogger eventLogger;
    private FileEventLogger fileEventLogger;
    private CacheFileEventLogger cacheFileEventLogger;

    private App(Client client, ConsoleEventLogger eventLogger, FileEventLogger fileEventLogger, CacheFileEventLogger cacheFileEventLogger) {
        this.client = client;
        this.eventLogger = eventLogger;
        this.fileEventLogger = fileEventLogger;
        this.cacheFileEventLogger = cacheFileEventLogger;
    }

    public void logEvent(Event event) {

        String message = String.format("%d: %s", client.getId(), client.getFullName());
        event.setMsg(message);
        eventLogger.logEvent(event);
        fileEventLogger.logEvent(event);
        cacheFileEventLogger.logEvent(event);
    }

    public static void main(String[] args) {
        ConfigurableApplicationContext ctx = new ClassPathXmlApplicationContext("spring.xml");
        App app = (App) ctx.getBean("app");
        Event event = (Event) ctx.getBean("event");
        Event event2 = (Event) ctx.getBean("event");
        Event event3 = (Event) ctx.getBean("event");
        Event event4 = (Event) ctx.getBean("event");

        app.client = new Client(1L, "Ivan Petrov");
        app.eventLogger = new ConsoleEventLogger();


        app.logEvent(event);
        app.logEvent(event2);
        app.logEvent(event3);
        app.logEvent(event4);
        ctx.close();
    }
}
