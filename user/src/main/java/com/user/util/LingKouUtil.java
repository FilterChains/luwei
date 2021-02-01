package com.user.util;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

public class LingKouUtil {

    public static void main(String[] args) {
        int[] num1 = {1, 0, 3, 12, 0, 7, 8, 0, 9};
        int[] num2 = {0, 2, 1, 0, 0, 8};
        //System.err.println("题解:" + moveZeroes(num1));
        moveZeroes(num1);
    }

    /**
     * <p>@description : 删除排序数组中的重复项
     * 给定一个排序数组，你需要在 原地 删除重复出现的元素，使得每个元素只出现一次，返回移除后数组的新长度。
     * 不要使用额外的数组空间，你必须在 原地 修改输入数组 并在使用 O(1) 额外空间的条件下完成。
     * <p>
     * 示例 1:
     * 给定数组 nums = [1,1,2],
     * 函数应该返回新的长度 2, 并且原数组 nums 的前两个元素被修改为 1, 2。
     * 你不需要考虑数组中超出新长度后面的元素。
     * </p>
     * <p>@author : Wei.Lu</p>
     * <p>@date : 2020/6/24 10:12 </p>
     *
     * @return
     **/
    public static int removeDuplicates(int[] nums) {
        if (null == nums) {
            return 0;
        }
        final int length = nums.length;
        if (0 == length) {
            return 0;
        }
        int j = 0;
        for (int i = 0; i < length; i++) {
            // 移动位置即可
            if (nums[j] != nums[i]) {
                j++;
                nums[j] = nums[i];
            }
        }
        for (int i = 0; i < nums.length; i++) {
            System.out.print("答案:" + nums[i]);
        }
        return j + 1;
    }


    /**
     * <p>@description : 买卖股票的最佳时机 II
     * 给定一个数组，它的第 i 个元素是一支给定股票第 i 天的价格。
     * 设计一个算法来计算你所能获取的最大利润。你可以尽可能地完成更多的交易（多次买卖一支股票）。
     * 注意：你不能同时参与多笔交易（你必须在再次购买前出售掉之前的股票）。
     * <p>
     * 示例 1:
     * 输入: [7,1,5,3,6,4]
     * 输出: 7
     * 解释: 在第 2 天（股票价格 = 1）的时候买入，在第 3 天（股票价格 = 5）的时候卖出, 这笔交易所能获得利润 = 5-1 = 4 。
     * 随后，在第 4 天（股票价格 = 3）的时候买入，在第 5 天（股票价格 = 6）的时候卖出, 这笔交易所能获得利润 = 6-3 = 3 。
     *
     * </p>
     * <p>@author : Wei.Lu</p>
     * <p>@date : 2020/6/24 11:41 </p>
     *
     * @param prices
     * @return
     **/
    @Deprecated
    public static int maxProfit(int[] prices) {
        if (null == prices) {
            return 0;
        }
        final int length = prices.length;
        if (0 == length) {
            return 0;
        }
        Map<Integer, Integer> map = new HashMap<>(16);
        for (int i = 0; i < length; i++) {
            final int price = prices[i];
            int temp = 0;
            for (int j = i + 1; j < length; j++) {
                int rt = prices[j] - price;
                if (0 < rt && temp < rt) {
                    temp = rt;
                }
            }
            System.out.println("最大值：" + temp);
            map.put(price, temp);
        }
        int sum = map.values().stream().mapToInt(Integer::intValue).sum();
        System.out.println("结果：" + sum);
        return 0;
    }

    /**
     * <p>@description : 旋转数组
     * 给定一个数组，将数组中的元素向右移动 k 个位置，其中 k 是非负数。
     * 示例 1:
     * 输入: [1,2,3,4,5,6,7] 和 k = 3
     * 输出: [5,6,7,1,2,3,4]
     * 解释:
     * 向右旋转 1 步: [7,1,2,3,4,5,6]
     * 向右旋转 2 步: [6,7,1,2,3,4,5]
     * 向右旋转 3 步: [5,6,7,1,2,3,4] </p>
     * <p>@author : Wei.Lu</p>
     * <p>@date : 2020/6/28 10:29 </p>
     *
     * @param nums
     * @param k
     **/
    public static void rotate(int[] nums, int k) {
        if (0 < k && Objects.nonNull(nums) && 0 < nums.length) {
            int temp = 0, len = nums.length - 1, flag;
            for (int i = 0; i < k; i++) {
                if (temp != k) {
                    flag = nums[len];
                    for (int j = len; j >= 0; j--) {
                        if (0 != j) {
                            nums[j] = nums[j - 1];
                        }
                    }
                    nums[0] = flag;
                    temp++;
                }
            }
            for (int i = 0; i < nums.length; i++) {
                System.err.print("答案：" + nums[i]);
            }
        }
    }

    /**
     * <p>@description :  存在重复元素
     * 给定一个整数数组，判断是否存在重复元素。
     * 如果任意一值在数组中出现至少两次，函数返回 true 。如果数组中每个元素都不相同，则返回 false 。
     * 示例 1:
     * 输入: [1,2,3,1]
     * 输出: true
     * </p>
     * <p>@author : Wei.Lu</p>
     * <p>@date : 2020/6/29 10:58 </p>
     *
     * @param nums
     * @return
     **/
    public static boolean containsDuplicate(int[] nums) {
        if (null == nums || 0 == nums.length) {
            return false;
        }
        int len = nums.length;
        Set<Integer> set = new HashSet<>(len);
        for (int num : nums) {
            if (set.contains(num)) {
                return true;
            }
            set.add(num);
        }
        return false;
    }


    /**
     * <p>@description : 只出现一次的数字
     * 给定一个非空整数数组，除了某个元素只出现一次以外，其余每个元素均出现两次。找出那个只出现了一次的元素。
     * 说明：
     * 你的算法应该具有线性时间复杂度。 你可以不使用额外空间来实现吗？
     * 示例 1:
     * 输入: [2,2,1]
     * 输出: 1
     * </p>
     * <p>@author : Wei.Lu</p>
     * <p>@date : 2020/6/29 11:35 </p>
     *
     * @param nums
     * @return
     **/
    public static int singleNumber(int[] nums) {
        if (null == nums || 0 == nums.length) {
            return 0;
        }
        Map<Integer, Integer> map = new HashMap<>(16);
        for (int i = 0, len = nums.length; i < len; i++) {
            int num = nums[i];
            int k = 0;
            for (int j = 0; j < len; j++) {
                if (num == nums[j]) {
                    k++;
                }
            }
            map.put(num, k);
        }
        Set<Map.Entry<Integer, Integer>> entrySet = map.entrySet();
        for (Map.Entry<Integer, Integer> entry : entrySet) {
            if (Integer.valueOf("1").equals(entry.getValue())) {
                return entry.getKey();
            }
        }
        System.out.println("结果：" + map);
        return 0;
    }

    /**
     * <p>@description : 两个数组的交集 II
     * 给定两个数组，编写一个函数来计算它们的交集。
     * 示例 1:
     * 输入: nums1 = [1,2,2,1], nums2 = [2,2]
     * 输出: [2,2]
     * </p>
     * <p>
     * 说明：
     * 输出结果中每个元素出现的次数，应与元素在两个数组中出现的次数一致。
     * 我们可以不考虑输出结果的顺序。
     * </p>
     * <p>@author : Wei.Lu</p>
     * <p>@date : 2020/6/29 17:12 </p>
     *
     * @param nums1
     * @param nums2
     * @return
     **/
    @Deprecated
    public static int[] intersect(int[] nums1, int[] nums2) {
        if (null == nums1 || 0 == nums1.length ||
                null == nums2 || 0 == nums2.length) {
            return null;
        }
        final int length1 = nums1.length;
        final int length2 = nums2.length;
        int[] resultNum = new int[Math.min(length1, length2)];
        int temp = 0, flag = 0;
        if (length1 >= length2) {
            for (int i = 0; i < length2; i++) {
                int i1 = nums2[i];
                if (flag != i1) {
                    for (int j = 0; j < length1; j++) {
                        int i2 = nums1[j];
                        if (i1 == i2) {
                            resultNum[temp] = i2;
                            temp++;
                        }
                    }
                    flag = i1;
                }
            }


        }
        for (int i : resultNum) {
            System.out.print("结果:" + i);
        }
        return resultNum;
    }

    /**
     * <p>@description :   移动零
     * 给定一个数组 nums，编写一个函数将所有 0 移动到数组的末尾，同时保持非零元素的相对顺序。
     * 示例:
     * 输入: [0,1,0,3,12]
     * 输出: [1,3,12,0,0]
     * 说明:
     * 必须在原数组上操作，不能拷贝额外的数组。
     * 尽量减少操作次数 </p>
     * <p>@author : Wei.Lu</p>
     * <p>@date : 2020/6/29 21:56 </p>
     *
     * @param nums
     **/
    public static void moveZeroes(int[] nums) {
        if (null != nums && 1 < nums.length) {
            int temp = 0, j = 0, k = 0, len = nums.length;
            for (int i = 0; i < len; i++) {
                if (0 != nums[i]) {
                    if (0 < i - 1 && 0 == nums[i - 1]) {
                        temp = i - j;
                    }
                    k = nums[temp];
                    nums[temp] = nums[i];
                    nums[i] = k;
                    temp++;
                } else {
                    j++;
                }
            }
            for (int num : nums) {
                System.out.print(String.valueOf(num).concat(","));
            }
        }
    }

    public static void postMan() {
//        //参数排序
//        function sort (keys) {
//        for (var i = 0; i < keys.length - 1; i++) {
//            for (var j = 0; j < keys.length - 1 - i; j++) {
//                var total_1 = 0
//                var charKeys = keys[j].split('')
//                for (var index in charKeys){
//                    total_1 += charKeys[index].charCodeAt()
//                }
//
//                var total_2 = 0
//                charKeys = keys[j + 1].split('')
//                for (var index in charKeys){
//                    total_2 += charKeys[index].charCodeAt()
//                }
//
//                if (total_1 > total_2) {
//                    var temp = keys[j]
//                    keys[j] = keys[j + 1]
//                    keys[j + 1] = temp
//                }
//            }
//        }
//}
//        // 获取uri
//        var url = pm.request.url;
//        var uri = "";
//        for (let i in url.path){
//            uri += "/" + url.path[i];
//        }
//        console.log(uri);
//
//        // 获取参数，并排序，拼接成字符串
//        let queryParam = pm.request.url.query.members;
//        let paramsMap = {};
//        for (let i in queryParam){
//            paramsMap[queryParam[i].key] = queryParam[i].value;
//        }
//        let paramsStr = "";
//        console.log(paramsMap);
//        let paramsKeys = Object.keys(paramsMap);
//        sort(paramsKeys);
//        for (var i = 0; i < paramsKeys.length; i++) {
//            paramsStr += paramsKeys[i] + paramsMap[paramsKeys[i]];
//        }
//        console.log("参数字符串：" + paramsStr);
//
//        // 获取body
//        var body = pm.request.body;
//        console.log(body);
//        if (body != undefined && body.mode == "raw") {
//            body = JSON.parse(pm.request.body.raw);
//            console.log("----" + body);
//            let keys = Object.keys(body);
//            sort(keys);
//            for (var i = 0; i < keys.length; i++) {
//                paramsStr += keys[i] + body[keys[i]];
//            }
//            console.log("body参数字符串：" + paramsStr);
//        }
//
//        // 获取时间戳
//        var timestamp = Math.round(new Date().getTime());
//        pm.environment.set("timeStamp", timestamp);
//        console.log(timestamp);
//
//        // 获取appSecret
//        var appSecret = pm.environment.get("appSecret");
//        // 签名明文
//        var signStr = uri + paramsStr + timestamp + appSecret;
//        console.log(signStr);
//        var sign = CryptoJS.MD5(signStr).toString().toUpperCase();
//        pm.environment.set("sign", sign);
//        console.log(sign);
    }
}
