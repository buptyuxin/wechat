package com.yanmo.wechat.biz.msg.processor.message;

import com.google.common.collect.Lists;
import com.yanmo.wechat.biz.msg.processor.BaseMsgProcessor;
import com.yanmo.wechat.domain.BaseKvPairDO;
import com.yanmo.wechat.domain.ResultDO;
import com.yanmo.wechat.domain.message.MsgDO;

import java.util.List;
import java.util.Random;

/**
 * Created by yanmo.yx on 2015/4/24.
 */
public class UserMsgProcessor extends BaseMsgProcessor {

    private Random r = new Random();

    private List<String> messages;

    public void setMessages(List<String> messages) {
        this.messages = messages;
    }

    @Override
    public ResultDO<MsgDO> processMsg(MsgDO msgDO) {
        ResultDO<MsgDO> result = new ResultDO<>();
        MsgDO replyMsg = new MsgDO();
        if (messages == null || messages.isEmpty()) {
            BaseKvPairDO contentKv = new BaseKvPairDO();
            contentKv.setKey("Content");
            contentKv.setValue("您好，这是鱼子酱的测试服务帐号");
            BaseKvPairDO msgTypeKv = new BaseKvPairDO();
            msgTypeKv.setKey("MsgType");
            msgTypeKv.setValue("Text");
            replyMsg.setProperties(Lists.newArrayList(msgTypeKv, contentKv));
        } else {
            BaseKvPairDO contentKv = new BaseKvPairDO();
            contentKv.setKey("Content");
            contentKv.setValue(messages.get(r.nextInt(messages.size())));
            BaseKvPairDO msgTypeKv = new BaseKvPairDO();
            msgTypeKv.setKey("MsgType");
            msgTypeKv.setValue("Text");
            replyMsg.setProperties(Lists.newArrayList(msgTypeKv, contentKv));
        }
        result.setModule(replyMsg);
        return result;
    }

    @Override
    public String getMsgType() {
        return "Text";
    }
}
