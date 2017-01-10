package com.yanmo.wechat.biz.service.impl;

import com.yanmo.wechat.biz.service.TokenService;

/**
 * Created by yanmo.yx on 2015/5/26.
 */
public class TokenServiceTest implements TokenService {
    @Override
    public String queryToken() {
        return "success";
    }
}
