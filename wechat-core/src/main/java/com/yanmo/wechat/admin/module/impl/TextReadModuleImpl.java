package com.yanmo.wechat.admin.module.impl;

import com.yanmo.wechat.admin.dao.TextDAO;
import com.yanmo.wechat.admin.module.TextReadModule;
import com.yanmo.wechat.domain.admin.TextDO;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * Created by yanmo.yx on 2015/9/21.
 */
public class TextReadModuleImpl implements TextReadModule {

    private TextDAO textDAO;

    public void setTextDAO(TextDAO textDAO) {
        this.textDAO = textDAO;
    }

    @Override
    public TextDO queryTextById(Long id) {
        return textDAO.getTextById(id);

//        这里玩一下动态代理，但是好像是jdk自带的，只能针对接口级别进行代理，还是玩cglib吧
//        TextDO text = new TextDO();
//        TextDO textDO = (TextDO) Proxy.newProxyInstance(this.getClass().getClassLoader(), text.getClass().getInterfaces(), new InvocationHandler() {
//            @Override
//            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
//                if (method.getName().equals("")) {
//                    return method.invoke(text, args);
//                } else {
//                    return null;
//                }
//            }
//        });
//        return textDO;
    }
}
