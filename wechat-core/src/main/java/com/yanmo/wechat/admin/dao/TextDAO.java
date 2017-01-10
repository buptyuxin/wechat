package com.yanmo.wechat.admin.dao;

import com.yanmo.wechat.domain.admin.TextDO;

/**
 * Created by yanmo.yx on 2015/9/21.
 */
public interface TextDAO {

    public TextDO getTextById(Long id);

    public boolean insertText(TextDO text);
}
