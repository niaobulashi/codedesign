package com.hulang.codedesign.concurrency.countdown;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/**
 * @description: CountDownLatch是一个同步工具类，
 * 可以用来实现多个线程之间的同步。
 * 它可以让一个线程等待其他线程完成某些工作后再继续执行。
 * @date: 2024/1/2 11:11
 * @author: HuLang
 * @version: 1.0
 */
public class CountDownLatchDemo {

    public static void main(String[] args) throws InterruptedException {
        CountDownLatch countDownLatch = new CountDownLatch(4);
        for (int i = 0; i < 3; i++) {
            new Thread(() -> {
                System.out.println(Thread.currentThread().getName() + " 准备好了");
                countDownLatch.countDown();
            }, "t" + i).start();
        }
        countDownLatch.await(2000, TimeUnit.MILLISECONDS);
        System.out.println("所有线程都准备好了");
    }

}
