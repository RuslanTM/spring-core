package com.epam.spring.core.util;

import com.epam.spring.core.logger.EventLogger;
import com.epam.spring.core.logger.FileEventLogger;
import com.epam.spring.core.model.Event;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class LoggingAspect {

    private final static int MAX_ALLOWED = 2;
    @Autowired
    private EventLogger fileEventLogger;
    private int count;

    @Pointcut("execution(* *.logEvent(..))")
    public void allLogEventMethods() {

    }

    @Pointcut("allLogEventMethods() && within(*.*File*Logger)")
    public void logEventInsideFileLooggers() {

    }

    @Pointcut("execution(* com.epam.spring.core.logger.ConsoleEventLogger.logEvent(..))")
    public void consoleLoggerMethods() {

    }

    @Before("allLogEventMethods()")
    public void logBefore(JoinPoint joinPoint) {
        System.out.println("BEFORE : " + joinPoint.getTarget().getClass().getSimpleName() + " " + joinPoint.getSignature().getName());
    }

    @AfterReturning(pointcut = "allLogEventMethods()", returning = "retVal")
    public void logAfter(Object retVal) {
        System.out.println("Returned value: " + retVal);
    }

    @AfterThrowing(pointcut = "allLogEventMethods()", throwing = "ex")
    public void logAfterThrow(Throwable ex) {
        System.out.println("Thrown: " + ex);
    }

    @Around("consoleLoggerMethods() && args(evt)")
    public void aroundLogEvent(ProceedingJoinPoint jp, Object evt) throws Throwable {
        count++;
        if (count <= MAX_ALLOWED) {
            jp.proceed(new Object[] {evt});
        } else {
            fileEventLogger.logEvent((Event) evt);
        }
    }
}
