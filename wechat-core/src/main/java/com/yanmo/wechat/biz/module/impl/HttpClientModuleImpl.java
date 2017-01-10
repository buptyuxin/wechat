package com.yanmo.wechat.biz.module.impl;

import com.yanmo.wechat.biz.module.HttpClientModule;
import com.yanmo.wechat.log.WxLog;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.charset.StandardCharsets;
import java.util.Map;

/**
 * Created by yanmo.yx on 2015/5/9.
 */
public class HttpClientModuleImpl implements HttpClientModule {

    private CloseableHttpClient httpClient;

    public void init() {
        PoolingHttpClientConnectionManager cm = new PoolingHttpClientConnectionManager();
        cm.setMaxTotal(50);
        cm.setDefaultMaxPerRoute(10);
        this.httpClient = HttpClients.custom().setConnectionManager(cm).build();
    }

    @Override
    public String doGet(String url) {
        CloseableHttpResponse res = null;
        try {
            HttpGet httpGet = new HttpGet(url);
            RequestConfig requestConfig = RequestConfig.custom().setSocketTimeout(2000).setConnectTimeout(2000).build();//设置请求和传输超时时间
            httpGet.setConfig(requestConfig);
            res = httpClient.execute(httpGet);
            if (res != null && res.getStatusLine().getStatusCode() == 200) {
                return EntityUtils.toString(res.getEntity(), StandardCharsets.UTF_8);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            return null;
        } finally {
            try {
                res.close();
            } catch (IOException e) {
                WxLog.log(e.toString());
            }
        }
        return null;
    }

    @Override
    public String doPost(String url, Map<String, String> parameters, String data) {
        URIBuilder uriBuilder = new URIBuilder();
        uriBuilder.setHost(url);
        if (parameters != null && !parameters.isEmpty()) {
            for (String key : parameters.keySet()) {
                uriBuilder.setParameter(key, parameters.get(key));
            }
        }
        URI uri = null;
        try {
            uri = uriBuilder.build();
        } catch (URISyntaxException e) {
            return null;
        }
        HttpPost httpPost = new HttpPost(uri);
        try {
            StringEntity entity = new StringEntity(data, StandardCharsets.UTF_8);
            entity.setContentType("text/xml");
            entity.setContentEncoding(StandardCharsets.UTF_8.toString());
            httpPost.setEntity(entity);
            CloseableHttpResponse res = httpClient.execute(httpPost);
            if (res != null && res.getStatusLine().getStatusCode() == 200) {
                return EntityUtils.toString(res.getEntity(), StandardCharsets.UTF_8);
            }
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
