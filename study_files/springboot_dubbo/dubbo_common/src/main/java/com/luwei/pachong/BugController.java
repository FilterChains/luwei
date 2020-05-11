//package com.luwei.pachong;
//
//import org.jsoup.Jsoup;
//import org.jsoup.nodes.Document;
//import org.jsoup.nodes.Element;
//import org.jsoup.select.Elements;
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.RequestMapping;
//
//import javax.annotation.Resource;
//import java.net.URL;
//import java.util.ArrayList;
//import java.util.List;
///**
// * @projectName： bugmessage
// * @packageName: com.luwei.bugmessage.cotroller
// * @auther: luwei
// * @description:
// * @date: 2019/5/21 23:18
// * @alert: This document is private to luwei
// * @version: 1.8.00_66
// */
//@Controller
//public class BugController {
//
//    //计算当前是多少页
//    private static int count=0;
//
//    @RequestMapping("/getMessage")
//    public void getBugMessage(HttpServletResponse response){
//
//        System.out.println("消息进来了");
//        String strUrl = "https://search.51job.com/list" +
//                "/060000,000000,0000,00,9,99,%25E5%25A4%25A7%25E6%2595%25B0%25E6%258D%25AE," +
//                "2,1.html?lang=c&stype=1&postchannel=0000&workyear=99&cotype=" +
//                "99&degreefrom=99&jobterm=99&companysize=99&lonlat=0%2C0&radius=" +
//                "-1&ord_field=0&confirmdate=9&fromType=&dibiaoid=0&address=" +
//                "&line=&specialarea=00&from=&welfare=";
//        //解析url
//        Document document = getDom(strUrl);
//        //解析当前页面信息
//        List<JobBean> list = getPageInfo(document);
//        System.out.println("---------------" + (++count) + "-------------");
//        //在控制台console 打印爬下来的信息；
//        for (JobBean jobBean : list) {
//            System.err.println(jobBean);
//        }
//        try {
//            //判断当前的文本信息是否为空，即为空视为资源已被爬完。
//            if (null != document) {
//                getNextPageInfo(document);
//            }
//        } catch (Exception e) {
//            System.out.println("页面已爬完。。。。");
//            System.out.println("爬完后的信息："+bugmessage);
//            //将爬下来的信息存入excel表格中，其中bugmessage为存储所有爬下来的信息集合；
//            //messageService.getBugMessage(bugmessage,"前程无忧招聘信息",response);
//        }
//    }
//
//    /**
//     * @Title: getNextPageInfo
//     * @Description:绑定下一页 ，解析本页内容
//     * @param: @param document    参数
//     * @return: void    返回类型
//     * @throws:
//     */
//    private static void getNextPageInfo(Document document) {
//        //绑定页面下一页，标签属性 .bk即为当前页面中跳转页面的跟标签属性
//        Elements elements = document.select(".bk");
//        //获取本页面中，跳转页面中的相关url标签
//        Element element = elements.get(1);
//        //绑定，页数跳转a标签和a标签中的href属性,即下一页url
//        String strUrl = element.select("a").attr("href");
//        //如果strUrl为null，则表示到了最后一页
//        if (strUrl == null) {
//            return;
//        }
//        //获取下一页url中的全部Html内容
//        Document dom = getDom(strUrl);
//        /*System.err.println("处理信息:"+dom);*/
//        //爬取想要的信息,即指定爬取内容
//        List<JobBean> list = getPageInfo(dom);
//        /*System.out.println("集合内容:"+list);*/
//        System.err.println("---------------" + (++count) + "-------------");
//        //展示所爬取内容的结果
//        for (JobBean jobBean : list) {
//            //将爬下来的所有内容存入集合；
//            bugmessage.add(jobBean);
//            System.out.println(jobBean);
//        }
//        try {
//            Thread.sleep(400);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//
//        //递归遍历,模拟下一页
//        getNextPageInfo(dom);
//
//    }
//
//
//    /**
//     * @Title: getPageInfo
//     * @Description: 获取当前页面信息
//     * @param: @param document
//     * @param: @return    参数
//     * @return: List<JobBean>    返回类型
//     * @throws:
//     */
//    private static void getPageInfo(Document document) {
//
//        //获取页面中所要爬取信息的跟标签#resultList  .el表示跟标签中的子标签。
//        Elements elements = document.select("#resultList .el");
//        //获取一个删除一个，保证不重复爬
//        elements.remove(0);
//        //遍历标签
//        for (Element element : elements) {
//            //绑定所爬内容的根标签
//            Elements elements2 = element.select("span");
//            /*System.out.println("所爬内容："+elements2);*/
//            //实例化modle对象
//            //把所有对象传入modle中进行临时存储，根据get(x)x来定义所需内容的标签
//            /*jobBean.set(elements2.get(0).text(),
//                    elements2.get(1).text(),
//                    elements2.get(2).text(),
//                    elements2.get(3).text(),
//                    elements2.get(4).text());*/
//            //将model对象存入集合；
//        }
//    }
//
//
//    /**
//     * @Title: getDom
//     * @Description: 解析url, 获得当前url中的所有内容包含标签
//     * @param: @param strUrl
//     * @param: @return    参数
//     * @return: Document    返回类型
//     * @throws:
//     */
//    private static Document getDom(String strUrl) {
//        try {
//            URL url = new URL(strUrl);
//            Document document = Jsoup.parse(url, 4000);
//            /*System.err.println("url解析答案:"+document);*/
//            return document;
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return null;
//    }
//}
//
