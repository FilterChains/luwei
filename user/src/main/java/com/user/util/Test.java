package com.user.util;

import com.aliyuncs.utils.ParameterHelper;
import com.google.common.base.Splitter;
import com.google.common.collect.Lists;
import org.apache.commons.lang.time.DateFormatUtils;
import org.springframework.util.CollectionUtils;

import java.util.Date;
import java.util.List;
import java.util.Optional;

public class Test {
    private static final String REG_PRICE = "([1-9])|([1-9]\\d{1,7})|([1-9]\\d{1,7}\\.\\d{1,2})|([0-9]\\.\\d{1,2})";
    private static final String REG_ID = "([0-9]|[1-9]\\d{1,10})";

    public static void main(String[] args) {

        //System.out.println("0".matches(REG_ID));
        //
        //System.out.println(DateFormatUtils.format(new Date(), "yyyy年MM月dd日 HH:mm:ss"));
        //
        //BigDecimal b = BigDecimal.valueOf(1L);
        //System.out.println(b.compareTo(BigDecimal.TEN) < 0);

        //List<DemoTest> list1 = new ArrayList<>();
        //for (int i = 0; i < 1000; i++) {
        //    DemoTest demoTest = new DemoTest();
        //    demoTest.setIndex(i);
        //    list1.add(demoTest);
        //}
        //
        //List<DemoTest> list2 = new ArrayList<>();
        //DemoTest demoTest = null;
        //for (int i = 0; i < 1000; i++) {
        //    demoTest = new DemoTest();
        //    demoTest.setIndex(i);
        //    list2.add(demoTest);
        //}
        //
        //System.out.println(0 > BigDecimal.ZERO.compareTo(BigDecimal.valueOf(1L)));


        //System.out.println(new BigDecimal("10.0").stripTrailingZeros().toPlainString());
        //
        //String str = "123.jpg," +
        //        "34.jpg," +
        //        "1441.jpg," +
        //        "114.jpg," +
        //        "6545.jpg";
        //System.out.println("index_1:" + str.contains(","));
        //System.out.println("index_1:" + str.substring(str.indexOf(",") + 1));
        //
        //String s = "product/2018/6/23/5dafa3fa-47ec-4e1c-a8d3-fd05a218eec5.png";
        //System.out.println("index_2:" + s.contains(","));
        //System.out.println("asdasd" + s.substring(s.indexOf(",") + 1));
        //System.out.println(Math.multiplyExact(2L, 3L));
        // 不允许对象为空
        //Optional<String> s = Optional.of(str);
        //System.err.println(s.get());
        // 允许对象为空
        String str1 = null;
        Optional<String> s1 = Optional.ofNullable(str1);
        // Integer integer = Optional.of(new DemoTest()).map(DemoTest::getIndex).orElseGet(() -> 1);
        // Integer integer1 = Optional.of(new DemoTest()).map(DemoTest::getIndex).orElseThrow(() -> new ExcelExportException("asd"));

        String s = "1,2,3,4,5,6,7,8";
        System.out.println(Splitter.on(",").omitEmptyStrings().trimResults().splitToList(s));
        final Date pushDate = new Date(System.currentTimeMillis() + 3600 * 1000);
        //用于定时发送。不设置缺省是立即发送。时间格式按照ISO8601标准表示，并需要使用UTC时间，格式为`YYYY-MM-DDThh:mm:ssZ`。
        System.out.println(DateFormatUtils.format(pushDate, "yyyy-MM-dd HH:mm:ss"));

        final String pushTime = ParameterHelper.getISO8601Time(pushDate);

        System.err.println("当前时间:" + pushTime);

        System.out.println("1971539".matches(REG_ID));
        String u = " 1(Integer), 2(Integer), 3(Integer), 4(Integer), 5(Integer), 6(Integer), 7(Integer), 8(Integer), 10(Integer), 11(Integer), 12(Integer), 13(Integer), 14(Integer), 15(Integer), 16(Integer), 17(Integer), 18(Integer), 19(Integer), 20(Integer), 21(Integer), 22(Integer), 23(Integer), 24(Integer), 25(Integer), 26(Integer), 27(Integer), 28(Integer), 29(Integer), 30(Integer), 31(Integer), 32(Integer), 33(Integer), 34(Integer), 35(Integer), 36(Integer), 37(Integer), 38(Integer), 39(Integer), 40(Integer), 41(Integer), 42(Integer), 43(Integer), 44(Integer), 45(Integer), 46(Integer), 47(Integer), 48(Integer), 49(Integer), 50(Integer), 51(Integer), 52(Integer), 53(Integer), 54(Integer), 55(Integer), 56(Integer), 57(Integer), 58(Integer), 59(Integer), 60(Integer), 61(Integer), 62(Integer), 63(Integer), 64(Integer), 65(Integer), 67(Integer), 68(Integer), 69(Integer), 70(Integer), 71(Integer), 72(Integer), 73(Integer), 74(Integer), 75(Integer), 76(Integer), 77(Integer), 78(Integer), 79(Integer), 80(Integer), 81(Integer), 82(Integer), 83(Integer), 84(Integer), 85(Integer), 86(Integer), 87(Integer), 88(Integer), 89(Integer), 90(Integer), 91(Integer), 92(Integer), 93(Integer), 94(Integer), 95(Integer), 96(Integer), 97(Integer), 98(Integer), 99(Integer), 100(Integer), 101(Integer), 102(Integer), 103(Integer), 104(Integer), 105(Integer), 106(Integer), 107(Integer), 108(Integer), 109(Integer), 110(Integer), 112(Integer), 113(Integer), 114(Integer), 115(Integer), 116(Integer), 117(Integer), 118(Integer), 119(Integer), 120(Integer), 121(Integer), 122(Integer), 123(Integer), 124(Integer), 125(Integer), 126(Integer), 127(Integer), 128(Integer), 130(Integer), 131(Integer), 132(Integer), 133(Integer), 134(Integer), 135(Integer), 136(Integer), 137(Integer), 138(Integer), 139(Integer), 140(Integer), 141(Integer), 142(Integer), 143(Integer), 144(Integer), 145(Integer), 146(Integer), 147(Integer), 148(Integer), 149(Integer), 150(Integer), 151(Integer), 152(Integer), 153(Integer), 154(Integer), 155(Integer), 156(Integer), 157(Integer), 158(Integer), 159(Integer), 160(Integer), 161(Integer), 162(Integer), 163(Integer), 164(Integer), 165(Integer), 166(Integer), 167(Integer), 168(Integer), 169(Integer), 170(Integer), 171(Integer), 172(Integer), 173(Integer), 174(Integer), 175(Integer), 176(Integer), 177(Integer), 178(Integer), 179(Integer), 180(Integer), 181(Integer), 182(Integer), 183(Integer), 184(Integer), 185(Integer), 186(Integer), 187(Integer), 188(Integer), 189(Integer), 190(Integer), 191(Integer), 192(Integer), 193(Integer), 194(Integer), 195(Integer), 196(Integer), 197(Integer), 198(Integer), 199(Integer), 200(Integer), 201(Integer), 202(Integer), 203(Integer), 204(Integer), 205(Integer), 206(Integer), 207(Integer), 208(Integer), 209(Integer), 210(Integer), 211(Integer), 212(Integer), 213(Integer), 214(Integer), 215(Integer), 216(Integer), 217(Integer), 218(Integer), 219(Integer), 220(Integer), 221(Integer), 222(Integer), 223(Integer), 224(Integer), 225(Integer), 226(Integer), 227(Integer), 228(Integer), 229(Integer), 230(Integer), 231(Integer), 232(Integer), 233(Integer), 234(Integer), 235(Integer), 236(Integer), 237(Integer), 238(Integer), 239(Integer), 240(Integer), 241(Integer), 242(Integer), 243(Integer), 244(Integer), 245(Integer), 246(Integer), 247(Integer), 248(Integer), 249(Integer), 250(Integer), 251(Integer), 252(Integer), 253(Integer), 254(Integer), 255(Integer), 256(Integer), 257(Integer), 258(Integer), 259(Integer), 260(Integer), 261(Integer), 262(Integer), 263(Integer), 264(Integer), 265(Integer), 266(Integer), 267(Integer), 268(Integer), 270(Integer), 271(Integer), 272(Integer), 273(Integer), 274(Integer), 275(Integer), 276(Integer), 277(Integer), 278(Integer), 279(Integer), 281(Integer), 282(Integer), 288(Integer), 289(Integer), 290(Integer), 291(Integer), 292(Integer), 293(Integer), 294(Integer), 295(Integer), 296(Integer), 297(Integer), 298(Integer), 299(Integer), 300(Integer), 301(Integer), 302(Integer), 303(Integer), 304(Integer), 305(Integer), 306(Integer), 307(Integer), 308(Integer), 309(Integer), 310(Integer), 311(Integer), 312(Integer), 313(Integer), 314(Integer), 316(Integer), 317(Integer), 318(Integer), 319(Integer), 320(Integer), 321(Integer), 322(Integer), 323(Integer), 324(Integer), 325(Integer), 326(Integer), 327(Integer), 328(Integer), 329(Integer), 330(Integer), 332(Integer), 333(Integer), 334(Integer), 335(Integer), 337(Integer), 338(Integer), 339(Integer), 340(Integer), 341(Integer), 342(Integer), 343(Integer), 344(Integer), 345(Integer), 346(Integer), 347(Integer), 349(Integer), 350(Integer), 351(Integer), 352(Integer), 353(Integer), 354(Integer), 355(Integer), 357(Integer), 358(Integer), 359(Integer), 360(Integer), 361(Integer), 362(Integer), 363(Integer), 364(Integer), 365(Integer), 366(Integer), 367(Integer), 369(Integer), 370(Integer), 371(Integer), 372(Integer), 374(Integer), 377(Integer), 378(Integer), 379(Integer), 380(Integer), 381(Integer), 382(Integer), 384(Integer), 386(Integer), 389(Integer), 390(Integer), 391(Integer), 392(Integer), 393(Integer), 394(Integer), 395(Integer), 396(Integer), 397(Integer), 398(Integer), 399(Integer), 400(Integer), 401(Integer), 402(Integer), 403(Integer), 404(Integer), 405(Integer), 406(Integer), 407(Integer), 408(Integer), 409(Integer), 410(Integer), 411(Integer), 412(Integer), 413(Integer), 414(Integer), 416(Integer), 420(Integer), 421(Integer), 422(Integer), 423(Integer), 424(Integer), 426(Integer), 427(Integer), 428(Integer), 429(Integer), 430(Integer), 431(Integer), 432(Integer), 434(Integer), 435(Integer), 436(Integer), 437(Integer), 438(Integer), 440(Integer), 441(Integer), 442(Integer)";
        System.out.println(u.replace("(Integer)", ""));
        List<String> list = Lists.newArrayList("123", "231", "3123", "12312");
        System.out.println("处理后集合：" + listToString(list));

        System.out.println("集合截取：" + listSub(list, 2));


        String y = "product/2018/6/19/be63f444-0db5-4198-a500-c5fdfa1ee02d.jpg".concat(",");
        String uq = "product/2018/6/19/691ae17c-de38-4284-a786-47f64dea1203.jpg,product/2018/6/19/be63f444-0db5-4198-a500-c5fdfa1ee02d.jpg,product/2018/6/19/1e2018b2-1e1f-4aa2-b8b7-f88c574062ad.jpg,product/2018/6/19/36e7b2e8-8ceb-413a-afc6-ce729db871f4.jpg,product/2018/6/19/fe7b451a-8241-474b-8f31-90aad097efe7.jpg,";
        String replace = uq.replace(y, "");
        System.err.println("最后:" + replace);

        String u1 = "asda";
        System.out.println("分隔：" + Splitter.on(",").omitEmptyStrings().trimResults().splitToList(u1));


    }

    public static List<String> listSub(List<String> list, int subNum) {
        if (subNum >= list.size()) {
            return list;
        }
        return list.subList(0, subNum);
    }


    public static String listToString(List<String> list) {
        if (CollectionUtils.isEmpty(list)) {
            return null;
        }
        return list.toString().replace("[", "")
                .replace("]", "").replaceAll("\\s+", "");
    }

}
