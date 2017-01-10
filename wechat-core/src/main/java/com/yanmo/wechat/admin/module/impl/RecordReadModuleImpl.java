package com.yanmo.wechat.admin.module.impl;

import com.yanmo.wechat.admin.dao.RecordDAO;
import com.yanmo.wechat.admin.module.RecordReadModule;
import com.yanmo.wechat.domain.admin.RecordDO;

import java.util.List;

/**
 * Created by yanmo.yx on 2015/9/24.
 */
public class RecordReadModuleImpl implements RecordReadModule {

    private RecordDAO recordDAO;

    public void setRecordDAO(RecordDAO recordDAO) {
        this.recordDAO = recordDAO;
    }

    @Override
    public List<RecordDO> queryRecordByUserId(Long userId) {
        return recordDAO.getRecordByUserId(userId);
    }

    @Override
    public List<RecordDO> queryRecordByTextId(Long textId) {
        return recordDAO.getRecordByTextId(textId);
    }
}
