package com.github.bjlhx15.boot2async;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.async.DeferredResult;
import org.springframework.web.context.request.async.WebAsyncTask;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.Queue;
import java.util.concurrent.*;

@RestController
@RequestMapping("/async")
@Slf4j
public class AsyncController {
    @RequestMapping("servlet")
    @ResponseBody
    public String login(HttpServletRequest request) {
        return "ok";
    }


    @GetMapping("/callable")
    public Callable<String> testCallable() throws Exception {
        log.info("主线程开始！");

        Callable<String> result = new Callable<String>() {
            @Override
            public String call() throws Exception {
                log.info("副线程开始1！");
                Thread.sleep(3000);
                log.info("副线程结束1！");
                return "SUCCESS1" + new Date();
            }
        };
        log.info("主线程结束！");
        return result;
    }

    @RequestMapping(value = "/longtimetask", method = RequestMethod.GET)
    public WebAsyncTask longTimeTask() {
        System.out.println("/longtimetask被调用 thread id is : " + Thread.currentThread().getId());
        Callable<String> callable = new Callable<String>() {
            public String call() throws Exception {
                Thread.sleep(3000); //假设是一些长时间任务
                System.out.println("执行成功 thread id is : " + Thread.currentThread().getId());
                return "ok";
            }
        };
        return new WebAsyncTask(callable);
    }

    @RequestMapping(value = "/longtimetaskTimeout", method = RequestMethod.GET)
    public WebAsyncTask longtimetaskTimeout(HttpServletRequest request) {
        System.out.println("/longtimetask被调用 thread id is : " + Thread.currentThread().getId());
        String tr = request.getParameter("tr");
        int trs=Integer.parseInt(tr);
        Callable<String> callable = new Callable<String>() {
            public String call() throws Exception {
                if(1000==trs){
                    throw new Exception("错误的时间");
                }
                Thread.sleep(trs); //假设是一些长时间任务
                System.out.println("执行成功 thread id is : " + Thread.currentThread().getId());
                return "ok";
            }
        };

        // 采用WebAsyncTask 返回 这样可以处理超时和错误 同时也可以指定使用的Excutor名称
        WebAsyncTask<String> webAsyncTask = new WebAsyncTask<>(3000, callable);

        // 注意：onCompletion表示完成，不管你是否超时、是否抛出异常，这个函数都会执行的
        webAsyncTask.onCompletion(() -> System.out.println("程序[正常执行]完成的回调"));

        // 这两个返回的内容，最终都会放进response里面去===========
        webAsyncTask.onTimeout(() -> "程序[超时]的回调");

        // 备注：这个是Spring5新增的
        webAsyncTask.onError(() -> "程序[出现异常]的回调");

        return webAsyncTask;
    }


    //接收队列
    private BlockingQueue<DeferredResult<String>> blockingQueue = new ArrayBlockingQueue(1024);
    //接收队列 或者ConcurrentLinkedQueue
//    private static Queue<DeferredResult<String>> queue = new ConcurrentLinkedQueue<DeferredResult<String>>();

    /**
     * 返回值是DeferredResult类型，如果没有结果请求阻塞
     *
     * @return
     */
    @GetMapping("/quotes")
    public DeferredResult<String> quotes() {
        //指定超时时间，及出错时返回的值
        DeferredResult<String> result = new DeferredResult(3000L, "error");
        //先存储起来，等待触发
        blockingQueue.add(result);
//        queue.add(result);
        return result;
    }

    /**
     * 另外一个请求(新的线程)设置值
     *
     * @throws InterruptedException
     */

    @GetMapping("take")
    public void take() throws InterruptedException {
        DeferredResult<String> result = blockingQueue.take();
        result.setResult("route");
//        DeferredResult<String> poll = queue.poll();
//        poll.setResult("OK");
    }

    /**
     * 一个独立的示例
     * @return
     */
    @RequestMapping(value = "/deferred", method = RequestMethod.GET)
    public DeferredResult<String> executeSlowTask() {
        log.info("Request received");
        DeferredResult<String> deferredResult = new DeferredResult<>();
        CompletableFuture.supplyAsync(()->{
            try {
                Thread.sleep(5000);
                log.info("Slow task executed");
                return "Task finished";
            } catch (InterruptedException e) {
                e.printStackTrace();
                return "Task exception";
            }
        }).whenCompleteAsync((result, throwable) -> deferredResult.setResult(result));
        log.info("Servlet thread released");

        return deferredResult;
    }
}

