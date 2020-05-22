package com.luwei.pachong;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.net.ssl.SSLContext;

import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpHost;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.config.CookieSpecs;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLContextBuilder;
import org.apache.http.conn.ssl.TrustStrategy;
import org.apache.http.cookie.Cookie;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;



public class Demo {
    private String mainurl = "https://www.zhihu.com";
    private String email = "";
    private String password = "";
    private String _xsrf = "";
    boolean daili = false;
    HttpClientBuilder httpClientBuilder = HttpClientBuilder.create();
    //CloseableHttpClient httpClient = httpClientBuilder.build();
    CloseableHttpClient httpClient = createSSLClientDefault();
    private HttpHost proxy = new HttpHost("127.0.0.1",8888,"http");
    private RequestConfig config = RequestConfig.custom().setProxy(proxy).build();
    private String useage = "Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/45.0.2454.101 Safari/537.36";
    private RequestConfig configtime=RequestConfig.custom().setCircularRedirectsAllowed(true).setSocketTimeout(10000).setConnectTimeout(10000).build();

    public Demo() {

    }


    public Demo(String email, String password) {

        this.email = email;
        this.password = password;
    }
    // client工具函数，信任对方（https）所有证书
    private CloseableHttpClient createSSLClientDefault(){
        try {
            SSLContext sslContext = new SSLContextBuilder().loadTrustMaterial(null, new TrustStrategy() {
                //信任所有证书
                public boolean isTrusted(X509Certificate[] chain, String authType) throws CertificateException {
                    return true;
                }
            }).build();
            SSLConnectionSocketFactory sslFactory = new SSLConnectionSocketFactory(sslContext);
            return HttpClients.custom().setSSLSocketFactory(sslFactory).build();
        } catch (Exception e) {
        }
        return  HttpClients.createDefault();
    }

    public String getPageHtml(String url) {
        String html="";
        HttpGet httpget = new HttpGet(url);
        httpget.addHeader("User-Agent", useage);
        httpget.setConfig(configtime);
        try {
            CloseableHttpResponse response = httpClient.execute(httpget);
            HttpEntity entity = response.getEntity();
            html = EntityUtils.toString(entity, "utf-8");
            httpget.releaseConnection();
        } catch (ClientProtocolException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return html;
    }
    /**
     * 下载验证码到本地
     * @param url
     * @param desFileName
     * @return
     * @throws MalformedURLException
     */
    public boolean downloaderCapter(String url,String desFileName) throws MalformedURLException {
        boolean flag = false;
        String page = getPageHtml(url);
        Document doc = Jsoup.parse(page);
        Elements capchas = doc.select("img.Captcha-image");
        System.out.println(capchas.size());
        if (capchas.size()==0) {
            System.out.println("不需要验证码");
        }else {
            String caurl = "";
            //生成随机数
            Random rnd = new Random();
            StringBuilder sb = new StringBuilder();
            for(int i=0; i < 13; i++) {
                sb.append((char)('0' + rnd.nextInt(10)));
            }
            String id = sb.toString();
            caurl = "https://www.zhihu.com/captcha.gif?r="+id+"&type=login";
            //下载验证码图片
            File file = new File(desFileName);
            if (file.exists()) {
                file.delete();
            }
            try {
                HttpGet getCaptcha = new HttpGet(caurl);
                CloseableHttpResponse imageResponse = httpClient.execute(getCaptcha);
                byte[] bs = new byte[1024];
                int len;
                OutputStream os = new FileOutputStream(file);
                while ((len = imageResponse.getEntity().getContent().read(bs)) != -1) {
                    os.write(bs,0,len);
                    flag = true;
                }
                os.close();
            } catch (FileNotFoundException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        return flag;
    }
    /**
     * 获取_xsrf
     */
    public String get_xsrf(String url) {
        String page = getPageHtml(url);
        Document doc = Jsoup.parse(page);
        Elements srfs = doc.getElementsByAttributeValue("name", "_xsrf");
        //String xsrf = srfs.first().attr("value");
        return "";
    }


    public void login() throws IOException {
        List<NameValuePair> para = new ArrayList<NameValuePair>();
        _xsrf=get_xsrf(mainurl);
        System.out.println(_xsrf);
        para.add(new BasicNameValuePair("_xsrf", _xsrf));
        Map<String, String> header = new HashMap<String, String>();
        header.put("Content-Type", "application/x-www-form-urlencoded");
        header.put("Referer","https://www.zhihu.com/");
        header.put("User-Agent", useage);
        header.put("X-Requested-With", "XMLHttpRequest");
        header.put("Host", "www.zhihu.com");
        header.put("Origin", "https://www.zhihu.com");
        boolean flag = downloaderCapter(mainurl, "D:\\image.png");
        if (flag) {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            System.out.println("请输入验证码：");
            String captcha = br.readLine();
            para.add(new BasicNameValuePair("captcha",captcha));
        }
        para.add(new BasicNameValuePair("username", email));
        para.add(new BasicNameValuePair("password", password));
        para.add(new BasicNameValuePair("rememberme", "true"));
        HttpPost httppost = new HttpPost("https://www.zhihu.com/login/email");
        for (String string : header.keySet()) {
            httppost.addHeader(string, header.get(string));
        }
        httppost.addHeader("X-Xsrftoken", _xsrf);
        if (daili) {
            httppost.setConfig(config);
        }
        httppost.setEntity(new UrlEncodedFormEntity(para,"utf-8"));
        CloseableHttpResponse res = httpClient.execute(httppost);
        int statuts_code = res.getStatusLine().getStatusCode();
        System.out.println(statuts_code);
        System.out.println(EntityUtils.toString(res.getEntity(),"utf-8"));
        httppost.releaseConnection();
    }




    public static void main(String[] args) {

        Demo zhihu = new Demo("867285912@qq.com","luwei13320367983");
        try {
            zhihu.login();
            String html = zhihu.getPageHtml("https://www.zhihu.com/hot");
            System.out.println(html);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

}

