package com.user.util.practice;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Solution {
    public static void main(String[] args) {
        int[] num = {2, 7, 4, 1, 0, 1};
        int i = lastStoneWeight(num);
        System.out.println("结果:" + i);

    }

    public static int lastStoneWeight(int[] stones) {
        if (null == stones) {
            return 0;
        }
        int length = stones.length - 1;
        if (1 == length) {
            return stones[0];
        }
        List<Integer> list = new ArrayList<>(length);
        for (int i = 0; i < length; i++) {
            list.add(stones[i]);
        }
        do {
            list.sort(Comparator.reverseOrder());
            Integer a = list.get(0);
            Integer b = list.get(1);
            if (2 < list.size() && a.equals(b) && 0 >= list.get(2)) {
                break;
            }
            list.add((a >= b) ? a - b : b - a);
            System.out.println(list);
            list.remove(a);
            list.remove(b);
        } while (2 < list.size());
        Integer a1 = list.get(0);
        Integer b1 = list.get(1);
        return a1.equals(b1) ? a1 : a1 - b1;
    }
}
