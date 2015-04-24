package com.yanmo.wechat.web.admin.module.action;

import com.alibaba.citrus.service.form.Form;
import com.alibaba.citrus.service.form.FormService;
import com.alibaba.citrus.service.form.Group;
import com.alibaba.citrus.service.template.TemplateContext;
import com.alibaba.citrus.turbine.TurbineRunData;
import org.apache.commons.lang.StringUtils;

/**
 * Created by yanmo.yx on 2015/4/24.
 */
public class LoginAction {

    private FormService formService;

    public void setFormService(FormService formService) {
        this.formService = formService;
    }

    public void doLogin(TurbineRunData rundata, TemplateContext context) {
        Form form = formService.getForm();

        if (form.isValid()) {
            Group group = form.getGroup("login");
            String userNick = group.getField("userNick").getStringValue();
            String userPwd = group.getField("userPwd").getStringValue();
            if (StringUtils.isNotBlank(userNick) && StringUtils.isNotBlank(userPwd)) {
                // 内部跳转到成功页面
                context.put("userNick", userNick);
                context.put("userPwd", userPwd);
                rundata.setRedirectTarget("my_wechat.vm");
            }
        }
    }
}
