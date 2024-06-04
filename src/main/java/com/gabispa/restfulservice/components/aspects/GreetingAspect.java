package com.gabispa.restfulservice.components.aspects;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.util.logging.Logger;

@Aspect
@Component
public class GreetingAspect {
  private  Logger logger = Logger.getLogger(getClass().getName());

  @Pointcut("within(@org.springframework.web.bind.annotation.RestController *)")
  public void controllerMethods(){}

  @Around("controllerMethods() && execution(* com.gabispa.restfulservice.controller.UserController.*(..))")
  public Object logUserActivity (ProceedingJoinPoint joinPoint) throws Throwable{

    String methodName = joinPoint.getSignature().getName();
    String remoteAddress = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes())
            .getRequest().getRemoteAddr();

    logger.info("UserName "+methodName+" IP ADDRESS "+remoteAddress);
    Object result = joinPoint.proceed();
    logger.info("User activity finished "+methodName);
    return result;
  }
}