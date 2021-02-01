package com.user.util.pachong;// package com.luwei.pachong;
//
// import com.alibaba.fastjson.JSONObject;
// import org.apache.http.HttpEntity;
// import org.apache.http.client.methods.CloseableHttpResponse;
// import org.apache.http.client.methods.HttpGet;
// import org.apache.http.client.methods.HttpUriRequest;
// import org.apache.http.client.methods.RequestBuilder;
// import org.apache.http.impl.client.CloseableHttpClient;
// import org.apache.http.impl.client.HttpClients;
// import org.apache.http.util.EntityUtils;
// import org.jsoup.Connection;
// import org.jsoup.Jsoup;
// import org.jsoup.helper.HttpConnection;
// import org.jsoup.nodes.Document;
// import org.jsoup.nodes.Element;
// import org.jsoup.select.Elements;
//
// import java.io.IOException;
// import java.net.URI;
// import java.net.URISyntaxException;
// import java.net.URL;
// import java.util.HashMap;
// import java.util.Map;
//
// /**
//  * @projectName： springboot_dubbo
//  * @packageName: com.luwei.pachong
//  * @auther: luwei
//  * @description: 爬取知乎信息
//  * @date: 2020/3/14 17:20
//  * @alert: This document is private to luwei
//  * @version: 1.8.00_66
//  */
// public class PcDemo {
//
//     private static final String zhiHuUrl = "https://www.zhihu.com/hot";
//     private static final String zhiHuLogin = "https://www.zhihu.com/signin?next=%2Fhot";
//
//
//     public static void main(String[] args) {
//         //解析url
//         Document document = null;
//         try {
//             getDom(zhiHuUrl,zhiHuLogin);
//         } catch (Exception e) {
//             e.printStackTrace();
//         }
//         // 解析页面数据
//         getPageInfo(document);
//         System.out.println(document);
//     }
//
//     private static void getPageInfo(Document document) {
//
//         Elements select = document.select(".SignContainer-inner");
//         System.out.println(select);
//         Elements elements = document.select("#TopstoryContent .ListShortcut .Topstory-hot HotList .HotList-list");
//         //获取一个删除一个，保证不重复爬
//         elements.remove(0);
//         //遍历标签
//         for (Element element : elements) {
//             System.out.println(element);
//             //绑定所爬内容的根标签
//             //Elements elements2 = element.select("span");
//             /*System.out.println("所爬内容："+elements2);*/
//             //实例化modle对象
//             //把所有对象传入modle中进行临时存储，根据get(x)x来定义所需内容的标签
//             /*jobBean.set(elements2.get(0).text(),
//                     elements2.get(1).text(),
//                     elements2.get(2).text(),
//                     elements2.get(3).text(),
//                     elements2.get(4).text());*/
//             //将model对象存入集合；
//         }
//     }
//
//
//     /**
//      * @Title: getDom
//      * @Description: 解析url, 获得当前url中的所有内容包含标签
//      * @param: @strUrl    参数
//      * @return: Document    返回类型
//      */
//     private static void getDom(String strUrl,String loginUrl) throws Exception{
//         // 构造登陆参数
//         Map<String,String> data = new HashMap<>();
//         data.put("name","19922218826");
//         data.put("password","luwei13320367983");
//         data.put("remember","false");
//         data.put("ticket","");
//         data.put("ck","");
//         Connection.Response login = Jsoup.connect(loginUrl)
//                 .ignoreContentType(true) // 忽略类型验证
//                 .followRedirects(false) // 禁止重定向
//                 .postDataCharset("utf-8")
//                 .header("Upgrade-Insecure-Requests","1")
//                 .header("Accept","application/json")
//                 .header("Content-Type","application/x-www-form-urlencoded")
//                 .header("X-Requested-With","XMLHttpRequest")
//                 .header("User-Agent","Mozilla/5.0 (X11; Linux x86_64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/75.0.3770.100 Safari/537.36")
//                 .data(data)
//                 .method(Connection.Method.POST)
//                 .execute();
//         login.charset();
//         System.out.println("登录成功响应数据"+login.statusCode());
//         System.out.println("登录成功响应数据"+login.cookies());
//
//         // login 中已经获取到登录成功之后的cookies
//         // 构造访问个人中心的请求
//         Document document = Jsoup.connect(strUrl)
//                 // 取出login对象里面的cookies
//                 .cookies(login.cookies())
//                 .get();
//         if (document != null) {
//             Elements element = document.select("#TopstoryContent");
//             if (element.size() == 0) {
//                 System.out.println("登陆失败");
//             }
//         } else {
//             System.out.println("出错啦！！！！！");
//         }
//     }
//
// }
