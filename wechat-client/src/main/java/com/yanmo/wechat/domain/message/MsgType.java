package com.yanmo.wechat.domain.message;

import org.apache.commons.lang3.StringUtils;

/**
 * Created by yanmo.yx on 2015/4/18.
 */
public enum MsgType {
    TEXT_MSG(1, "text"),
    IMAGE_MSG(2, "image");

    private int type;
    private String name;
    MsgType(int type, String name) {
        this.type = type;
        this.name = name;
    }

    public int getType() {
        return type;
    }

    public String getName() {
        return name;
    }

    public static MsgType getMsgTypeByName(String name) {
        if (name == null || StringUtils.isBlank(name)) {
            return null;
        }
        for (MsgType msgType : MsgType.values()) {
            if (msgType.getName().equals(name)) {
                return msgType;
            }
        }
        return null;
    }

    public static String getName(int type) {
        if (type > 0) {
            for (MsgType msgType : MsgType.values()) {
                if (msgType.getType() == type) {
                    return msgType.getName();
                }
            }
        }
        return null;
    }

    @Override
    public String toString() {
        return name;
    }
}
