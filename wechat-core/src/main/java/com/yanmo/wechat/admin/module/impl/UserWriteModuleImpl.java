package com.yanmo.wechat.admin.module.impl;

import com.yanmo.wechat.admin.dao.UserDAO;
import com.yanmo.wechat.admin.module.UserWriteModule;
import com.yanmo.wechat.domain.admin.UserDO;

/**
 * Created by yanmo.yx on 2015/5/22.
 */
public class UserWriteModuleImpl implements UserWriteModule {

    private UserDAO userDAO;

    public void setUserDAO(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    @Override
    public boolean insertUser(UserDO userDO) {
        if (userDO.getName() == null) {
            return false;
        }
        if (userDO.getId() == null) {
            return false;
        }
        if (userDO.getPwd() == null) {
            return false;
        }
        return userDAO.insertUser(userDO);

    }
}
