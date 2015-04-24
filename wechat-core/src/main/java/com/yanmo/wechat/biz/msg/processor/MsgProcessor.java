package com.yanmo.wechat.biz.msg.processor;

import com.yanmo.wechat.domain.message.MsgDO;

/**
 * Created by yanmo.yx on 2015/4/18.
 */
public interface MsgProcessor {
    public MsgDO process(MsgDO recvMsg);

    public String getMsgType();
}
