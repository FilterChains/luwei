package com.user.util;

import java.util.concurrent.CountDownLatch;

public class FooBar {
    private int n;

    public FooBar(int n) {
        this.n = n;
    }

    private CountDownLatch countFoo = new CountDownLatch(1);
    private CountDownLatch countBar = new CountDownLatch(1);

    public void foo(Runnable printFoo) throws InterruptedException {
        for (int i = 0; i < n; i++) {

            // printFoo.run() outputs "foo". Do not change or remove this line.

            System.out.print("foo");
            printFoo.run();
            countFoo.countDown();
        }
    }

    public void bar(Runnable printBar) throws InterruptedException {
        for (int i = 0; i < n; i++) {

            // printBar.run() outputs "bar". Do not change or remove this line.
            System.out.print("bar");
            countFoo.await();
            printBar.run();
        }
    }
}
