package com.yanmo.wechat.aop;

import com.yanmo.wechat.log.LogBackInit;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;

/**
 * Created by yanmo.yx on 2015/9/8.
 */
@Aspect
public class AspectJTest {

    @Pointcut("execution(public * *.sleep())")
    public void sleeppoint(){}

    @Before("sleeppoint()")
    public void beforeSleep(){
        System.out.println("吃多了");
    }

    @AfterReturning("sleeppoint()")
    public void afterSleep(){
        System.out.println("ZZZ zzz...");
    }

    @Around("execution(* *(..)) && @annotation(Loggable)")
    public Object around(ProceedingJoinPoint point) {
        Long start = System.currentTimeMillis();
        Object result = null;
        try {
            result = point.proceed();
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }

        LogBackInit.log.info("AAAAAAAAAAAAAAAAAAAAA");

        return result;
    }
}
