package com.yanmo.wechat.biz.msg.processor;

import com.yanmo.wechat.biz.msg.rule.ReplyRuleDO;
import com.yanmo.wechat.domain.BaseKvPairDO;
import com.yanmo.wechat.domain.ResultDO;
import com.yanmo.wechat.domain.message.MsgDO;

import java.util.Map;

/**
 * Created by yanmo.yx on 2015/4/18.
 */
public abstract class BaseMsgProcessor implements MsgProcessor {

    private ReplyRuleDO replyRuleDO;

    public void setReplyRuleDO(ReplyRuleDO replyRuleDO) {
        this.replyRuleDO = replyRuleDO;
    }

    @Override
    public MsgDO process(MsgDO recvMsg) {
        if (recvMsg == null) {
            // TODO
            return null;
        }

        ResultDO<MsgDO> result = processMsg(recvMsg);
        if (!result.isSuccess()) {
            // TODO 处理失败，日志记录
        }
        MsgDO replyMsg = result.getModule();

        // 填充发送方、接收方、创建时间等公共属性
        fillReplyMsg(recvMsg, replyMsg);
        return replyMsg;
    }

    private void fillReplyMsg(MsgDO recvMsg, MsgDO replyMsg) {
        // 对应回复规则，主要是发送、接收方
        Map<String, String> replyRule = replyRuleDO.getRuleMap();
        for (String key : replyRule.keySet()) {
            BaseKvPairDO replyProperty = new BaseKvPairDO();
            for (BaseKvPairDO kv : recvMsg.getProperties()) {
                if (key.equals(kv.getKey())) {
                    replyProperty.setKey(replyRule.get(key));
                    replyProperty.setValue(kv.getValue());
                    replyMsg.addProperty(replyProperty);
                    break;
                }
            }
        }

        // 回复消息时间
        BaseKvPairDO createTime = new BaseKvPairDO();
        createTime.setKey("CreateTime");
        createTime.setValue(String.valueOf(System.currentTimeMillis()));
        replyMsg.addProperty(createTime);
    }

    public abstract ResultDO<MsgDO> processMsg(MsgDO msgDO);

    @Override
    public abstract String getMsgType();
}
