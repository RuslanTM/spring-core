package com.epam.spring.core.model;

import com.epam.spring.core.enums.EventType;

import java.text.DateFormat;
import java.time.LocalDateTime;
import java.util.Date;

public class Event {
    private int id;
    private String msg;
    private Date date;
    private DateFormat df;
    private EventType eventType;

    public Event(Date date, DateFormat df) {
        this.date = date;
        this.df = df;
        this.id = (int) (100.00 * Math.random());
    }

    public int getId() {
        return id;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public EventType getEventType() {
        return eventType;
    }

    public void setEventType(EventType eventType) {
        this.eventType = eventType;
    }

    @Override
    public String toString() {
        return "Event{" +
                "id=" + id +
                ", msg='" + msg + '\'' +
                ", date=" + df.format(date) +
                "}\n";

    }

    public String isDay() {
        LocalDateTime now = LocalDateTime.now();
        if (now.getHour() >= 8 && now.getHour() <= 17) {
            return Boolean.TRUE.toString();
        }
        return Boolean.FALSE.toString();

    }
}
