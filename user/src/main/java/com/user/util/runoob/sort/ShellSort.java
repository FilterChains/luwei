package com.user.util.runoob.sort;

/**
 * 希尔排序
 */
public class ShellSort {

    public static void sort(Comparable[] arr) {
        int j;
        for (int gap = arr.length / 2; gap > 0; gap /= 2) {
            for (int i = gap; i < arr.length; i++) {
                Comparable tmp = arr[i];
                for (j = i; j >= gap && tmp.compareTo(arr[j - gap]) < 0; j -= gap) {
                    arr[j] = arr[j - gap];
                }
                arr[j] = tmp;
            }
        }
    }

    public static void main(String[] args) {

        int N = 2000;
        Integer[] arr = SortTestHelper.generateRandomArray(N, 0, 10);
        ShellSort.sort(arr);
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i]);
            System.out.print(' ');
        }
    }
}