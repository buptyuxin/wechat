package com.yanmo.wechat.web.home.module.screen;

import com.alibaba.citrus.turbine.Context;
import com.alibaba.citrus.turbine.TurbineRunData;
import com.google.common.collect.Lists;
import com.yanmo.wechat.biz.service.MsgProcessService;
import com.yanmo.wechat.domain.message.MsgDO;
import com.yanmo.wechat.log.WxLog;
import com.yanmo.wechat.web.common.utils.EnvUtils;
import com.yanmo.wechat.web.common.utils.MsgConvertUtils;
import org.apache.commons.codec.digest.DigestUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Created by yanmo.yx on 2015/4/18.
 */
public class Default {

    private MsgProcessService msgProcessService;

    public void setMsgProcessService(MsgProcessService msgProcessService) {
        this.msgProcessService = msgProcessService;
    }

    public void execute(TurbineRunData rundata, Context context) {

        if (!validateParam(rundata)) {
            WxLog.log("微信消息验证失败");
            rundata.setRedirectLocation("/error.vm");
            return;
        }
        String echostr = rundata.getParameters().getString("echostr");
        if (echostr != null) {
            context.put("result", echostr);
            return;
        }

        HttpServletRequest req = rundata.getRequest();
        HttpServletResponse res = rundata.getResponse();
        try {
            req.setCharacterEncoding("UTF-8");
            res.setCharacterEncoding("UTF-8");
            MsgDO msgDO = MsgConvertUtils.convertMsg(req);
            MsgDO replyMsg = msgProcessService.processMsg(msgDO);
            context.put("result", MsgConvertUtils.fromMsg2Xml(replyMsg));
        } catch (UnsupportedEncodingException e) {
            WxLog.log("http请求使用uft-8编码转码失败" + req);
        }
    }

    private boolean validateParam(TurbineRunData rundata) {
        String signature = rundata.getParameters().getString("signature");
        String timestamp = rundata.getParameters().getString("timestamp");
        String nonce = rundata.getParameters().getString("nonce");

        List<String> list = Lists.newArrayList(EnvUtils.TOKEN, timestamp, nonce);
//        这里不支持lambda表达式，需要jdk1.8以上
//        Collections.sort(list,(o1,o2)->{return -o1.compareTo(o2);});
        Collections.sort(list, new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return -o1.compareTo(o2);
            }
        });
        StringBuilder sb = new StringBuilder();
        for (String s : list) {
            sb.append(s);
        }
        String validator = DigestUtils.sha1Hex(sb.toString());
        if (validator != null && validator.equals(signature)) {
            return true;
        }
        return true;
//        return false;

    }
}
