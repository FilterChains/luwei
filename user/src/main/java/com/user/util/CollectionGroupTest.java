package com.user.util;//package com.luwei.util;
//
//import java.util.ArrayList;
//import java.util.Collection;
//import java.util.HashMap;
//import java.util.Iterator;
//import java.util.List;
//import java.util.Map;
//import java.util.Set;
//
///**
// * 模拟中国电信翼支付的分账功能接口调用的参数字符串
// * 根据分组依据对集合进行分组
// *
// * @author ZhangBing
// */
//public class CollectionGroupTest {
//    /***
//     * 分组依据接口，用于集合分组时，获取分组依据
//     * @author ZhangBing
//     * @date 20140530
//     * @param <T>
//     */
//    interface GroupBy<T> {
//        T groupBy(Object obj);
//    }
//
//    /**
//     * 模拟订单实体类
//     *
//     * @author ZhangBing
//     */
//    private static class ItemInfo {
//        private String id;//订单ID
//        private String shopid;//店铺ID
//        private String fxSplitMoney;//分账金额
//        private String itemValue;//物品价格
//        /** 省略getter setter方法 */
//        .....
//    }
//
//    /**
//     * 集合临时转换类,跟模拟订单实体类对应
//     *
//     * @author ZhangBing
//     * @date 2014-5-30 上午10:38:48
//     */
//    private static class MyData {
//        private String id;
//        private String shopId;
//        private String itemValue;
//        private String fxMoney;
//
//        public String getFxMoney() {
//            return fxMoney;
//        }
//
//        public MyData setFxMoney(String fxMoney) {
//            this.fxMoney = fxMoney;
//            return this;
//        }
//
//        public String getItemValue() {
//            return itemValue;
//        }
//
//        public MyData setItemValue(String itemValue) {
//            this.itemValue = itemValue;
//            return this;
//        }
//
//        public String getId() {
//            return id;
//        }
//
//        public MyData setId(String id) {
//            this.id = id;
//            return this;
//        }
//
//        public String getShopId() {
//            return shopId;
//        }
//
//        public MyData setShopId(String shopId) {
//            this.shopId = shopId;
//            return this;
//        }
//    }
//
//    /**
//     * 分组依据实现
//     *
//     * @param <T>
//     * @param <D>
//     * @param colls
//     * @param gb
//     * @return
//     */
//    private static final <T extends Comparable<T>, D> Map<T, List<D>> group(Collection<D> colls, GroupBy<T> gb) {
//        if (colls == null || colls.isEmpty()) {
//            System.out.println("分组集合不能为空!");
//            return null;
//        }
//        if (gb == null) {
//            System.out.println("分组依据接口不能为Null!");
//            return null;
//        }
//        Iterator<D> iter = colls.iterator();
//        Map<T, List<D>> map = new HashMap<T, List<D>>();
//        while (iter.hasNext()) {
//            D d = iter.next();
//            T t = gb.groupBy(d);
//            if (map.containsKey(t)) {
//                map.get(t).add(d);
//            } else {
//                List<D> list = new ArrayList<D>();
//                list.add(d);
//                map.put(t, list);
//            }
//        }
//        return map;
//    }
//
//    /**
//     * 根据店铺号返回该店铺的商户号
//     *
//     * @param shopId 店铺号
//     * @return 商户号
//     * @throws Exception
//     */
//    private static String getByShopID(String shopId) throws Exception {
//        if ("10001".equals(shopId)) {
//            return "0018888881";
//        } else if ("10002".equals(shopId)) {
//            return "0018888882";
//        } else {
//            throw new Exception("找不到改店铺的商户号！");
//        }
//    }
//
//    /***
//     * 设置分账信息、组装分账信息参数字符串
//     * @param orderIds 订单号码
//     * @param ordersAmount 订单总金额
//     * @return 分账信息参数字符串
//     * @throws Exception
//     * @author ZhangBing
//     * @date 20140527
//     * @version 2
//     */
//    private static String getPaymentDivDetails(List<ItemInfo> items, String ordersAmount) throws Exception {
//        //分账字符串
//        String divs = "";
//        String div = "";
//
//        //主商户号1200000016
//        String mainSid = "1200000016";
//
//        List<MyData> list = new ArrayList<MyData>();
//        for (ItemInfo item : items) {
//            list.add(new MyData().setId(item.getId()).setShopId(item.getShopid())
//                    .setFxMoney(item.getFxSplitMoney()).setItemValue(item.getItemValue()));
//        }
//
//        //对得到的集合进行分组
//        Map<String, List<MyData>> map = group(list, new GroupBy<String>() {
//            public String groupBy(Object obj) {
//                MyData d = (MyData) obj;
//                return d.getShopId();    // 分组依据为店铺ID
//            }
//        });
//        //主商户分账总金额
//        Double mainMoney = 0.0;
//        Set<String> set = map.keySet();
//        for (Object key : set) {
//            //System.out.println(key);
//            String shopId = (String) key;
//            //System.out.println("子商户号："+shop.getFxshopSid());
//            List<MyData> datas = map.get(key);
//            Double fxMoney = 0.0;
//            //子商户号
//            String subSid = getByShopID(shopId);
//            for (MyData d : datas) {
//                //System.out.println("价格:"+d.getItemValue());
//                //System.out.println("分账:"+d.fxMoney);
//
//                //获取商品的子商户分账金额
//                Double fxSplitMoney = Double.parseDouble(d.fxMoney);
//                //计算主商户分账总金额
//                mainMoney += Double.parseDouble(d.getItemValue()) - fxSplitMoney;
//                //计算子商户分账金额
//                fxMoney += fxSplitMoney;
//                //System.out.println("子商户号:"+subSid+" 子商号分账总金额:"+fxMoney);
//            }
//            divs += subSid + ":" + fxMoney + "|";
//            //System.out.println(divs);
//        }
//        //System.out.println("主商户号:"+mainSid+" 主商号分账总金额:"+mainMoney);
//        div = mainSid + ":" + mainMoney + "|" + divs;
//        //System.out.println(div);
//        div = div.substring(0, div.length() - 1);
//        //System.out.println(div);
//        return div;
//    }
//
//    public static void main(String[] args) {
//        /**
//         * 模拟有一个订单，订单下有三个物品
//         * ItemInfo+ordersAmount组成了一个订单信息
//         */
//        //订单总金额/元
//        String ordersAmount = "3000";
//
//        //物品1
//        ItemInfo ii = new ItemInfo();
//        ii.setId("1");                //订单表ID
//        ii.setFxSplitMoney("100");    //分账金额，比如这个物品1价格为1000元，需要分给平台100元
//        ii.setItemValue("1000");    //物品价格
//        ii.setShopid("10001");        //店铺号
//        //物品2
//        ItemInfo ii2 = new ItemInfo();
//        ii2.setId("2");
//        ii2.setFxSplitMoney("300");
//        ii2.setItemValue("1000");
//        ii2.setShopid("10001");
//        //物品3
//        ItemInfo ii3 = new ItemInfo();
//        ii3.setId("3");
//        ii3.setFxSplitMoney("200");
//        ii3.setItemValue("1000");
//        ii3.setShopid("10002");
//
//        List<ItemInfo> items = new ArrayList<>();
//        items.add(ii);
//        items.add(ii2);
//        items.add(ii3);
//        try {
//            //得到这样的字符串：1200000016:2400.0|0018888881:400.0|0018888882:200.0
//            String divDetails = getPaymentDivDetails(items, ordersAmount);
//            System.out.println(divDetails);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
//}
