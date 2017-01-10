package com.yanmo.wechat.schames.spring;

import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.xml.AbstractSingleBeanDefinitionParser;
import org.springframework.beans.factory.xml.NamespaceHandlerSupport;
import org.springframework.util.StringUtils;
import org.w3c.dom.Element;

/**
 * Created by yanmo.yx on 2015/5/25.
 */
public class NamespaceHandler extends NamespaceHandlerSupport {
    @Override
    public void init() {
        registerBeanDefinitionParser("dog", new MyBeanDefinitionParser());
    }
    class MyBeanDefinitionParser extends AbstractSingleBeanDefinitionParser {
        protected Class getBeanClass(Element element) {
            return DogDO.class;
        }
        protected void doParse(Element element, BeanDefinitionBuilder bean) {
            String name = element.getAttribute("name");
            String age = element.getAttribute("age");
            if (StringUtils.hasText(name)) {
                bean.addPropertyValue("name", name);
            }
            if (StringUtils.hasText(age)) {
                bean.addPropertyValue("age", Integer.valueOf(age));
            }
        }
    }
}
