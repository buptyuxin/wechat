package com.yanmo.wechat.biz.testBeanFactory;

import com.yanmo.wechat.biz.service.TokenService;
import com.yanmo.wechat.biz.service.impl.TokenServiceTest;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;

/**
 * Created by yanmo.yx on 2015/5/13.
 */
public class MyBeanFactoryPostProcessor implements BeanFactoryPostProcessor {
    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
        // TODO 这里手动添加reslovableDependency到beanfactory中，看看回头注入的时候是否能注入
        beanFactory.registerResolvableDependency(TokenService.class, new TokenServiceTest());
    }
}
