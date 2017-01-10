package com.yanmo.wechat.admin.dao;

import com.yanmo.wechat.domain.admin.RecordDO;

import java.util.List;

/**
 * Created by yanmo.yx on 2015/9/23.
 */
public interface RecordDAO {
    public boolean insertRecord(RecordDO recordDO);

    public List<RecordDO> getRecordByTextId(Long textId);

    public List<RecordDO> getRecordByUserId(Long userId);
}
