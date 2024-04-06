package org.example.bookslibrary.config;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.example.bookslibrary.logger.Logger;
import org.springframework.stereotype.Component;


@Aspect
@Component
public class LogTimeAspect {
    private final Logger log = Logger.getInstance();

    @Pointcut(value = "execution(* org.example.bookslibrary.repositories..*(..))")
    public void forRepositoryLog() {}

    @Pointcut(value = "execution(* org.example.bookslibrary.services..*(..))")
    public void forServiceLog() {}

    @Pointcut(value = "execution(* org.example.bookslibrary.controllers..*(..))")
    public void forControllerLog() {}

    @Pointcut(value = "forRepositoryLog() || forServiceLog() || forControllerLog()")
    public void forAllApp() {}

    @Before(value = "forAllApp()")
    public void beforeMethod(JoinPoint joinPoint) {
        String methodName = joinPoint.getSignature().getName();
        log.logInfo(this.getClass(),"====> Method Name is >> "+ methodName);

        // Record the start time
        long startTime = System.currentTimeMillis();

        Object[] args = joinPoint.getArgs();
        for (Object arg : args) {
            log.logInfo(this.getClass(),"=====> argument >> "+ arg);
        }

        // Record the end time
        long endTime = System.currentTimeMillis();
        long executionTime = endTime - startTime;

        log.logInfo(this.getClass(),"====> Time taken to execute method "+methodName+" : milliseconds "+ executionTime);
    }
}
