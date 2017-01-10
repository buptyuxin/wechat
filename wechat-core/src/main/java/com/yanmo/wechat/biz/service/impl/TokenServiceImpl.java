package com.yanmo.wechat.biz.service.impl;

import com.yanmo.wechat.biz.module.HttpClientModule;
import com.yanmo.wechat.biz.msg.parser.JsonParser;
import com.yanmo.wechat.biz.service.TokenService;
import com.yanmo.wechat.biz.utils.EnvUtils;
import com.yanmo.wechat.domain.ResultDO;
import com.yanmo.wechat.domain.json.BaseJsonDO;
import com.yanmo.wechat.domain.json.TokenJsonDO;
import com.yanmo.wechat.log.WxLog;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.InitializingBean;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * Created by yanmo.yx on 2015/5/9.
 */
public class TokenServiceImpl implements TokenService, InitializingBean {

    private String token;

    private HttpClientModule httpClientModule;

    private JsonParser jsonParser;

    public void setHttpClientModule(HttpClientModule httpClientModule) {
        this.httpClientModule = httpClientModule;
    }

    public void setJsonParser(JsonParser jsonParser) {
        this.jsonParser = jsonParser;
    }

    @Override
    public String queryToken() {
        if (StringUtils.isBlank(token)) {
            return null;
        }
        return token;
    }

    class TokenGet implements Runnable {
        private void getToken() {
            String json = null;
            try {
                json = httpClientModule.doGet(EnvUtils.ACCESS_TOKEN_GET_URI);
            } catch (Exception e) {
                return;
            }
            ResultDO<BaseJsonDO> result = jsonParser.parseJson(json);
            if (result.isSuccess()) {
                token = ((TokenJsonDO)result.getModule()).getAccessToken();
            } else {
                WxLog.log(result.getErrorList().get(0).getName());
            }
            token = "";
        }

        @Override
        public void run() {
            getToken();
        }
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        //        Timer timer = new Timer();
//        timer.schedule(new AccessTokenTask(), 0, ACCESS_TOKEN_TASK_PERIOD);
        // 开个定时任务每一个小时更新一次access token
        ScheduledExecutorService ses = Executors.newSingleThreadScheduledExecutor();
        // TODO 这里需要一个runnable对象来取token，并被定时调度
        ses.scheduleAtFixedRate(new TokenGet(), 0, EnvUtils.ACCESS_TOKEN_TASK_PERIOD, TimeUnit.HOURS);
    }
}
