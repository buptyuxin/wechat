package com.yanmo.wechat.biz.utils;

/**
 * Created by yanmo.yx on 2015/5/9.
 */
public class EnvUtils {
    public static final String APPID = "wx9a78deca454f5fd6";
    public static final String APPSECRET = "972cd62c43de4c26c894169302d58130";
    public static final String ACCESS_TOKEN_GET_URI = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid="+ APPID + "&secret=" + APPSECRET;

    // 应用的token
    public static final String TOKEN = "XA8YQJ1YHgeLs";

    public static final long ACCESS_TOKEN_TASK_PERIOD = 1 * 60 * 60; // 1小时
}
