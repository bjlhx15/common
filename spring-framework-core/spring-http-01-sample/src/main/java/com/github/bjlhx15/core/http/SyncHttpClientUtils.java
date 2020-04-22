package com.github.bjlhx15.core.http;

import org.apache.http.Header;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;


public class SyncHttpClientUtils {

    /**
     * 同步get请求
     * @param url
     * @param headers
     * @param parameters
     * @return HttpResult
     * @throws Exception
     */
    public static HttpResult syncGet(String url, Map<String, String> headers, Map<String, String> parameters) throws Exception{

        CloseableHttpResponse response = SyncHttpExecutor.build().syncGet(url, headers, parameters);
        HttpResult httpResult = getHttpResult(response);
        return httpResult;
    }

    /**
     * 同步post 请求
     * @param url
     * @param headers
     * @param params
     * @return CloseableHttpResponse
     * @throws Exception
     */
    public static HttpResult syncPost(String url, Map<String, String> headers, Map<String, String> params) throws Exception{

        CloseableHttpResponse response = SyncHttpExecutor.build().syncPost(url, headers, params);
        return getHttpResult(response);
    }

    /**
     * 同步post请求，参数是json格式
     * @param url
     * @param headers
     * @param JsonStr
     * @return
     */
    public static HttpResult syncJsonPost(String url, Map<String, String> headers, String JsonStr) throws Exception {

        CloseableHttpResponse response = SyncHttpExecutor.build().syncJsonPost(url, headers, JsonStr);
        return getHttpResult(response);
    }

    /**
     * 返回结果信息
     * @param response
     * @return HttpResult
     * @throws IOException
     */
    private static HttpResult getHttpResult(CloseableHttpResponse response) throws IOException {

        if(response != null){
            HttpResult httpResult = new HttpResult();
            if(response != null && response.getStatusLine() != null && response.getStatusLine().getStatusCode() == 200){
                setHeaders(response, httpResult);
                httpResult.setCode(response.getStatusLine().getStatusCode());
                if (response.getEntity() != null) {
                    httpResult.setContent(EntityUtils.toString(response.getEntity(), Constants.ENCODING_CHAR));
                }
            }else{
                httpResult.setCode(response.getStatusLine().getStatusCode());
            }
            return httpResult;
        }
        return null;
    }

    /**
     * 将Http client 返回的response中的header取出放到HttpResult对象中去
     * @param response
     * @param httpResult
     */
    private static void setHeaders(CloseableHttpResponse response, HttpResult httpResult) {
        Header[] allHeaders = response.getAllHeaders();
        if(allHeaders != null){
            Map<String, String> headers = new HashMap<>();
            for (Header header : allHeaders) {
                headers.put(header.getName(), header.getValue());
            }
            httpResult.setHeaders(headers);
        }
    }
}
