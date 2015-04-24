package com.yanmo.wechat.biz.service;

import com.yanmo.wechat.domain.message.MsgDO;

/**
 * Created by yanmo.yx on 2015/4/18.
 */
public interface MsgProcessService {
    public MsgDO processMsg(MsgDO msgDO);
}
