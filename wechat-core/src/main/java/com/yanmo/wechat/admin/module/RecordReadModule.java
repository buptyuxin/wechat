package com.yanmo.wechat.admin.module;

import com.yanmo.wechat.domain.admin.RecordDO;

import java.util.List;

/**
 * Created by yanmo.yx on 2015/9/24.
 */
public interface RecordReadModule {
    public List<RecordDO> queryRecordByUserId(Long userId);

    public List<RecordDO> queryRecordByTextId(Long textId);
}
