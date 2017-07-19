package com.high.utils;

import net.sf.json.JSONObject;
import org.apache.commons.httpclient.DefaultHttpMethodRetryHandler;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.methods.StringRequestEntity;
import org.apache.commons.httpclient.params.HttpMethodParams;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

/**
 * Created by llw on 2017/7/5.
 */
public class HttpUtils {
    public static String doGet(String url) throws IOException {
        HttpClient client = new HttpClient();
        GetMethod method = new GetMethod(url);
        //StringRequestEntity entity = new StringRequestEntity(data, "application/json", "UTF-8");
        int code = client.executeMethod(method);
        if(code == HttpStatus.SC_OK){
            byte[] responseBody = method.getResponseBodyAsString().getBytes(method.getResponseCharSet());
            // 在返回响应消息使用编码(utf-8或gb2312)
            String response = new String(responseBody, "utf-8");
            // 释放连接
            method.releaseConnection();
            return  response;
        }
        return null;
    }
    public static String doPost(String url, JSONObject data) throws IOException {
        HttpClient client = new HttpClient();
        PostMethod method = new PostMethod(url);
        StringRequestEntity requestEntity = new StringRequestEntity(data.toString(), "application/json", "UTF-8");
        method.setRequestEntity(requestEntity);
        //使用系统提供的默认的恢复策略
        method.getParams().setParameter(HttpMethodParams.RETRY_HANDLER, new DefaultHttpMethodRetryHandler());
        int code = client.executeMethod(method);
        System.out.println(code);
        byte[] responseBody1 = method.getResponseBodyAsString().getBytes(method.getResponseCharSet());
        // 在返回响应消息使用编码(utf-8或gb2312)
        String response1 = new String(responseBody1, "utf-8");
        System.out.println(response1);
        if(code == HttpStatus.SC_OK){
            byte[] responseBody = method.getResponseBodyAsString().getBytes(method.getResponseCharSet());
            // 在返回响应消息使用编码(utf-8或gb2312)
            String response = new String(responseBody, "utf-8");
            // 释放连接
            method.releaseConnection();
            return  response;
        }
        return null;
    }
}
