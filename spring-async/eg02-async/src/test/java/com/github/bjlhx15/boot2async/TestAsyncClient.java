package com.github.bjlhx15.boot2async;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.concurrent.FutureCallback;
import org.apache.http.impl.nio.client.CloseableHttpAsyncClient;
import org.apache.http.impl.nio.client.HttpAsyncClients;
import org.junit.Test;

import java.util.concurrent.CountDownLatch;

public class TestAsyncClient {
    @Test
    public void test() throws Exception {
        CloseableHttpAsyncClient httpAsyncClient = HttpAsyncClients.createDefault();
        final HttpGet request = new HttpGet("http://www.baidu.com");
        httpAsyncClient.start();
        CountDownLatch latch = new CountDownLatch(1);
        httpAsyncClient.execute(request, new FutureCallback<HttpResponse>() {
            @Override
            public void completed(final HttpResponse response2) {
                latch.countDown();
                System.out.println("收到回复" + response2.getStatusLine());
            }

            @Override
            public void failed(final Exception ex) {
                latch.countDown();
                System.out.println("发生异常" + ":" + ex.getMessage());
            }

            @Override
            public void cancelled() {
                latch.countDown();
                System.out.println("cancelled");
            }
        });
        // 因为httpAsyncClient不阻塞，所以需要在这里等待执行完再调用httpAsyncClient.close()
        // 不然还没收到回复就关了http连接
        // 实际使用中当然不需要这样每调用一次关闭一次，而是工程一次运行的整个生命周期都用同一个，停止运行时关闭一次
        latch.await();
        httpAsyncClient.close();
    }
}
