package com.yanmo.wechat.biz.service.impl;

import com.yanmo.wechat.biz.msg.processor.MsgProcessor;
import com.yanmo.wechat.biz.service.MsgProcessService;
import com.yanmo.wechat.domain.BaseKvPairDO;
import com.yanmo.wechat.domain.message.MsgDO;
import com.yanmo.wechat.log.WxLog;

import java.util.List;

/**
 * Created by yanmo.yx on 2015/4/18.
 */
public class MsgProcessServiceImpl implements MsgProcessService {

    private List<MsgProcessor> msgProcessors;

    public void setMsgProcessors(List<MsgProcessor> msgProcessors) {
        this.msgProcessors = msgProcessors;
    }

    @Override
    public MsgDO processMsg(MsgDO msgDO) {

        if (msgDO.getMsgType() == null) {
            WxLog.log("接收用户消息解析错误" + msgDO);
            return null;
        }
        for (MsgProcessor processor : msgProcessors) {
            if (processor.getMsgType().equals(msgDO.getMsgType())) {
                return processor.process(msgDO);
            }
        }
        return null;
    }
}
