package com.yanmo.wechat.biz.testBeanFactory;

import com.yanmo.wechat.schames.springext.MyService;
import org.springframework.beans.BeansException;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by yanmo.yx on 2015/5/13.
 */
public class MyXmlApplicationContext extends ClassPathXmlApplicationContext {

    public MyXmlApplicationContext(String[] location) throws BeansException {
        super(location, true, null);
    }

    public static void main(String[] args) {
        MyXmlApplicationContext myXmlApplicationContext = new MyXmlApplicationContext(new String[]{"test.xml"});
        MyTest myTest = (MyTest) myXmlApplicationContext.getBean("myTest");


        MyService myService = (MyService) myXmlApplicationContext.getBean("myService");
        if (myService != null) {
            System.out.println(myService.sayHello());
        }
        System.out.println(myTest.getTokenService().queryToken());
    }
}
