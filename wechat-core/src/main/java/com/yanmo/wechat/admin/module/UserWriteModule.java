package com.yanmo.wechat.admin.module;

import com.yanmo.wechat.domain.admin.UserDO;

/**
 * Created by yanmo.yx on 2015/5/22.
 */
public interface UserWriteModule {
    public boolean insertUser(UserDO userDO);
}
