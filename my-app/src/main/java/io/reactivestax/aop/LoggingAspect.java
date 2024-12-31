package io.reactivestax.aop;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Aspect
@Component
@EnableAspectJAutoProxy
public class LoggingAspect {


    private static final Logger log = LogManager.getLogger(LoggingAspect.class);

    @Pointcut("execution(* io.reactivestax.order.service.OrderService.createOrder(..))")
    public void logAroundPointCutDefinition(){}

    @Around(value = "logAroundPointCutDefinition()")
    public Object logAround(ProceedingJoinPoint joinPoint) throws Throwable {
        log.debug("****************logAround***************************");
        log.debug("logAround() is running!");
        System.out.println("check--------------->");
        log.debug("method intercepted method : {}", joinPoint.getSignature().getName());
        log.debug("method intercepted arguments : {}", Arrays.toString(joinPoint.getArgs()));
        log.debug("Around before is running!");
        Object returnValue = joinPoint.proceed();

        log.debug("Around after is running! {}", returnValue);
        log.debug("********************logAround************************");
        return returnValue;
    }
}
