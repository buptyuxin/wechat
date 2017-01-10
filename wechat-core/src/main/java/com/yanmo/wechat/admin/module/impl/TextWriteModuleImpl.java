package com.yanmo.wechat.admin.module.impl;

import com.yanmo.wechat.admin.dao.RecordDAO;
import com.yanmo.wechat.admin.dao.SequenceDAO;
import com.yanmo.wechat.admin.dao.TextDAO;
import com.yanmo.wechat.admin.dao.UserDAO;
import com.yanmo.wechat.admin.module.TextWriteModule;
import com.yanmo.wechat.domain.admin.RecordDO;
import com.yanmo.wechat.domain.admin.TextDO;
import com.yanmo.wechat.domain.admin.UserDO;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallback;
import org.springframework.transaction.support.TransactionTemplate;

/**
 * Created by yanmo.yx on 2015/9/21.
 */
public class TextWriteModuleImpl implements TextWriteModule {

    private TextDAO textDAO;
    private RecordDAO recordDAO;

    public void setRecordDAO(RecordDAO recordDAO) {
        this.recordDAO = recordDAO;
    }

    private SequenceDAO sequenceDAO;

    private TransactionTemplate distributedTransactionTemplate;

    public void setDistributedTransactionTemplate(TransactionTemplate distributedTransactionTemplate) {
        this.distributedTransactionTemplate = distributedTransactionTemplate;
    }

    public void setSequenceDAO(SequenceDAO sequenceDAO) {
        this.sequenceDAO = sequenceDAO;
    }

    public void setTextDAO(TextDAO textDAO) {
        this.textDAO = textDAO;
    }

    @Override
    public boolean insertText(String text, Long userId) {
        final TextDO textDO = new TextDO();
        textDO.setText(text);
        Long textId = sequenceDAO.getMessageId();
        textDO.setId(textId);
        textDO.setUserId(userId);

        Long recordId = sequenceDAO.getRecordId();
        final RecordDO recordDO = new RecordDO();
        recordDO.setUserId(userId);
        recordDO.setId(recordId);
        recordDO.setTextId(textId);

        // TODO 这里做下分布式事务，同时插入text表和record表
        return  distributedTransactionTemplate.execute(new TransactionCallback<Boolean>() {
            @Override
            public Boolean doInTransaction(TransactionStatus status) {
                textDAO.insertText(textDO);
                recordDAO.insertRecord(recordDO);
                return null;
            }
        });
    }
}
