package com.yanmo.wechat.biz.msg.parser;

import java.util.List;

/**
 * Created by yanmo.yx on 2015/5/9.
 */
public abstract class BaseParser {

    protected boolean matchKeyWords(String str, List<String> keyWords) {
        if (keyWords != null && !keyWords.isEmpty()) {
            for (String keyWord : keyWords) {
                // 必须包含所有的关键词
                if (!str.contains(keyWord)) {
                    return false;
                }
            }
            return true;
        }
        return false;
    }
}
