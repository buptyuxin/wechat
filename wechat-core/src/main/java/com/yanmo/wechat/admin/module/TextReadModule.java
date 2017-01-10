package com.yanmo.wechat.admin.module;

import com.yanmo.wechat.domain.admin.TextDO;

/**
 * Created by yanmo.yx on 2015/9/21.
 */
public interface TextReadModule {
    public TextDO queryTextById(Long id);
}
