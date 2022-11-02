package com.github.bjlhx15.chainmaker.demo.controller;

import com.github.bjlhx15.chainmaker.demo.bean.HelloWorld;
import com.github.bjlhx15.chainmaker.demo.common.response.Result;
import com.google.common.collect.Maps;
import lombok.extern.slf4j.Slf4j;
import org.chainmaker.pb.common.ResultOuterClass;
import org.chainmaker.sdk.ChainClient;
import org.chainmaker.sdk.SdkException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

/**
 * HelloWorld合约(wasm版)控制器
 */
@Slf4j
@RestController
@RequestMapping("/helloworld")
public class HelloWorldController {

    private static long rpcCallTimeout = 10000;
    private static long syncResultTimeout = 10000;

    private static final String CONTRACT_NAME = "helloworld";

    @Autowired
    private ChainClient chainClient;

    @PostMapping(value = "/set")
    public Result<?> set(@RequestBody HelloWorld hello) {

        Map<String, byte[]> params = new HashMap<>();
        params.put("n",  hello.getN().getBytes());
        String method = "set";

        ResultOuterClass.TxResponse responseInfo = null;
        try {
            responseInfo = chainClient.invokeContract(CONTRACT_NAME, method, null, params, rpcCallTimeout, syncResultTimeout);
        } catch (SdkException e) {
            e.printStackTrace();
        }
        HashMap<Object, Object> map = Maps.newHashMap();
        map.put("code", responseInfo.getCode());
        map.put("txId", responseInfo.getTxId());
        return Result.data(map);
    }

    @GetMapping(value = "/get")
    public Result<?> get(@RequestParam("txId") String txId) {

        String method = "get";
        ResultOuterClass.TxResponse responseInfo = null;
        try {
            responseInfo = chainClient.queryContract(CONTRACT_NAME, method, txId, null, rpcCallTimeout);
        } catch (SdkException e) {
            e.printStackTrace();
        }
        return Result.data(responseInfo.getContractResult().getResult().toString());
    }

}
