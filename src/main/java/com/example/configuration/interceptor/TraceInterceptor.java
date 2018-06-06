package com.example.configuration.interceptor;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
@Aspect
public class TraceInterceptor {

    @Before("execution(public * com.example.configuration..*(..))")
    public void invokeBefore(JoinPoint joinPoint) {
        System.out.printf("[AOP at before] called parameters = %s, by %s#%s%n",
                Arrays.toString(joinPoint.getArgs()),
                joinPoint.getTarget().getClass(),
                joinPoint.getSignature().getName());
        // JoinPoint#getThisの場合は、拡張されたオブジェクトが返る
    }

    @Around("execution(public * com.example.configuration..*(..))")
    public Object invoke(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        Object ret = null;
        try {
            System.out.printf("[AOP at around] before invoke, parameters = %s, by %s#%s%n",
                    Arrays.toString(proceedingJoinPoint.getArgs()),
                    proceedingJoinPoint.getTarget().getClass(),
                    proceedingJoinPoint.getSignature().getName());

            ret = proceedingJoinPoint.proceed();
            return ret;
        } finally {
            System.out.printf("[AOP at around] after invoke, result = %s, %s#%s%n",
                    ret,
                    proceedingJoinPoint.getTarget().getClass(),
                    proceedingJoinPoint.getSignature().getName());
        }
    }
}
