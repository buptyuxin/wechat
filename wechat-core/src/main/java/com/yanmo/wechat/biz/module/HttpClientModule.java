package com.yanmo.wechat.biz.module;

import java.util.Map;

/**
 * Created by yanmo.yx on 2015/5/9.
 */
public interface HttpClientModule {

    /**
     * get请求
     *
     * @param url
     * @return
     */
    public String doGet(String url);

    /**
     * post请求
     *
     * @param url
     * @param parameters
     * @param data
     * @return
     */
    public String doPost(String url, Map<String, String> parameters, String data);
}
