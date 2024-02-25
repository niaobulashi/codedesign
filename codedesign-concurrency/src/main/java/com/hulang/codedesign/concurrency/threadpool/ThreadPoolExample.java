package com.hulang.codedesign.concurrency.threadpool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @description: ThreadPoolExample
 * @date: 2024/2/18 9:59
 * @author: HuLang
 * @version: 1.0
 */
public class ThreadPoolExample {

    public static void main(String[] args) {
        // 创建一个固定大小的线程池
        ExecutorService executor = Executors.newFixedThreadPool(5);

        // 循环创建10个任务并提交给线程池执行
        for (int i = 0; i < 10; i++) {
            Runnable worker = new WorkerThread("worker" + i);
            executor.execute(worker);
        }
        // 关闭线程池
        executor.shutdown();
        executor.shutdownNow();
        // 等待所有任务完成
        while (!executor.isTerminated()) {
            // 空循环，等待所有任务完成

        }
        // 所有任务已完成
        System.out.println("所有任务已完成");
    }

}

class WorkerThread implements Runnable {
    private String name;

    /**
     * 构造方法
     * @param name 工作线程名称
     */
    public WorkerThread(String name) {
        this.name = name;
    }

    /**
     * 运行方法
     */
    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName() + " 开始. 命令 = " + name);
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.println(Thread.currentThread().getName() + " 结束.");
    }

    /**
     * 转换为字符串
     * @return 工作线程名称
     */
    @Override
    public String toString() {
        return this.name;
    }
}
