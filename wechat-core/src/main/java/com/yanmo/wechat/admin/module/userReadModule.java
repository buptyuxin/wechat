package com.yanmo.wechat.admin.module;

import com.yanmo.wechat.domain.admin.UserDO;

/**
 * Created by yanmo.yx on 2015/5/9.
 */
public interface UserReadModule {
    UserDO queryUserByName(String name);
    UserDO queryUserById(Long id);
}
