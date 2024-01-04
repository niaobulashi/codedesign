package com.hulang.codedesign.concurrency.countdown;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @description: CompletableFuture是一个异步编程的工具类，
 * 可以用来执行异步任务并获取任务执行结果
 * @date: 2024/1/2 17:36
 * @author: HuLang
 * @version: 1.0
 */
public class CompletableFutureDemo {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        completableFutureTest();
        //如果任务比较多的情况下就可以使用数组列表来存储创建的任务

    }

    private static void completableFutureTest() throws InterruptedException, ExecutionException {
        // 手动创建线程池，效果会更好哦。
        ExecutorService executorService = Executors.newFixedThreadPool(3);
        // 启动三个异步任务
        CompletableFuture<Void> future1 = CompletableFuture.runAsync(() -> {
            // 执行任务1
            System.out.println("任务1 执行");
        }, executorService);
        CompletableFuture<Void> future2 = CompletableFuture.runAsync(() -> {
            // 执行任务2
            System.out.println("任务2 执行");
        }, executorService);
        CompletableFuture<Void> future3 = CompletableFuture.runAsync(() -> {
            // 执行任务3
            System.out.println("任务3 执行");
        }, executorService);

        CompletableFuture.allOf(future1, future2, future3).get();
        System.out.println("所有任务执行完成");
    }


}
