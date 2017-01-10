package com.yanmo.wechat.aop;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by yanmo.yx on 2015/9/8.
 */
public class Human implements Sleepable {
    @Override
    public void sleep() {
        System.out.println("大王要睡觉了");
    }

    static public void main(String[] args) {
        ApplicationContext appCtx = new ClassPathXmlApplicationContext("testAop.xml");
        Sleepable human = (Sleepable) appCtx.getBean("human");
        human.sleep();
    }
}
