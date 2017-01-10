package com.yanmo.wechat.annotation;


/**
 * Created by yanmo.yx on 2015/10/22.
 */
public interface ITest {
    @MyTag(type = "test")
    public String test(@MyNotNull Integer i);
}
