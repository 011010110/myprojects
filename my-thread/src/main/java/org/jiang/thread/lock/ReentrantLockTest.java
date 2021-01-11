package org.jiang.thread.lock;

import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @Description
 * @Author li.linhua
 * @Date 2020/6/17
 * @Version 1.0
 */
public class ReentrantLockTest {
    ReentrantLock lock = new ReentrantLock();
    Semaphore semaphore = new Semaphore(3);
    volatile int count = 0;

    public void m() {
        lock.lock();
        for (int i = 0; i < 10; i++) {
            System.out.println("" + Thread.currentThread().getName() + ":" + i);

            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        lock.unlock();
    }

    public void m2(int num) {
        try {
            semaphore.acquire();
            count++;
            System.out.println(Thread.currentThread().getName() + ":" + num + "--count:" + count);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            count--;
            semaphore.release();
        }


    }

    public static void main(String[] args) {
        ReentrantLockTest reentrantLockTest = new ReentrantLockTest();
        for (int i = 0; i < 100; i++) {
            final int num = i;
            new Thread(new Runnable() {
                @Override
                public void run() {
                    reentrantLockTest.m2(num);
                }
            }).start();
        }
    }
}
