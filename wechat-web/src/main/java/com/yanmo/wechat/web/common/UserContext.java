package com.yanmo.wechat.web.common;

import com.yanmo.wechat.admin.module.UserReadModule;
import com.yanmo.wechat.domain.admin.UserDO;
import com.yanmo.wechat.log.WxLog;
import org.apache.commons.lang3.StringUtils;

/**
 * Created by yanmo.yx on 2015/5/20.
 */
public class UserContext {
    private final static ThreadLocal<UserDO> holder = new ThreadLocal<UserDO>();

    /**
     * 用来根据session初始化用户数据
     *
     * @param userReadModule
     * @param nick
     */
    public static UserDO initUser(UserReadModule userReadModule, String nick) {
        if (userReadModule == null) {
            WxLog.log("MyValve 注入 userReadModule失败，空指针");
            return null;
        }
        if (StringUtils.isBlank(nick)) {
            return null;
        }
        UserDO userDO = userReadModule.queryUserByName(nick);
        if (userDO == null) {
            WxLog.log("MyValve 查找用户失败");
            return null;
        }
        holder.set(userDO);
        return userDO;
    }

    public static void setUser(UserDO userDO) {
        if (userDO != null) {
            holder.remove();
            holder.set(userDO);
        }
    }

    public static UserDO getUser() {
        return holder.get();
    }

    public static void clearUser() {
        holder.remove();
    }
}
