package com.github.bjlhx15.core.http;

import lombok.extern.log4j.Log4j2;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;

import java.io.IOException;
import java.util.Map;

@Log4j2
public class SyncHttpExecutor extends BaseHttpExecutor {

    private CloseableHttpClient httpClient;
    private volatile boolean inited = false;
    private volatile boolean started = false;
    private SyncHttpExecutor(){}

    public static SyncHttpExecutor build(){
        return Instance.instance;
    }

    @Override
    public void init() {

        if(!inited){
            RequestConfig requestConfig = RequestConfig.custom()
                    .setConnectTimeout(Constants.HTTPCLIENT_CONNECTION_TIMEOUT)
                    .setSocketTimeout(Constants.HTTPCLIENT_SOCKET_TIMEOUT)
                    .setConnectionRequestTimeout(Constants.HTTPCLIENT_REQUEST_TIMEOUT)
                    .build();


            PoolingHttpClientConnectionManager connManager = new PoolingHttpClientConnectionManager();
            connManager.setMaxTotal(Constants.HTTPCLIENT_MAX_TOTAL);
            connManager.setDefaultMaxPerRoute(Constants.HTTPCLIENT_DEFAULT_MAX_PER_ROUTE);

            httpClient = HttpClients.custom().setConnectionManager(connManager).setDefaultRequestConfig(requestConfig).build();

            inited = true;
            log.info("syncHttpExecutor 初始化完成");
        }
    }

    @Override
    public void start() {
        started = true;
        init();
    }

    @Override
    public void close() {

        try {
            if(httpClient != null){
                httpClient.close();
            }
            inited = false;
        } catch (IOException e) {
            log.error(e.getMessage(), e);
        }

        log.info("syncHttpExecutor 关闭资源");
    }

    /**
     * 同步get请求
     * @param url
     * @param headers
     * @param parameters
     * @return CloseableHttpResponse
     * @throws Exception
     */
    public CloseableHttpResponse syncGet(String url, Map<String, String> headers, Map<String, String> parameters) throws Exception{

        URIBuilder uriBuilder = new URIBuilder(url);
        if(parameters != null){
            parameters.forEach((key, value) -> {
                uriBuilder.setParameter(key, value);
            });
        }

        HttpGet httpGet = new HttpGet(uriBuilder.build());
        setHeaderInfo(headers, httpGet);
        return doExecute(httpGet);
    }

    /**
     * 同步post 请求
     * @param url
     * @param headers
     * @param params
     * @return CloseableHttpResponse
     * @throws Exception
     */
    public CloseableHttpResponse syncPost(String url, Map<String, String> headers, Map<String, String> params) throws Exception{

        HttpPost httpPost = new HttpPost(url);
        setHeaderInfo(headers, httpPost);
        setParametersInfo(params, httpPost);
        return doExecute(httpPost);
    }

    /**
     * 同步post请求，参数是json格式
     * @param url
     * @param headers
     * @param JsonStr
     * @return
     */
    public CloseableHttpResponse syncJsonPost(String url, Map<String, String> headers, String JsonStr) throws Exception {

        HttpPost httpPost = new HttpPost(url);
        setHeaderInfo(headers, httpPost);
        // 设置json参数
        setJsonEntity(JsonStr, httpPost);
        return doExecute(httpPost);
    }

    /**
     * 执行网络请求
     * @param httpMethod
     * @return CloseableHttpResponse
     * @throws Exception
     */
    public CloseableHttpResponse doExecute(HttpRequestBase httpMethod) throws Exception{

        if(!started){
            start();
        }
        return httpClient.execute(httpMethod);
    }

    /**
     * 静态内部类，用于做单例的构建
     */
    private static class Instance {
        private static SyncHttpExecutor instance = new SyncHttpExecutor();
    }
}
