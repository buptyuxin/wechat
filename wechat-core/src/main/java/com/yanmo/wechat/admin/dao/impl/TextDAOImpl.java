package com.yanmo.wechat.admin.dao.impl;

import com.yanmo.wechat.admin.dao.TextDAO;
import com.yanmo.wechat.domain.admin.TextDO;
import org.apache.ibatis.session.SqlSession;

/**
 * Created by yanmo.yx on 2015/9/21.
 */
public class TextDAOImpl implements TextDAO {

    private final String NS = "com.yanmo.wechat.admin.dao.TextMapper";

    // 对应mybatis.xml中的
    private SqlSession sqlSessionText;

    public void setSqlSessionText(SqlSession sqlSessionText) {
        this.sqlSessionText = sqlSessionText;
    }

    @Override
    public TextDO getTextById(Long id) {
        return sqlSessionText.selectOne(NS + ".getTextById", id);
    }

    @Override
    public boolean insertText(TextDO text) {
        if (sqlSessionText.insert(NS + ".insert", text) > 0) {
            return true;
        }
        return false;
    }
}
