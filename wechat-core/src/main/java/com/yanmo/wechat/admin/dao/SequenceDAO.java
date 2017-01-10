package com.yanmo.wechat.admin.dao;

/**
 * Created by yanmo.yx on 2015/5/22.
 */
public interface SequenceDAO {

    /**
     * 产生用户序列
     * @return
     */
    public Long getUserId();

    /**
     * 调整用户id的序列
     * @param id
     * @return
     */
    public boolean adjustUserSequence(Long id);

    /**
     * 产生消息序列
     * @return
     */
    public Long getMessageId();

    /**
     * 产生record序列
     *
     * @return
     */
    public Long getRecordId();
}
