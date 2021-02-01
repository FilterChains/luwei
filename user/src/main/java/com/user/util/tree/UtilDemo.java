package com.user.util.tree;

public class UtilDemo {
    public static void main(String[] args) {

        for (int i = 0; i < 10; i++) {
            for (int j = 20; j > i+1; j--) {
                System.out.print("-");
            }
            for (int j = 0; j < i+1; j++) {
                System.out.print("[".concat(String.valueOf(i)).concat("]"));
            }
            System.out.println("");
            // for (int j = 10; j < 20; j++) {
            //     System.out.print("-");
            // }

        }
    }
}
