package com.yanmo.wechat.log;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by yanmo.yx on 2015/4/18.
 */
public class WxLog {
    private static Logger logger = LoggerFactory.getLogger("consolo");

    //    private static Logger logger = LoggerFactory.getLogger("SxJingDong");
    public static void log(String s) {
        logger.info(s);
    }
}
