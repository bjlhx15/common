package com.github.bjlhx15.boot2async;

import lombok.extern.slf4j.Slf4j;
import org.apache.http.NameValuePair;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpUriRequest;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.async.DeferredResult;

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.util.ArrayList;
import java.util.Date;
import java.util.concurrent.Callable;
import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping("/gw")
@Slf4j
public class GwController {
    private static String doExec(HttpServletRequest request) throws Exception {
        String httptimeoutStr = request.getParameter("httptimeout");
        String timeoutStr = request.getParameter("timeout");
        System.out.println("request start:" + new Date());
        int httptimeout = StringUtils.isEmpty(timeoutStr) ? 1000 : Integer.parseInt(httptimeoutStr);
        int timeout = StringUtils.isEmpty(timeoutStr) ? 1000 : Integer.parseInt(timeoutStr);
        URI uri = URI.create("http://localhost:8090/sync/timeout?timeout=" + timeout);
        ArrayList<NameValuePair> nameValuePairs = new ArrayList<>();
        RequestConfig requestConfig = RequestConfig.custom().setConnectTimeout(httptimeout).setConnectionRequestTimeout(httptimeout)
                .setSocketTimeout(httptimeout).build();
        UrlEncodedFormEntity entity = new UrlEncodedFormEntity(nameValuePairs, "UTF-8");

        try {
            HttpPost post = new HttpPost(uri);
            post.setEntity(entity);
            post.setConfig(requestConfig);
            String handler = GwHttpClientHelper.handler(post);
            return "gw ok:"+handler;
        } catch (Exception e) {
            return "gw exception:" + e.getMessage();
        } finally {
            System.out.println("request start:" + new Date());
        }
    }

    @RequestMapping("sync")
    @ResponseBody
    public String sync(HttpServletRequest request) throws Exception {
        return this.doExec(request);
    }





}
