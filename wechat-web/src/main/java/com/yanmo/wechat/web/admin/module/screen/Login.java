package com.yanmo.wechat.web.admin.module.screen;

import com.alibaba.citrus.turbine.Context;
import com.alibaba.citrus.turbine.TurbineRunData;
import com.yanmo.wechat.web.common.UserContext;

/**
 * Created by yanmo.yx on 2015/4/24.
 */
public class Login {
    public void execute(TurbineRunData rundata, Context context) {
        if (UserContext.getUser() != null) {
            rundata.setRedirectLocation("/weixin/admin/my_wechat.htm");
        }
    }
}
