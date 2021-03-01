package com.user.util.lock;

import javax.swing.tree.TreeNode;
import java.util.Enumeration;

public class Interrupet {

    public static void main(String[] args) throws InterruptedException {

//        Thread thread = new Thread(() -> {
//            for (int i = 0; i < 10000; i++) {
//                if (i > 2000 && Thread.currentThread().isInterrupted()) {
//                    break;
//                }
//                System.out.println(i);
//            }
//            System.out.println("执行到了最后面了");
//        }, "线程1======");
//
//        thread.start();
//        thread.join();
//        Thread.yield();
//        Thread.sleep(10);
//        // thread.stop();
//        thread.interrupt();
//
//        ThreadPoolExecutor executor = new ThreadPoolExecutor(1, 1, 1, TimeUnit.MINUTES,
//                new LinkedBlockingQueue<>(1), Executors.defaultThreadFactory(), new ThreadPoolExecutor.CallerRunsPolicy());
//        try {
//            executor.execute(() -> {
//                try {
//                    Thread.sleep(3000);
//                    System.out.println("你好");
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//            });
//            System.out.println(executor.isTerminated());
//            executor.shutdown();
//        } catch (Exception w) {
//            executor.shutdownNow();
//            Integer.valueOf(1).equals(1);
//        }
//        System.out.println("isTerminated:=====> " + executor.isTerminated());
//        System.out.println("isShutdown:===== >" + executor.isShutdown());
//        System.out.println("isTerminating:=====> " + executor.isTerminating());


        String num1 = "1";
        String num2 = "1";
        String integerNum1 = new String(num1);
        String integerNum2 = new String(num2);
        System.out.println(num1 == num2);
        System.out.println(integerNum1 == integerNum2);
        System.out.println(integerNum1.equals(integerNum2));
        new TreeNode() {
            @Override
            public TreeNode getChildAt(int childIndex) {
                return null;
            }

            @Override
            public int getChildCount() {
                return 0;
            }

            @Override
            public TreeNode getParent() {
                return null;
            }

            @Override
            public int getIndex(TreeNode node) {
                return 0;
            }

            @Override
            public boolean getAllowsChildren() {
                return false;
            }

            @Override
            public boolean isLeaf() {
                return false;
            }

            @Override
            public Enumeration children() {
                return null;
            }
        };
    }
}
