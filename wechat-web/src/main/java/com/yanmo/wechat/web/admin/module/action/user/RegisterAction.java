package com.yanmo.wechat.web.admin.module.action.user;

import com.alibaba.citrus.service.form.Form;
import com.alibaba.citrus.service.form.FormService;
import com.alibaba.citrus.service.form.Group;
import com.alibaba.citrus.turbine.Context;
import com.alibaba.citrus.turbine.TurbineRunData;
import com.yanmo.wechat.admin.dao.SequenceDAO;
import com.yanmo.wechat.admin.module.UserReadModule;
import com.yanmo.wechat.admin.module.UserWriteModule;
import com.yanmo.wechat.domain.admin.UserDO;
import com.yanmo.wechat.web.common.UserContext;
import org.apache.commons.lang.StringUtils;

/**
 * Created by yanmo.yx on 2015/5/22.
 */
public class RegisterAction {

    private FormService formService;

    private UserReadModule userReadModule;
    private SequenceDAO sequenceDAO;
    private UserWriteModule userWriteModule;

    public void doRegister(TurbineRunData rundata, Context context) {
        Form form = formService.getForm();

        if (form.isValid()) {
            Group group = form.getGroup("register");
            String userNick = group.getField("usernick").getStringValue();
            String userPwd = group.getField("userpwd").getStringValue();
            if (StringUtils.isNotBlank(userNick) && StringUtils.isNotBlank(userPwd)) {
                // 内部跳转到成功页面
                UserDO userDO = userReadModule.queryUserByName(userNick);
                if (userDO == null) {
                    // 没找到用户信息，跳转注册页面
                    UserDO newUser = new UserDO();
                    Long id = sequenceDAO.getUserId();
                    newUser.setId(id);
                    newUser.setName(userNick);
                    newUser.setPwd(userPwd);
                    userWriteModule.insertUser(newUser);
                    UserContext.clearUser();
                    UserContext.setUser(newUser);
                    rundata.setRedirectTarget("/admin/my_wechat.vm");
                    rundata.getRequest().getSession().setAttribute("nick", userNick);
                    return;
                }
                // 用户名已被使用
                context.put("error", "该用户名已被使用");
            }
        }
    }

    public void setSequenceDAO(SequenceDAO sequenceDAO) {
        this.sequenceDAO = sequenceDAO;
    }

    public void setUserWriteModule(UserWriteModule userWriteModule) {
        this.userWriteModule = userWriteModule;
    }

    public void setFormService(FormService formService) {
        this.formService = formService;
    }

    public void setUserReadModule(UserReadModule userReadModule) {
        this.userReadModule = userReadModule;
    }
}
