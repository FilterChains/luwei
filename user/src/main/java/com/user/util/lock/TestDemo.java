package com.user.util.lock;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.ReentrantLock;

public class TestDemo {
    private static AtomicInteger atomicInteger = new AtomicInteger();

    public static void main(String[] args) {
        ReentrantLock lock = new ReentrantLock();
        try {
            lock.lock();
        } finally {
            lock.unlock();
        }
        boolean interrupted1 = Thread.currentThread().isInterrupted();
        boolean interrupted = Thread.interrupted();
        System.out.println(atomicInteger.compareAndSet(0, 0));
    }
}
