package com.github.bjlhx15.core.http;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.http.client.OkHttp3ClientHttpRequestFactory;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = ApplicationMain.class)
@Slf4j
public class HttpOrHttpsTest {

        @Autowired
    private RestTemplate restTemplate;

//    @Before
//    public void aa() {
//        restTemplate =null;// new RestTemplate(new HttpsSimpleClientHttpRequestFactory());
//    }

    @Test
    public void testClientHttpRequestFactory() {
        ClientHttpRequestFactory requestFactory = restTemplate.getRequestFactory();
        if(requestFactory instanceof SimpleClientHttpRequestFactory){
            log.info("SimpleClientHttpRequestFactory");
        }
        if(requestFactory instanceof HttpComponentsClientHttpRequestFactory){
            log.info("HttpComponentsClientHttpRequestFactory");
        }
        if(requestFactory instanceof OkHttp3ClientHttpRequestFactory){
            log.info("OkHttp3ClientHttpRequestFactory");
        }
    }

    @Test
    public void testHttp() {
        JSONObject forObject = restTemplate.getForObject("http://yuncha.jdcloud.com/auth/user/header", JSONObject.class);
        log.info(forObject.toJSONString());
    }

    @Test
    public void testHttps() {
        JSONObject forObject = restTemplate.getForObject("https://constid.dingxiang-inc.com/udid/c1?", JSONObject.class);
        log.info(forObject.toJSONString());
    }

    @Test
    public void testHttp2s() {
        Map<String,Object> req=new HashMap<String,Object>();
        req.put("itemId",12098838);
        req.put("itemTitle","010-Linux 磁盘信息查看");
//        String json = JSON.toJSONString(params);
        HttpEntity<String> httpEntity = new HttpEntity(req, null);
        ResponseEntity<JSONArray> postForEntity = restTemplate.postForEntity("https://recomm.cnblogs.com/api/v2/recomm/blogpost/reco", httpEntity, JSONArray.class);
        if(postForEntity.getStatusCode()== HttpStatus.OK) {
            log.info(postForEntity.getBody().toJSONString());
        }else{
            log.error("----error----",postForEntity);
        }
    }
}