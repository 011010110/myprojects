package org.jiang.thread;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @Description
 * @Author li.linhua
 * @Date 2018/6/12
 * @Version 1.0
 */
public class HelloThread {

    private AtomicInteger count = new AtomicInteger(0);

    public static void main(String[] args) {

        Thread thread = new Thread() {
            @Override
            public void run() {
                System.out.println(Thread.currentThread().getName() + "正则运行！");
            }
        };
        thread.start();
    }
}
