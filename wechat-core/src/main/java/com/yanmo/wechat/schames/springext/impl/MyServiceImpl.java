package com.yanmo.wechat.schames.springext.impl;

import com.alibaba.citrus.springext.support.parser.AbstractNamedBeanDefinitionParser;
import com.alibaba.citrus.springext.util.DomUtil;
import com.alibaba.citrus.springext.util.SpringExtUtil;
import com.yanmo.wechat.schames.springext.MyService;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.xml.ParserContext;
import org.w3c.dom.Element;

/**
 * Created by yanmo.yx on 2015/8/19.
 */
public class MyServiceImpl implements MyService {

    private String wang;

    private String post;

    public String getPost() {
        return post;
    }

    public void setPost(String post) {
        this.post = post;
    }

    public String getWang() {
        return wang;
    }

    public void setWang(String wang) {
        this.wang = wang;
    }

    @Override
    public String sayHello() {
        return wang + post;
    }

    public static class DefinitionParser extends AbstractNamedBeanDefinitionParser<MyServiceImpl> {

        @Override
        protected void doParse(Element element, ParserContext parserContext, BeanDefinitionBuilder builder) {
            SpringExtUtil.subElementsToProperties(element, builder, DomUtil.sameNs(element));
            SpringExtUtil.attributesToProperties(element, builder, "post");
        }

        @Override
        protected String getDefaultName() {
            return MyService.DEFAULT_NAME;
        }
    }
}
