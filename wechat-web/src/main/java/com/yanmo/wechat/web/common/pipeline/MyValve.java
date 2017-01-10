package com.yanmo.wechat.web.common.pipeline;

import com.alibaba.citrus.service.mappingrule.MappingRuleService;
import com.alibaba.citrus.service.pipeline.PipelineContext;
import com.alibaba.citrus.service.pipeline.support.AbstractValve;
import com.alibaba.citrus.turbine.TurbineConstant;
import com.alibaba.citrus.turbine.TurbineRunDataInternal;
import com.alibaba.citrus.turbine.util.TurbineUtil;
import com.yanmo.wechat.admin.module.UserReadModule;
import com.yanmo.wechat.domain.admin.UserDO;
import com.yanmo.wechat.web.common.UserContext;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by yanmo.yx on 2015/5/11.
 */
public class MyValve extends AbstractValve {

    private HttpServletRequest request;

    private UserReadModule userReadModule;

    private MappingRuleService mappingRuleService;

    @Override
    public void invoke(PipelineContext pipelineContext) throws Exception {
        TurbineRunDataInternal rundata = (TurbineRunDataInternal) TurbineUtil.getTurbineRunData(request);
        String target = rundata.getTarget();
        String nick = (String) request.getSession().getAttribute("nick");
        UserDO userDO = UserContext.initUser(userReadModule, nick);
//        if (UserContext.getUser() == null) {
//            userDO = UserContext.initUser(userReadModule, nick);
//        }
        String test = request.getParameter("test");
        if (target.startsWith("/default")) {
            rundata.setRedirectLocation("login.htm");
            pipelineContext.invokeNext();
            return;
        } else if (!target.startsWith("/home") && !target.startsWith("/login") && !target.startsWith("/admin/login")
                && !target.startsWith("/user/register")) {
            // 这里是对URL做分析，如果是微信的页面则不做用户的校验
            if (nick == null || userDO == null) {
                rundata.setRedirectLocation("/weixin/login.htm");
                pipelineContext.invokeNext();
                return;
            }
        }
        target = mappingRuleService.getMappedName(TurbineConstant.EXTENSION_INPUT, target);
        rundata.setTarget(target);
        pipelineContext.invokeNext();
    }

    public void setRequest(HttpServletRequest request) {
        this.request = request;
    }

    public void setMappingRuleService(MappingRuleService mappingRuleService) {
        this.mappingRuleService = mappingRuleService;
    }

    public void setUserReadModule(UserReadModule userReadModule) {
        this.userReadModule = userReadModule;
    }
}
