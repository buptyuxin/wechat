package com.yanmo.wechat.web.admin.module.action;

import com.alibaba.citrus.service.form.Form;
import com.alibaba.citrus.service.form.FormService;
import com.alibaba.citrus.service.form.Group;
import com.alibaba.citrus.turbine.Context;
import com.alibaba.citrus.turbine.TurbineRunData;
import com.yanmo.wechat.admin.module.UserReadModule;
import com.yanmo.wechat.domain.admin.UserDO;
import com.yanmo.wechat.web.common.UserContext;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by yanmo.yx on 2015/4/24.
 */
public class LoginAction {

    private FormService formService;

    private UserReadModule userReadModule;

    public void doLogin(TurbineRunData rundata, Context context) {
        Form form = formService.getForm();

        if (form.isValid()) {
            Group group = form.getGroup("login");
            String userNick = group.getField("usernick").getStringValue();
            String userPwd = group.getField("userpwd").getStringValue();
            if (StringUtils.isNotBlank(userNick) && StringUtils.isNotBlank(userPwd)) {
                // 内部跳转到成功页面
                UserDO userDO = userReadModule.queryUserByName(userNick);
                if (userDO == null) {
                    // 没找到用户信息，跳转注册页面
                    rundata.setRedirectTarget("/user/register.vm");
                    return;
                }
                if (userPwd.equals(userDO.getPwd())) {
                    context.put("userNick", userNick);
                    context.put("userPwd", userPwd);
                    UserContext.initUser(userReadModule, userNick);
                    rundata.getRequest().getSession().setAttribute("nick", userNick);
                    return;
                }
                // 密码验证失败，提示密码错误
                context.put("error", "密码错误，请重新填写");
            }
        }
    }

    public void setFormService(FormService formService) {
        this.formService = formService;
    }

    public void setUserReadModule(UserReadModule userReadModule) {
        this.userReadModule = userReadModule;
    }
}
