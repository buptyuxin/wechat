package com.yanmo.wechat.annotation;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;

import java.util.Date;

/**
 * Created by yanmo.yx on 2015/10/22.
 */
public class AnnotationBeanPostProcessor implements BeanPostProcessor {
    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        Class cl = bean.getClass();
        return null;
    }

    public static void main(String[] args) {
        Date date = null;
        System.out.println("test null" + date );
    }
}
