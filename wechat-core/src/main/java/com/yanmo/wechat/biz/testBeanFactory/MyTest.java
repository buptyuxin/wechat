package com.yanmo.wechat.biz.testBeanFactory;

import com.yanmo.wechat.biz.service.TokenService;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by yanmo.yx on 2015/5/13.
 */
public class MyTest {

    @Autowired
    private TokenService tokenService;

    public TokenService getTokenService() {
        return tokenService;
    }

//    public void setTokenService(TokenService tokenService) {
//        this.tokenService = tokenService;
//    }
}
