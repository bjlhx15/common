package com.github.bjlhx15.core.http;

import org.apache.http.HttpHeaders;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpEntityEnclosingRequestBase;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.entity.StringEntity;
import org.apache.http.message.BasicNameValuePair;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public abstract class BaseHttpExecutor implements HttpExecutor{

    BaseHttpExecutor(){}

    /**
     * 设置值到header 中去
     * @param headers
     * @param httpMethod
     */
    public void setHeaderInfo(Map<String, String> headers, HttpUriRequest httpMethod) {

        if (headers == null) {
            return;
        }
        headers.forEach((key, value) -> {
            // 设置到请求头到HttpRequestBase对象中
            httpMethod.setHeader(key, value);
        });
    }

    /**
     * 封装请求参数
     * @param params
     * @param httpMethod
     * @throws UnsupportedEncodingException
     */
    public void setParametersInfo(Map<String, String> params, HttpEntityEnclosingRequestBase httpMethod) throws UnsupportedEncodingException {

        if(params == null) {
            return;
        }

        List<NameValuePair> nvps = new ArrayList<NameValuePair>();
        params.forEach((key, value) -> {
            nvps.add(new BasicNameValuePair(key, value));
        });
        // 设置到请求的http对象中
        httpMethod.setEntity(new UrlEncodedFormEntity(nvps, Constants.ENCODING_CHAR));
    }

    /**
     * 设置json 发送的格式信息
     * @param JsonStr
     * @param httpPost
     * @throws UnsupportedEncodingException
     */
    public void setJsonEntity(String JsonStr, HttpPost httpPost) throws UnsupportedEncodingException {

        //使用addHeader方法添加请求头部,诸如User-Agent, Accept-Encoding等参数.
        httpPost.setHeader(HttpHeaders.CONTENT_TYPE, Constants.HEADER_CONTENT_TYPE_JSON);
        //组织数据
        StringEntity se = new StringEntity(JsonStr);
        //设置编码格式
        se.setContentEncoding(Constants.ENCODING_CHAR);
        //设置数据类型
        se.setContentType(Constants.BODY_CONTENT_TYPE_JSON);
        //对于POST请求,把请求体填充进HttpPost实体.
        httpPost.setEntity(se);
    }

    public abstract void init();
}
