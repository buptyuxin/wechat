package com.yanmo.wechat.admin.dao;

import com.yanmo.wechat.domain.admin.UserDO;

/**
 * Created by yanmo.yx on 2015/5/9.
 */
public interface UserDAO {

    /**
     * 从DB查询用户
     *
     * @param name
     * @return
     */
    public UserDO queryUserByName(String name);

    /**
     * 从DB查询用户
     *
     * @param id
     * @return
     */
    public UserDO queryUserById(Long id);

    /**
     * 向DB插入新用户
     *
     * @param userDO
     */
    public boolean insertUser(UserDO userDO);

    /**
     * 更新DB用户数据
     *
     * @param userDO
     */
    public boolean updateUser(UserDO userDO);
}
