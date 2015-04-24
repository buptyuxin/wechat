package com.yanmo.wechat.web.common.utils;

import com.google.common.collect.Lists;
import com.yanmo.wechat.domain.BaseKvPairDO;
import com.yanmo.wechat.domain.message.MsgDO;
import com.yanmo.wechat.domain.message.MsgType;
import com.yanmo.wechat.log.WxLog;
import org.apache.commons.lang.math.NumberUtils;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.io.StringWriter;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Iterator;
import java.util.List;

/**
 * Created by yanmo.yx on 2015/4/18.
 */
public class MsgConvertUtils {

    private static final List<String> MSG_COMMON_PROPERTIES = Lists.newArrayList(
            "ToUserName",
            "FromUserName",
            "CreateTime",
            "MsgType"
    );

    private static final String METHOD_SET_PREFIX = "set";
    private static final String METHOD_GET_PREFIX = "get";

    public static MsgDO convertMsg(HttpServletRequest req) {
        MsgDO msgDO = new MsgDO();
        // 自己做的解析
        Document doc = null;
        SAXReader saxReader = new SAXReader();
        try {
            doc = saxReader.read(req.getInputStream());
            Element root = doc.getRootElement();
            Iterator it = root.elementIterator();
            while (it.hasNext()) {
                Element element = (Element) it.next();
                if (parseMsgCommonProps(msgDO, element)) {
                    continue;
                }
                BaseKvPairDO kv = new BaseKvPairDO();
                parseElement(element, kv);
                msgDO.addProperty(kv);
            }
        } catch (DocumentException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            WxLog.log("接收微信消息自动注入参数错误");
        } catch (InvocationTargetException e) {
            WxLog.log("接收微信消息自动注入对象错误");
        }
        return msgDO;
    }

    private static void parseElement(Element element, BaseKvPairDO kv) {
        List<Element> childs = element.elements();
        List<BaseKvPairDO> subPairs = kv.getSubPairs();
        if (!kv.hasSubPairs()) {
            subPairs = Lists.newArrayList();
            kv.setSubPairs(subPairs);
        }
        if (childs != null && !childs.isEmpty()) {
            for (Element child : childs) {
                BaseKvPairDO childKv = new BaseKvPairDO();
                parseElement(child, childKv);
                subPairs.add(childKv);
            }
        }
        String key = element.getName();
        String value = element.getText();
        kv.setKey(key);
        kv.setValue(value);
    }

    private static boolean parseMsgCommonProps(MsgDO msgDO, Element element) throws InvocationTargetException, IllegalAccessException {
        for (String propName : MSG_COMMON_PROPERTIES) {
            if (propName.equalsIgnoreCase(element.getName())) {
                // 通过反射把属性注入到消息对象中
                for (Method method : msgDO.getClass().getDeclaredMethods()) {
                    if (method.getName().equalsIgnoreCase(METHOD_SET_PREFIX + propName)) {
                        method.invoke(msgDO, element.getText());
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public static String fromMsg2Xml(MsgDO msgDO) {

        if (msgDO == null) {
            return "success";
        }

        Document doc = DocumentHelper.createDocument();
        Element root = doc.addElement("xml");

        try {
            dealCommonProperties(msgDO, root);
        } catch (IllegalAccessException e) {
            WxLog.log("接收微信消息自动注入参数错误");
        } catch (InvocationTargetException e) {
            WxLog.log("接收微信消息自动注入对象错误");
        }

        for (BaseKvPairDO kv : msgDO.getProperties()) {
            parseKv(root, kv);
        }

        StringWriter sw = new StringWriter();
        XMLWriter xmlWriter = new XMLWriter(sw, OutputFormat.createPrettyPrint());
        try {
            xmlWriter.write(doc);
            sw.close();
            xmlWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
        return sw.toString();
    }

    private static void parseKv(Element element, BaseKvPairDO kv) {
        Element child = element.addElement(kv.getKey());
        if (kv.hasSubPairs()) {
            for (BaseKvPairDO childKv : kv.getSubPairs()) {
                parseKv(child, childKv);
            }
        } else {
            child.addCDATA(kv.getValue());
        }
    }

    private static void dealCommonProperties(MsgDO msgDO, Element root) throws InvocationTargetException, IllegalAccessException {
        for (String commonProp : MSG_COMMON_PROPERTIES) {
            for (Method method : msgDO.getClass().getDeclaredMethods()) {
                if (method.getName().equalsIgnoreCase(METHOD_GET_PREFIX + commonProp)) {
                    Object res = null;
                    res = method.invoke(msgDO);
                    Element child = root.addElement(commonProp);
                    child.addCDATA(res.toString());
                }
            }
        }
    }
}
