package com.yanmo.wechat.admin.dao.impl;

import com.google.common.collect.Maps;
import com.yanmo.wechat.admin.dao.AdjustDO;
import com.yanmo.wechat.admin.dao.SequenceDAO;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallback;
import org.springframework.transaction.support.TransactionTemplate;

import java.util.Map;

/**
 * Created by yanmo.yx on 2015/5/22.
 */
public class SequenceDAOImpl implements SequenceDAO, InitializingBean {

    private final String NS = "com.yanmo.wechat.admin.dao.SequenceMapper";

    private final String USER_TYPE = "user_type";

    private final String MESSAGE_TYPE = "message_type";

    private final String RECORD_TYPE = "record_type";

    // 序列cache，放到内存，可以减少DB的压力
    private final Map<String, Integer> cacheSizes = Maps.newHashMap();

    // cache数量，每次取一个id就减1，为0的时候需要继续取id
    private Map<String, Integer> cacheCount = Maps.newHashMap();

    private Map<String, Long> ids = Maps.newHashMap();

    private SqlSession sqlSessionSequence;

    private TransactionTemplate sequenceTransactionTemplate;

    public void setSequenceTransactionTemplate(TransactionTemplate sequenceTransactionTemplate) {
        this.sequenceTransactionTemplate = sequenceTransactionTemplate;
    }

    public void setSqlSessionSequence(SqlSession sqlSessionSequence) {
        this.sqlSessionSequence = sqlSessionSequence;
    }

    @Override
    public Long getUserId() {
        return getId(USER_TYPE);
    }

    @Override
    public Long getMessageId() {
        return getId(MESSAGE_TYPE);
    }

    @Override
    public Long getRecordId() {
        return getId(RECORD_TYPE);
    }

    @Override
    public boolean adjustUserSequence(Long id) {
        return adjustSequence(id, USER_TYPE);
    }

    public Long getId(final String type) {
        // 同步下，其实这里做事务没有作用，事务应该是在业务逻辑中使用，保证业务逻辑的一致的
        // 这里就用事务玩玩吧
        synchronized (this) {
            if (cacheCount.get(type) == 0) {
                Long id = sequenceTransactionTemplate.execute(new TransactionCallback<Long>() {
                    @Override
                    public Long doInTransaction(TransactionStatus status) {
                        Long id = sqlSessionSequence.selectOne(NS + ".select", type);
                        adjustUserSequence(id + cacheSizes.get(type));
                        return id;
                    }
                });
                cacheCount.put(type, cacheSizes.get(type)-1);
                ids.put(type, id);
                return id;
            } else {
                cacheCount.put(type, cacheCount.get(type) - 1);
                return ids.get(type) + 1;
            }
        }
    }

    private boolean adjustSequence(Long id, String type) {
        AdjustDO adjustDO = new AdjustDO();
        adjustDO.setId(id);
        adjustDO.setType(type);
        return sqlSessionSequence.update(NS + ".update", adjustDO) > 0;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        cacheSizes.put(USER_TYPE, 10);
        cacheSizes.put(MESSAGE_TYPE, 10);
        cacheSizes.put(RECORD_TYPE, 10);

        cacheCount.put(USER_TYPE, 0);
        cacheCount.put(MESSAGE_TYPE, 0);
        cacheCount.put(RECORD_TYPE, 0);

        ids.put(USER_TYPE, 0L);
        ids.put(MESSAGE_TYPE, 0L);
        ids.put(RECORD_TYPE, 0L);
    }
}
