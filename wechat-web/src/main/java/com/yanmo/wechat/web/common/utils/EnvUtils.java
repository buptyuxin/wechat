package com.yanmo.wechat.web.common.utils;

/**
 * Created by yanmo.yx on 2015/4/18.
 */
public class EnvUtils {
    // 这里应该使用properties文件的，暂时先不管吧
    public static final String APPID = "wx9a78deca454f5fd6";
    public static final String APPSECRET = "972cd62c43de4c26c894169302d58130";
    public static final String ACCESS_TOKEN_GET_URI = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid="+ APPID + "&secret=" + APPSECRET;

    public static final String TOKEN = "XA8YQJ1YHgeLs";

    public static String ACCESS_TOKEN = "";
    public static long ACCESS_TOKEN_TASK_PERIOD = 1;    // 1h
}
