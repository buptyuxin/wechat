package com.yanmo.wechat.biz.testBeanFactory;

import com.alibaba.citrus.springext.support.context.XmlApplicationContext;
import com.yanmo.wechat.schames.springext.MyService;
import org.springframework.context.ApplicationContext;
import org.springframework.core.io.ClassPathResource;

/**
 * Created by yanmo.yx on 2015/8/20.
 */
public class TestMain {
    public static void main(String[] args) {
        ApplicationContext ctx = new XmlApplicationContext(new ClassPathResource("test.xml"));

        MyService myService = (MyService) ctx.getBean("myService");
        if (myService != null) {
            System.out.println(myService.sayHello());
        }
    }
}
