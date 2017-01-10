package com.yanmo.wechat.annotation;

/**
 * Created by yanmo.yx on 2015/10/22.
 */
public class TestImpl implements ITest {
    @Override
    public String test(@MyNotNull Integer i) {
        return "Hello!";
    }
}
