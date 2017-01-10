package com.yanmo.wechat.admin.module.impl;

import com.yanmo.wechat.admin.dao.UserDAO;
import com.yanmo.wechat.admin.module.UserReadModule;
import com.yanmo.wechat.domain.admin.UserDO;

/**
 * Created by yanmo.yx on 2015/5/9.
 */
public class UserReadModuleImpl implements UserReadModule {

    private UserDAO userDAO;

    public void setUserDAO(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    @Override
    public UserDO queryUserByName(String name) {
        return userDAO.queryUserByName(name);
    }

    @Override
    public UserDO queryUserById(Long id) {
        return userDAO.queryUserById(id);
    }
}
