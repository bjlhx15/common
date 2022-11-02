package com.github.bjlhx15.boot2async;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.conn.ssl.NoopHostnameVerifier;
import org.apache.http.impl.client.DefaultConnectionKeepAliveStrategy;
import org.apache.http.impl.nio.client.CloseableHttpAsyncClient;
import org.apache.http.impl.nio.client.HttpAsyncClientBuilder;
import org.apache.http.impl.nio.client.HttpAsyncClients;
import org.apache.http.util.EntityUtils;

import java.nio.charset.Charset;
import java.util.concurrent.Future;

public class GwHttpClientHelper {
    static CloseableHttpAsyncClient httpClient;
    static {
        HttpAsyncClientBuilder builder = HttpAsyncClients.custom().setMaxConnTotal(2000)
                .setMaxConnPerRoute(2000)
                .setKeepAliveStrategy(new DefaultConnectionKeepAliveStrategy())
                .setSSLHostnameVerifier(NoopHostnameVerifier.INSTANCE);
        httpClient=builder.build();
        httpClient.start();
    }
    public static String handler(HttpUriRequest request){
        Future<HttpResponse> execute = httpClient.execute(request, null);
        try {
            HttpResponse httpResponse = execute.get();
            String s = EntityUtils.toString(httpResponse.getEntity(), Charset.forName("UTF-8"));
            return s;
        } catch (Exception e) {
            return "failed:"+e.getMessage();
        }
    }
}
