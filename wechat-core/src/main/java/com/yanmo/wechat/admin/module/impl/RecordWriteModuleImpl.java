package com.yanmo.wechat.admin.module.impl;

import com.yanmo.wechat.admin.dao.RecordDAO;
import com.yanmo.wechat.admin.dao.SequenceDAO;
import com.yanmo.wechat.admin.module.RecordWriteModule;
import com.yanmo.wechat.domain.admin.RecordDO;

/**
 * Created by yanmo.yx on 2015/9/24.
 */
public class RecordWriteModuleImpl implements RecordWriteModule {

    private RecordDAO recordDAO;

    private SequenceDAO sequenceDAO;

    public void setSequenceDAO(SequenceDAO sequenceDAO) {
        this.sequenceDAO = sequenceDAO;
    }

    public void setRecordDAO(RecordDAO recordDAO) {
        this.recordDAO = recordDAO;
    }

    @Override
    public boolean insertRecord(RecordDO recordDO) {
        return recordDAO.insertRecord(recordDO);
    }
}
