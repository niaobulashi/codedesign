package com.hulang.codedesign.concurrency.threadpool;

/**
 * @description: ThreadPoolTest
 * @date: 2024/2/25 21:14
 * @author: HuLang
 * @version: V1.0
 **/
public class ThreadPoolTest {
    public static void main(String[] args) {
        // 该函数使用Java的Runtime类获取当前可用的处理器数量，并将其打印到控制台。
        System.out.println(Runtime.getRuntime().availableProcessors());
    }
}
