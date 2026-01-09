package com.project.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * Aspect for logging execution time and activity details of methods annotated with {@link LogActivity}.
 */
@Aspect
@Component
public class LoggingAspect {

    private static final Logger logger = LoggerFactory.getLogger(LoggingAspect.class);

    @Around("@annotation(logActivity)")
    public Object logActivityDetails(ProceedingJoinPoint joinPoint, LogActivity logActivity) throws Throwable {
        long start = System.currentTimeMillis();
        
        String activityName = logActivity.value().isEmpty() ? joinPoint.getSignature().getName() : logActivity.value();
        
        logger.info(">>> BEGIN ACTIVITY: [ {} ]", activityName);
        
        try {
            Object result = joinPoint.proceed();
            
            long executionTime = System.currentTimeMillis() - start;
            logger.info("<<< END ACTIVITY: [ {} ] completed in {} ms", activityName, executionTime);
            
            return result;
        } catch (Throwable throwable) {
            long executionTime = System.currentTimeMillis() - start;
            logger.error("!!! FAILED ACTIVITY: [ {} ] after {} ms. Error: {}", activityName, executionTime, throwable.getMessage());
            throw throwable;
        }
    }
}
