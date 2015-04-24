package com.yanmo.wechat.domain.message;

import com.google.common.collect.Lists;
import com.yanmo.wechat.domain.BaseKvPairDO;

import java.io.Serializable;
import java.util.List;

/**
 * Created by yanmo.yx on 2015/4/18.
 */
public class MsgDO implements Serializable {

    private static final long serialVerisonUID = 6750852852071535411L;

    private List<BaseKvPairDO> properties;
    private String features;
    private long options;

    // 消息公用属性
    private String toUserName;
    private String fromUserName;
    private long createTime;
    private String msgType;

    public List<BaseKvPairDO> getProperties() {
        return properties;
    }

    public void setProperties(List<BaseKvPairDO> properties) {
        this.properties = properties;
    }

    public long getOptions() {
        return options;
    }

    public void setOptions(long options) {
        this.options = options;
    }

    public String getFeatures() {
        return features;
    }

    public void setFeatures(String features) {
        this.features = features;
    }

    public String getToUserName() {
        return toUserName;
    }

    public String getFromUserName() {
        return fromUserName;
    }

    public long getCreateTime() {
        return createTime;
    }

    public void setToUserName(String toUserName) {
        this.toUserName = toUserName;
    }

    public void setFromUserName(String fromUserName) {
        this.fromUserName = fromUserName;
    }

    public void setCreateTime(long createTime) {
        this.createTime = createTime;
    }

    public String getMsgType() {
        return msgType;
    }

    public void setMsgType(String msgType) {
        this.msgType = msgType;
    }

    public void addOption(long opt) {
        if (opt == 0L) {
            return;
        }
        options = options | opt;
    }

    public void removeOption(long opt) {
        if (opt == 0L) {
            return;
        }
        opt = ~opt;
        options = options & opt;
    }

    public boolean addProperty(BaseKvPairDO kv) {
        if (kv == null) {
            return false;
        }
        if (kv.getKey() == null) {
            return false;
        }
        if (properties == null) {
            properties = Lists.newArrayList();
        }
        if (!properties.isEmpty()) {
            for (BaseKvPairDO property : properties) {
                if (kv.getKey().equals(property.getKey())) {
                    property.setValue(kv.getValue());
                    return true;
                }
            }
        }
        properties.add(kv);
        return true;
    }
}
