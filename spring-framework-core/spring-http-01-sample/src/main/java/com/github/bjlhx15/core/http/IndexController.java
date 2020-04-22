package com.github.bjlhx15.core.http;

import com.alibaba.fastjson.JSONObject;
import com.jd.jr.epp.openapi_security.al.AsyCipherAlgorithm;
import com.jd.jr.epp.openapi_security.al.DigestAlgorithm;
import com.jd.jr.epp.openapi_security.util.SecurityUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.http.HttpEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.Map;

@RestController
@Slf4j
public class IndexController {
    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private ApplicationContext applicationContext;

    @RequestMapping("test")
    public void test() {

        JSONObject forObject = restTemplate.getForObject("https://jcauth.jd.com/auth/user/header", JSONObject.class);
        log.info(forObject.toJSONString());


        Map<String, String> req = new HashMap<String, String>();
        req.put("clientId", "c28eb3c6353aa4a8c15c71d55e48fc3f");
        req.put("clientSecret", "JM4Er0seu$");
//        String json = JSON.toJSONString(params);
        HttpEntity<String> httpEntity = new HttpEntity(req, null);
        JSONObject forObject2 = restTemplate.postForObject("https://yuxi-blockchain-gw.jdcloud.com/account/getToken", httpEntity, JSONObject.class);
        log.info(forObject2.toJSONString());
    }

    @RequestMapping("testimg")
    public Object testimg() throws Exception {

        String a = aa();
        Map<String, Object> re = new HashMap<String, Object>();

        JSONObject jsonObject = JSONObject.parseObject(a, JSONObject.class);
        String image = jsonObject.getString("image");
        byte[] bytes = Base64.decodeBase64(image);
        re.put("img", bytes);

        return re;
    }

    @RequestMapping("testimg2")
    public Object testimg3() throws Exception {

        //将applicationContext转换为ConfigurableApplicationContext
//        ConfigurableApplicationContext configurableApplicationContext = (ConfigurableApplicationContext) applicationContext;
//
//        // 获取bean工厂并转换为DefaultListableBeanFactory
//        DefaultListableBeanFactory defaultListableBeanFactory = (DefaultListableBeanFactory) configurableApplicationContext.getBeanFactory();
//
//        // 通过BeanDefinitionBuilder创建bean定义
//        BeanDefinitionBuilder beanDefinitionBuilder = BeanDefinitionBuilder.genericBeanDefinition(TestController.class);
//
//        // 注册bean
//        defaultListableBeanFactory.registerBeanDefinition("testController", beanDefinitionBuilder.getRawBeanDefinition());



        return "";
    }

    class DownLoadFileRequest {
        private String fid;
        public String getFid(){
            return fid;
        }

        public void setFid (String fid){
            this.fid = fid;
        }
    }
    class InputEntity {
        private DownLoadFileRequest request;

        public DownLoadFileRequest getRequest() {
            return request;
        }

        public void setRequest(DownLoadFileRequest request) {
            this.request = request;
        }
    }
    private String aa() throws Exception {
        InputEntity inputEntity = new InputEntity();
        DownLoadFileRequest downLoadFileRequest = new DownLoadFileRequest();
//        downLoadFileRequest.setFid("A1_1812_3_242_XE736D6_RAS@6cef484289e1ca38f1");
//        downLoadFileRequest.setFid("A2_1911_107_350_2302E13_RAS@d63d881c4bc59216ab");
        downLoadFileRequest.setFid("A1_1912_25_4_4E736D6_RAS@e1af237fc9c51c65bf");
        downLoadFileRequest.setFid("A2_1912_172_461_6302E13_RAS@06a4c0e2aad72ded0f");


        //downLoadFileRequest.setSystemInfo(systemInfo);


//        AdaptEcsQueryRequest request = new AdaptEcsQueryRequest();
//        request.setOutsideId("C1000137596");
//        request.setOutsideUserId("zhzhd1117");
//        request.setBussCode("REAL_NAME");

//        inputEntity.setAdaptEcsQueryRequest(request);
        inputEntity.setRequest(downLoadFileRequest);

        String encrypt = JacksonUtils.toJson(inputEntity);

        //        String uri = "/mapi/v1/templateFacade/queryContract";
        String uri = "/smapi/v1/open-api/ecsfileservice/download-img";

        String requestTime = "20191009185448710";

        String url = "http://10.222.10.84:8112" + uri;
        //String url = "http://10.222.9.87:8112" + uri;
        //String url = "http://127.0.0.1:8112" + uri;

        AppInfo appInfo = new AppInfo();
        appInfo.setAppId(Constants.APP_ID);
        appInfo.setOpenPlatPublicKey(Constants.OPEN_KEYS_PLAT_PUBLIC);
        appInfo.setAppPrivateKey(Constants.OPEN_KEYS_APP_PRIVATE);
        appInfo.setMd5Salt(Constants.MD5_SALT);

        return doHttpRequest(url, encrypt, requestTime, appInfo);

    }

    private String doHttpRequest(String url, String input, String requestTime, AppInfo appInfo) throws Exception {

        // step1:组织请求报文.
        String encrypt = SecurityUtils.subEncryptByPublicKey(input.getBytes(Constants.ENCODING_CHAR),
                Base64.decodeBase64(appInfo.getOpenPlatPublicKey()), AsyCipherAlgorithm.RSA);
        String original4Sign = join4SignString(input, appInfo.getAppId(), requestTime, appInfo.getAppIdType());
        // 先md5做摘要, 再对此摘要用自己应用私钥做签名.
        String sign = SecurityUtils.sign(original4Sign.getBytes(Constants.ENCODING_CHAR), Base64.decodeBase64(appInfo.getAppPrivateKey()),
                DigestAlgorithm.MD5, appInfo.getMd5Salt().getBytes(Constants.ENCODING_CHAR));

        Map<String, String> headers = new HashMap<>();
        headers.put(Constants.JRGW_REQUEST_TIME, requestTime);
        headers.put(Constants.JRGW_ENTERPRISE_USER_ID, appInfo.getAppId());
        headers.put(Constants.JRGW_USER_ID_TYPE, appInfo.getAppIdType());
        headers.put(Constants.GW_ENCRYPT_TYPE, appInfo.getEncryptType());
        headers.put(Constants.GW_SIGN_TYPE, appInfo.getSignType());
        headers.put(Constants.GW_SIGN, sign);

        Map<String, String> parameters = new HashMap<>();
        parameters.put(Constants.ENCRYPT, encrypt);

        // step2: 调用http请求.
        HttpResult result = SyncHttpClientUtils.syncPost(url, headers, parameters);

        // step3: 解析响应报文.
        Map<String, String> resultMap = JacksonUtils.readValue(result.getContent().toString(), Map.class);

        String decrypt = SecurityUtils.subDecryptByPrivateKey(Base64.decodeBase64(resultMap.get(Constants.ENCRYPT)),
                Base64.decodeBase64(appInfo.getAppPrivateKey()), AsyCipherAlgorithm.RSA);
        System.err.println("解密后的 encrypt: " + decrypt);

        String data = join4SignString4Response(decrypt, result.getHeaders());

//        JSONObject jsonObject = JSONObject.parseObject(decrypt);
//        String image = jsonObject.getString("image");
//        base64StringToImage2(image);

        // 响应加签
        String gwSign = result.getHeaders().get(Constants.GW_SIGN);
        boolean flag = SecurityUtils.verify(data.getBytes(Constants.ENCODING_CHAR), Base64.decodeBase64(gwSign),
                Base64.decodeBase64(appInfo.getOpenPlatPublicKey()), DigestAlgorithm.MD5, appInfo.getMd5Salt().getBytes(Constants.ENCODING_CHAR));

        if (!flag) {
            // 验证不通过.
            throw new Exception();
        }

        System.err.println("验签通过: " + flag);
        return decrypt;
    }

    private static String join4SignString(String encrypt, String appId, String requestTime, String appIdType) {

        StringBuilder sb = new StringBuilder();
        sb.append(Constants.BIZ_CONTENT).append(Constants.EQUAL_FLAG).append(encrypt)
                .append(Constants.JOIN_FLAG).append(Constants.JRGW_ENTERPRISE_USER_ID).append(Constants.EQUAL_FLAG).append(appId)
                .append(Constants.JOIN_FLAG).append(Constants.JRGW_REQUEST_TIME).append(Constants.EQUAL_FLAG).append(requestTime)
                .append(Constants.JOIN_FLAG).append(Constants.JRGW_USER_ID_TYPE).append(Constants.EQUAL_FLAG).append(appIdType);

        return sb.toString();
    }

    private static String join4SignString4Response(String encrypt, Map<String, String> headers) throws UnsupportedEncodingException {

        StringBuilder sb = new StringBuilder();
        if (!StringUtils.isEmpty(encrypt)) {
            sb.append(Constants.BIZ_CONTENT).append(Constants.EQUAL_FLAG).append(encrypt);
        }
        sb.append(Constants.JOIN_FLAG).append(Constants.JRGW_RESP_CODE).append(Constants.EQUAL_FLAG).append(headers.get(Constants.JRGW_RESP_CODE))
                .append(Constants.JOIN_FLAG).append(Constants.JRGW_RESP_MSG).append(Constants.EQUAL_FLAG).append(URLDecoder.decode(headers.get(Constants.JRGW_RESP_MSG), Constants.ENCODING_CHAR))
                .append(Constants.JOIN_FLAG).append(Constants.JRGW_RESPOND_TIME).append(Constants.EQUAL_FLAG).append(headers.get(Constants.JRGW_RESPOND_TIME));
        return sb.toString();
    }



}

