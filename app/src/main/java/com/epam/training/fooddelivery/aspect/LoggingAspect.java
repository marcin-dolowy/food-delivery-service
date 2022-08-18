package com.epam.training.fooddelivery.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Aspect
@Component
@Slf4j
public class LoggingAspect {


    @Pointcut("execution(* com.epam.training.fooddelivery.view..*(..))")
    private void callParameters() {
    }

    @Before("@annotation(argumentLogging) && callParameters())")
    public void beforeCallParameters(JoinPoint joinPoint, EnableArgumentLogging argumentLogging) {
        log.info("Method name: [" +
                joinPoint.getSignature().getName() + "], parameter(s): " + Arrays.toString(joinPoint.getArgs())
        );
    }

    @AfterReturning(value = "@annotation(returnValueLogging) && callParameters()", returning = "returnValue")
    public void afterCallParameters(JoinPoint joinPoint, Object returnValue, EnableReturnValueLogging returnValueLogging) {
        log.info("Method name [" +
                joinPoint.getSignature().getName() + "], return value: " + returnValue.toString());
    }

    @Around(value = "@annotation(executionTimeLogging) && callParameters()")
    public Object executingTimeMethod(ProceedingJoinPoint joinPoint, EnableExecutionTimeLogging executionTimeLogging)
            throws Throwable {
        double startTime = System.nanoTime();
        Object proceed = joinPoint.proceed();
        log.info("Execution time = " + ((System.nanoTime() - startTime) * 1000) + "µs");
        return proceed;
    }

}
