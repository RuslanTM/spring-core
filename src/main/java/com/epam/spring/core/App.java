package com.epam.spring.core;

import com.epam.spring.core.enums.EventType;
import com.epam.spring.core.logger.ConsoleEventLogger;
import com.epam.spring.core.logger.EventLogger;
import com.epam.spring.core.model.Client;
import com.epam.spring.core.model.Event;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.Map;

import static java.util.Objects.nonNull;

public class App {

    private Client client;
    private ConsoleEventLogger defaultLogger;
    private Map<EventType, EventLogger> loggers;

    private App(Client client, ConsoleEventLogger eventLogger, Map<EventType, EventLogger> loggers) {
        this.client = client;
        this.defaultLogger = eventLogger;
        this.loggers = loggers;
    }

    public void logEvent(Event event, String msg) {
        event.setMsg(msg);
        EventLogger eventLogger = loggers.get(event.getEventType());
        if (nonNull(eventLogger)) {
            eventLogger.logEvent(event);
        } else {
            defaultLogger.logEvent(event);
        }

    }

    public static void main(String[] args) {
        ConfigurableApplicationContext ctx = new ClassPathXmlApplicationContext("spring.xml");
        App app = (App) ctx.getBean("app");
        Event event = (Event) ctx.getBean("event");
        event.setEventType(EventType.ERROR);
        Event event2 = (Event) ctx.getBean("event");
        event2.setEventType(EventType.INFO);

        app.client = new Client(1L, "Ivan Petrov");
        app.logEvent(event, "message");
        app.logEvent(event2, "message2");
        ctx.close();
    }
}
