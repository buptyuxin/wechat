package com.yanmo.wechat.web.admin.module.screen;

import com.alibaba.citrus.service.template.TemplateContext;
import com.alibaba.citrus.turbine.Context;
import com.alibaba.citrus.turbine.TurbineRunData;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.yanmo.wechat.admin.module.RecordReadModule;
import com.yanmo.wechat.admin.module.TextReadModule;
import com.yanmo.wechat.domain.TestDO;
import com.yanmo.wechat.domain.admin.RecordDO;
import com.yanmo.wechat.domain.admin.TextDO;
import com.yanmo.wechat.domain.admin.UserDO;
import com.yanmo.wechat.log.LogBackInit;
import com.yanmo.wechat.log.WxLog;
import com.yanmo.wechat.web.common.UserContext;

import java.util.List;
import java.util.WeakHashMap;

/**
 * Created by yanmo.yx on 2015/4/24.
 */
public class MyWechat {

    private TextReadModule textReadModule;

    private RecordReadModule recordReadModule;

    public void setRecordReadModule(RecordReadModule recordReadModule) {
        this.recordReadModule = recordReadModule;
    }

    public void setTextReadModule(TextReadModule textReadModule) {
        this.textReadModule = textReadModule;
    }

    public void execute(TurbineRunData rundata, Context context) {
        // webx 3.2.4不再支持其他的context以及rundata，具体参考
        // com.alibaba.citrus.turbine.dataresolver.impl.TurbineRunDataResolverFactory
        TemplateContext cc = null;
        TestDO testDO = new TestDO();
        testDO.setName("i am your father");
        UserDO userDO = UserContext.getUser();
        if (userDO == null) {
            context.put("userNick", "其实没用户，赶紧给我跳到登录页面");
            rundata.setRedirectLocation("/weixin/login.htm");
        } else {
            context.put("userNick", userDO.getName());
        }
        context.put("userPwd", "******");
        context.put("testDO", testDO);

//        展示小二发布的文本
        List<RecordDO> records = Lists.newArrayList();
        try {
            records = (List<RecordDO>) recordReadModule.queryRecordByUserId(userDO.getId());
        } catch (Exception e) {
            System.out.println(e);
        }

        List<TextDO> texts = Lists.newArrayList();

        for (RecordDO record : records) {
            TextDO textDO = textReadModule.queryTextById(record.getTextId());
            texts.add(textDO);
        }

        context.put("texts", texts);

        return;
    }
}
