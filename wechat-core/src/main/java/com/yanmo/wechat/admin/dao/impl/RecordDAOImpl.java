package com.yanmo.wechat.admin.dao.impl;

import com.yanmo.wechat.admin.dao.RecordDAO;
import com.yanmo.wechat.domain.admin.RecordDO;
import org.apache.ibatis.session.SqlSession;

import java.util.List;

/**
 * Created by yanmo.yx on 2015/9/24.
 */
public class RecordDAOImpl implements RecordDAO {

    private final String NS = "com.yanmo.wechat.admin.dao.RecordMapper";

    // 对应mybatis.xml中的
    private SqlSession sqlSessionRecord;

    public void setSqlSessionRecord(SqlSession sqlSessionRecord) {
        this.sqlSessionRecord = sqlSessionRecord;
    }

    @Override
    public boolean insertRecord(RecordDO recordDO) {
        if (sqlSessionRecord.insert(NS + ".insertRecord", recordDO) > 0) {
            return true;
        }
        return false;
    }

    @Override
    public List<RecordDO> getRecordByTextId(Long textId) {
        return sqlSessionRecord.selectList(NS + ".queryRecordByTextId", textId);
    }

    @Override
    public List<RecordDO> getRecordByUserId(Long userId) {
        return sqlSessionRecord.selectList(NS + ".queryRecordByUserId", userId);
    }
}
