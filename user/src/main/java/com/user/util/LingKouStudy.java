package com.user.util;

import com.google.common.collect.Lists;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

/**
 * <p>@description : 领扣学习 </p>
 * <p>@author : Wei.Lu</p>
 * <p>@date : 2020/6/1 11:23 </p>
 **/
public class LingKouStudy {

    public static void main(String[] args) {
        int[][] matrix = {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};
        //System.out.println(findLengthOfShortestSubarray());
        String ad = "领】取快(克）【";
        String regEx = "[\n`~!@#$%^&*()+=|{}':;',\\[\\].<>/?~！@#￥%……&*（）——+|{}【】‘；：”“’。， 、？]";
        String regE = "\\p{Punct}";
        String s = ad.replaceAll(regEx, "");
        System.out.println("结果:" + s);
        System.out.println("包含" + s.contains("快克"));
    }

    /**
     * <p>@description : 实现一个算法，确定一个字符串 s 的所有字符是否全都不同。 </p>
     * <p>@author : Wei.Lu</p>
     * <p>@date : 2020/6/23 15:07 </p>
     *
     * @param astr
     * @return
     **/
    public static boolean isUnique(String astr) {
        if (null == astr) {
            return false;
        }
        String s = astr.replaceAll("\\s+", "");
        int length = astr.length(), len = length;
        if ("".equals(s) || 0 == length) {
            return true;
        }
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < length; i++) {
            final int pointAt = astr.codePointAt(i);
            if (list.contains(pointAt)) {
                len -= 1;
                break;
            }
            list.add(pointAt);
        }
        return length == len;
    }

    /**
     * <p>@description : 去除字符串空格 </p>
     * <p>@author : Wei.Lu</p>
     * <p>@date : 2020/6/23 16:01 </p>
     *
     * @param S
     * @param length
     * @return
     **/
    public static String replaceSpaces(String S, int length) {
        String str = new String(S);
        String rep = str.replaceAll("\\s+", "");
        if (!"".equals(rep)) {
            return str.substring(0, length).replaceAll("\\s+", "%20");
        } else {
            StringBuilder buffer = new StringBuilder();
            for (int i = 0; i < length; i++) {
                buffer.append("&20");
            }
            return buffer.toString();
        }
    }

    /**
     * <p>@description : 给定两个字符串 s1 和 s2，请编写一个程序，确定其中一个字符串的字符重新排列后，能否变成另一个字符串。 </p>
     * <p>@author : Wei.Lu</p>
     * <p>@date : 2020/6/23 15:07 </p>
     *
     * @param s1
     * @param s2
     * @return
     **/
    public static boolean CheckPermutation(final String s1, final String s2) {
        if (Objects.isNull(s1) || Objects.isNull(s2)) {
            return false;
        }
        final int length1 = s1.length(), length2 = s2.length();
        if (length1 != length2) {
            return false;
        }
        int num1 = 0, num2 = 0;
        for (int i = 0; i < length1; i++) {
            // 判断两个字符串的ASCII码是否相等
            num1 += s1.charAt(i);
            num2 += s2.charAt(i);
        }
        return num1 == num2;
    }

    /**
     * 三数之和
     * 给你一个包含 n 个整数的数组 nums，判断 nums 中是否存在三个元素 a，b，c ，使得 a + b + c = 0 ？请你找出所有满足条件且不重复的三元组。
     * 注意：答案中不可以包含重复的三元组。
     */
    public static List<List<Integer>> threeSum(int[] nums) {
        int length = nums.length;
        if (3 > length) {
            return Collections.emptyList();
        }
        Set<List<Integer>> list = new HashSet<>();
        for (int i = 0; i < length; i++) {
            for (int j = i + 1; j < length; j++) {
                for (int k = j + 1; k < length; k++) {
                    int num_i = nums[i];
                    int num_j = nums[j];
                    int num_k = nums[k];
                    if (0 == num_i + num_j + num_k) {
                        List<Integer> arrayList = new ArrayList<>();
                        arrayList.add(num_i);
                        arrayList.add(num_j);
                        arrayList.add(num_k);
                        list.add(arrayList);
                    }
                }
            }
        }
        return Lists.newArrayList(list);
    }

    /**
     * 给你一个字符串 s 和一个 长度相同 的整数数组 indices 。
     * 请你重新排列字符串 s ，其中第 i 个字符需要移动到 indices[i] 指示的位置。
     * 返回重新排列后的字符串
     * 输入：s = "codeleet", indices = [4,5,6,7,0,2,1,3]
     * 输出："leetcode"
     * 解释：如图所示，"codeleet" 重新排列后变为 "leetcode" 。
     * 输入：s = "aiohn", indices = [3,1,4,2,0]
     * 输出："nihao"
     */
    public static String restoreString(String s, int[] indices) {
        if (null == s || 0 == indices.length) {
            return null;
        }
        final int length = indices.length;
        Map<Integer, String> map = new HashMap<>(length);
        for (int i = 0; i < length; i++) {
            String substring = s.substring(i, i + 1);
            map.put(indices[i], substring);
        }
        return map.values().stream().reduce(String::concat).get();
    }

    /**
     * 房间中有 n 个灯泡，编号从 0 到 n-1 ，自左向右排成一行。最开始的时候，所有的灯泡都是 关 着的。
     * 请你设法使得灯泡的开关状态和 target 描述的状态一致，其中 target[i] 等于 1 第 i 个灯泡是开着的，等于 0 意味着第 i 个灯是关着的。
     * 有一个开关可以用于翻转灯泡的状态，翻转操作定义如下：
     * 选择当前配置下的任意一个灯泡（下标为 i ）
     * 翻转下标从 i 到 n-1 的每个灯泡
     * 翻转时，如果灯泡的状态为 0 就变为 1，为 1 就变为 0 。
     * 返回达成 target 描述的状态所需的 最少 翻转次数。
     * 示例 1：
     * 输入：target = "10111"
     * 输出：3
     * 解释：初始配置 "00000".
     * 从第 3 个灯泡（下标为 2）开始翻转 "00000" -> "00111"
     * 从第 1 个灯泡（下标为 0）开始翻转 "00111" -> "11000"
     * 从第 2 个灯泡（下标为 1）开始翻转 "11000" -> "10111"
     * 至少需要翻转 3 次才能达成 target 描述的状态
     */
    @Deprecated
    public static int minFlips(String target) {
        int count = 0;
        if (null == target) {
            return count;
        }
        for (int i = 0, length = target.length() - 1; i < length; i++) {
            String substring = target.substring(i, i + 1);
            if ("1".equals(substring)) {
                substring = "0";
                count++;
                continue;
            }
            if ("0".equals(substring)) {
                substring = "1";
                count++;
            }
            System.out.println(substring);
        }

        return 0;
    }

    /**
     * 给你一个整数 n 和一个整数数组 rounds 。有一条圆形赛道由 n 个扇区组成，扇区编号从 1 到 n 。现将在这条赛道上举办一场马拉松比赛，该马拉松全程由 m 个阶段组成。其中，第 i 个阶段将会从扇区 rounds[i - 1] 开始，到扇区 rounds[i] 结束。举例来说，第 1 阶段从 rounds[0] 开始，到 rounds[1] 结束。
     * 请你以数组形式返回经过次数最多的那几个扇区，按扇区编号 升序 排列。
     * 注意，赛道按扇区编号升序逆时针形成一个圆（请参见第一个示例）。
     * 示例 1：
     * 输入：n = 4, rounds = [1,3,1,2]
     * 输出：[1,2]
     * 解释：本场马拉松比赛从扇区 1 开始。经过各个扇区的次序如下所示：
     * 1 --> 2 --> 3（阶段 1 结束）--> 4 --> 1（阶段 2 结束）--> 2（阶段 3 结束，即本场马拉松结束）
     * 其中，扇区 1 和 2 都经过了两次，它们是经过次数最多的两个扇区。扇区 3 和 4 都只经过了一次。
     */
    public static List<Integer> mostVisited(int n, int[] rounds) {
        List<Integer> list = new ArrayList<>();
        if (0 == rounds.length) {
            return list;
        }
        int leftNumber = rounds[0];
        int rightNumber = rounds[rounds.length - 1];
        if (leftNumber <= rightNumber) {
            for (int i = leftNumber; i <= rightNumber; i++) {
                list.add(i);
            }
        } else {
            for (int i = 1; i <= rightNumber; i++) {
                list.add(i);
            }
            for (int i = leftNumber; i <= n; i++) {
                list.add(i);
            }
        }
        return list;
    }

    /**
     * 给你一个整数数组 arr ，请你删除一个子数组（可以为空），使得 arr 中剩下的元素是 非递减 的。
     * 一个子数组指的是原数组中连续的一个子序列。
     * 请你返回满足题目要求的最短子数组的长度。
     * 示例 1：
     * 输入：arr = [1,2,3,10,4,2,3,5]
     * 输出：3
     * 解释：我们需要删除的最短子数组是 [10,4,2] ，长度为 3 。剩余元素形成非递减数组 [1,2,3,3,5] 。
     * 另一个正确的解为删除子数组 [3,10,4] 。
     * [2, 2, 2, 1, 1, 1]
     */
    @Deprecated
    public static int findLengthOfShortestSubarray(int[] arr) {
        if (null == arr || 0 == arr.length) {
            return 0;
        }
        List<Integer> list = new ArrayList<>();
        int flag = 0;
        int length = arr.length;
        for (int i = 0, len = length - 1; i < len; i++) {
            int left = arr[i];
            int right = arr[i + 1];
            if (left > right) {
                if (0 == flag) {
                    list.add(left);
                    list.add(right);
                }
                flag++;
                continue;
            }
            int size = list.size() - 1;
            if (0 < size) {
                Integer num = list.get(size);
                if (num >= right) {
                    flag++;
                }
            }
        }
        return 2 == list.size() ? Math.max(flag, 2) : flag;
    }

    /**
     * 解数独
     */
    @Deprecated
    public static void solveSudoku(char[][] board) {
        final char a = '.';
        final int total = 45;
        for (int i = 0; i < 9; i++) {
            int sum = 0;
            for (int j = 0; j < 9; j++) {
                char c = board[i][j];
                System.out.println(c);
                if (a == c) {

                } else {
                    sum = total - c;
                }
                if (0 == sum) {
                    break;
                }
            }
        }
    }


    private static Date getDateBefore(int day) {
        Calendar now = Calendar.getInstance();
        now.setTime(new Date());
        now.set(Calendar.DATE, now.get(Calendar.DATE) - day);
        return now.getTime();
    }
}
