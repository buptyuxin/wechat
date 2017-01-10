package com.yanmo.wechat.admin.dao.impl;

import com.yanmo.wechat.admin.dao.UserDAO;
import com.yanmo.wechat.domain.admin.UserDO;
import org.apache.ibatis.session.SqlSession;

/**
 * Created by yanmo.yx on 2015/5/9.
 */
public class UserDAOImpl implements UserDAO {

    private final String NS = "com.yanmo.wechat.admin.dao.UserMapper";

    // 对应mybatis.xml中的
    private SqlSession sqlSessionUser;

    @Override
    public UserDO queryUserByName(String name) {
        return sqlSessionUser.selectOne(NS + ".getUserByName", name);
    }

    @Override
    public UserDO queryUserById(Long id) {
        return sqlSessionUser.selectOne(NS + ".getUserById", id);
    }

    @Override
    public boolean insertUser(UserDO userDO) {
        if (sqlSessionUser.insert(NS + ".insert", userDO) > 0) {
            return true;
        }
        return false;
    }

    @Override
    public boolean updateUser(UserDO userDO) {
        if (sqlSessionUser.update(NS + ".update", userDO) > 0) {
            return true;
        }
        return false;
    }

    public void setSqlSessionUser(SqlSession sqlSessionUser) {
        this.sqlSessionUser = sqlSessionUser;
    }
}
