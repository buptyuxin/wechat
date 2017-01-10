package com.yanmo.wechat.web.common.pipeline;

import com.alibaba.citrus.service.pipeline.PipelineContext;
import com.alibaba.citrus.service.pipeline.support.AbstractValve;
import com.yanmo.wechat.web.common.UserContext;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by yanmo.yx on 2015/5/22.
 */
public class CleanValve extends AbstractValve {

    private HttpServletRequest request;

    public void setRequest(HttpServletRequest request) {
        this.request = request;
    }

    @Override
    public void invoke(PipelineContext pipelineContext) throws Exception {
        UserContext.clearUser();
        pipelineContext.invokeNext();
    }
}
