package com.user.util.pachong;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;

public class IpPool {

    public static void main(String[] args) throws IOException {
        Connection connect = Jsoup.connect("https://www.baidu.com/s?wd=js%E9%81%8D%E5%8E%86div&rsv_spt=1&rsv_iqid=0xb14fd49000002e81&issp=1&f=8&rsv_bp=1&rsv_idx=2&ie=utf-8&rqlang=cn&tn=baiduhome_pg&rsv_enter=0&rsv_dl=tb&oq=js%25E9%2581%258D%25E5%258E%2586div%25E5%2586%2585%25E7%259A%2584%25E6%2589%2580%25E6%259C%2589%25E5%25AD%2590div&rsv_btype=t&rsv_t=4f44dmUmfr6XPD2TOQMUg2KIFDuJa4u7QphW2BEAY0BfR4JGBTKvPSyeomJoH7wsuuT6&rsv_pq=86a402c000006823&inputT=180&rsv_sug3=316&rsv_sug1=107&rsv_sug7=100&rsv_sug2=0&rsv_sug4=596");
        Document document = connect.get();
        // 获取所有元素内容
        Elements allElements = document.getAllElements();
        for (Element element : allElements) {
            System.out.println(element.text());
        }


















    }
    // private List<IPBean> crawl(String api, int index){
    //     String html = HttpUtils.getResponseContent(api + index);
    //     System.out.println(html);
    //
    //     Document document = Jsoup.parse(html);
    //     Elements eles = document.selectFirst("table").select("tr");
    //
    //     for (int i = 0; i < eles.size(); i++){
    //         if (i == 0) {
    //             continue;
    //         }
    //         Element ele = eles.get(i);
    //         String ip = ele.children().get(1).text();
    //         int port = Integer.parseInt(ele.children().get(2).text().trim());
    //         String typeStr = ele.children().get(5).text().trim();
    //
    //         int type;
    //         if ("HTTP".equalsIgnoreCase(typeStr)) {
    //             type = IPBean.TYPE_HTTP;
    //         } else {
    //             type = IPBean.TYPE_HTTPS;
    //         }
    //
    //         IPBean ipBean = new IPBean(ip, port, type);
    //         ipList.add(ipBean);
    //     }
    //     return ipList;
    // }
}
