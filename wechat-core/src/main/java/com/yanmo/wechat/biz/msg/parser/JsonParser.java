package com.yanmo.wechat.biz.msg.parser;

import com.alibaba.fastjson.JSON;
import com.yanmo.wechat.domain.ResultDO;
import com.yanmo.wechat.domain.error.*;
import com.yanmo.wechat.domain.json.BaseJsonDO;
import com.yanmo.wechat.log.WxLog;
import org.springframework.util.StringUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by yanmo.yx on 2015/5/9.
 */
public class JsonParser extends BaseParser {

    private Map<Class<? extends BaseJsonDO>, List<String>> jsonMaps;

    public Map<Class<? extends BaseJsonDO>, List<String>> getJsonMaps() {
        return jsonMaps;
    }

    public void setJsonMaps(Map<String, List<String>> jsonMaps) {
        for (String key : jsonMaps.keySet()) {
            if (key != null && !key.isEmpty()) {
                List<String> keyWords = jsonMaps.get(key);
                if (keyWords != null && !keyWords.isEmpty()) {
                    Class<?> clazz = null;
                    try {
                        clazz = Thread.currentThread().getContextClassLoader().loadClass(key);
                    } catch (ClassNotFoundException e) {
                        e.printStackTrace();
                        continue;
                    }
                    if (clazz != null && BaseJsonDO.class.isAssignableFrom(clazz)) {
                        if (this.jsonMaps == null) {
                            this.jsonMaps = new HashMap<>();
                        }
                        this.jsonMaps.put((Class<? extends BaseJsonDO>) clazz, jsonMaps.get(key));
                        continue;
                    }
                    WxLog.log("类错误，加载类失败");
                    continue;
                }
                WxLog.log("关键词错误");
            }
            WxLog.log("类错误");
        }
    }

    public ResultDO<BaseJsonDO> parseJson(String json) {
        ResultDO result = new ResultDO();
        if (StringUtils.isEmpty(json)) {
            return result;
        }

        for (Class<? extends BaseJsonDO> clazz : jsonMaps.keySet()) {
            if (matchKeyWords(json, jsonMaps.get(clazz))) {
                BaseJsonDO res = JSON.parseObject(json, clazz);
                result.setModule(res);
                return result;
            }
        }
        result.addError(Errors.PARSE_JSON_ERROR);
        return result;
    }

    public static void main(String[] args) {
        Map<String, String> map = new HashMap<>();
        map.put("supplierIds", "1001,231,32131");
        map.put("tables", "1000, 2000");
        String json = JSON.toJSONString(map);
        System.out.println(json);
    }
}
