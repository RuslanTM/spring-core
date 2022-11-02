package com.epam.spring.core;

import com.epam.spring.core.config.AppConfig;
import com.epam.spring.core.enums.EventType;
import com.epam.spring.core.logger.CombinedEventLogger;
import com.epam.spring.core.logger.ConsoleEventLogger;
import com.epam.spring.core.logger.EventLogger;
import com.epam.spring.core.logger.FileEventLogger;
import com.epam.spring.core.model.Client;
import com.epam.spring.core.model.Event;
import com.epam.spring.core.util.StatisticsAspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.HashMap;
import java.util.Map;

import static java.util.Objects.nonNull;

public class App {

    private Client client;
    @Autowired
    private EventLogger consoleEventLogger;
    @Autowired
    private EventLogger fileEventLogger;
    @Autowired
    private EventLogger combinedEventLogger;
    @Autowired
    private StatisticsAspect statisticsAspect;

    private Map<EventType, EventLogger> loggers = new HashMap();

    public App() {
        System.out.println("Hello");
    }

    {
        loggers.put(EventType.ERROR, combinedEventLogger);
        loggers.put(EventType.INFO, fileEventLogger);
    }

    public void logEvent(Event event, String msg) {
        event.setMsg(msg);
        EventLogger eventLogger = loggers.get(event.getEventType());
        if (loggers.isEmpty()) {
            eventLogger.logEvent(event);
        } else {
            combinedEventLogger.logEvent(event);
        }

    }

    public static void main(String[] args) {

        ApplicationContext annotationCtx = new AnnotationConfigApplicationContext(AppConfig.class);
        //ConfigurableApplicationContext ctx = new ClassPathXmlApplicationContext("spring.xml");
        App app = (App) annotationCtx.getBean("app");
        Event event = (Event) annotationCtx.getBean("event");
        Event event2 = (Event) annotationCtx.getBean("event");
        app.client = (Client) annotationCtx.getBean("client");

        event.setEventType(EventType.ERROR);
        event2.setEventType(EventType.INFO);

        app.logEvent(event, app.client.getFullName());
        app.logEvent(event2, app.client.getGreeting());
        app.logEvent(event2, app.client.getGreeting());
        app.logEvent(event, app.client.getGreeting());
        app.logEvent(event, app.client.getGreeting());
        ((AnnotationConfigApplicationContext) annotationCtx).stop();
    }
}
