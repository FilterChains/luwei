package com.luwei.pachong;

import java.awt.image.BufferedImage;
import java.io.*;
import java.nio.ByteBuffer;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;
import javax.imageio.ImageIO;

import org.apache.http.Consts;
import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.StatusLine;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.config.CookieSpecs;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.jboss.netty.handler.codec.protobuf.ProtobufDecoder;
import org.jboss.netty.handler.codec.protobuf.ProtobufVarint32FrameDecoder;

public class LogIn {

    private String indexURL = "https://www.zhihu.com/";
    private String loginURL = "https://www.zhihu.com/login/email";
    private String captchaURL = "https://www.zhihu.com/captcha.gif?type=login";
    protected RequestConfig requestConfig = null;
    protected CloseableHttpClient httpClient = null;

    public LogIn(String indexURL, String loginURL, String captchaURL) {
        super();
        this.indexURL = indexURL;
        this.loginURL = loginURL;
        this.captchaURL = captchaURL;
        requestConfig = RequestConfig.custom().setCookieSpec(CookieSpecs.STANDARD_STRICT).build();
        httpClient = HttpClients.custom().setDefaultRequestConfig(requestConfig).build();
    }

    public LogIn() {
        super();
        requestConfig = RequestConfig.custom().setCookieSpec(CookieSpecs.STANDARD_STRICT).build();
        httpClient = HttpClients.custom().setDefaultRequestConfig(requestConfig).build();
    }

    //获取XSRF
    public String getXSRF() throws ClientProtocolException, IOException {
        HttpGet get = new HttpGet(indexURL);
        get.setHeader("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/62.0.3202.94 Safari/537.36");
        get.setHeader("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng,*/*;q=0.8");
        get.setHeader("Accept-Encoding", "gzip,deflate,br");
        get.setHeader("Cache-Control", "no-cache");
        get.setHeader("Connection", "keep-alive");
        get.setHeader("Accept-Language", "zh-CN,zh;q=0.8");
        get.setHeader("Origin", "https://www.zhihu.com");
        get.setHeader("Referer", "https://www.zhihu.com/");

        HttpEntity entity = httpClient.execute(get).getEntity();
        String responseHtml = EntityUtils.toString(entity);
        String[] split = responseHtml.split("<input type=\"hidden\" name=\"_xsrf\" value=\"");
        return split[0];
    }

    //获取验证码
    public String getCaptcha() throws ClientProtocolException, IOException {
        CloseableHttpResponse response = httpClient.execute(new HttpGet(captchaURL));
        InputStream input = response.getEntity().getContent();
        BufferedImage bio = ImageIO.read(input);
        File w2 = new File("src/QQ.jpg");
        ImageIO.write(bio, "jpg", w2);
        input.close();
        response.close();
        String captcha = null;
        Scanner s = new Scanner(System.in);
        System.out.print("captcha:");
        captcha = s.nextLine();
        s.close();
        return captcha;
    }

    //获取登陆后的响应状态，包含cookie信息
    public HttpResponse logIn(String email, String password) throws ClientProtocolException, IOException {
        List<NameValuePair> valuePairs = new LinkedList<NameValuePair>();
        valuePairs.add(new BasicNameValuePair("_xsrf", getXSRF()));
        valuePairs.add(new BasicNameValuePair("email", email));
        valuePairs.add(new BasicNameValuePair("password", password));
        valuePairs.add(new BasicNameValuePair("captcha", getCaptcha()));
        UrlEncodedFormEntity entity = new UrlEncodedFormEntity(valuePairs, Consts.UTF_8);
        HttpPost post = new HttpPost(loginURL);
        post.setEntity(entity);
        HttpResponse httpResponse = httpClient.execute(post);

        return httpResponse;
    }

    //根据cookie信息，访问其他页面
    public String visitURL(HttpResponse httpResponse, String url) throws ClientProtocolException, IOException {
        HttpGet get = new HttpGet("http://www.zhihu.com/question/following");
        Header[] headers = httpResponse.getHeaders("Set-Cookie");
        for (int i = 0; i < headers.length; i++) {
            get.addHeader(headers[i]);
        }
        CloseableHttpResponse r = httpClient.execute(get);
        String content = EntityUtils.toString(r.getEntity());
        System.out.println(content);
        r.close();
        return null;
    }

    /**
     * @param args
     * @throws IOException
     * @throws ClientProtocolException
     */
    public static void main(String[] args) throws ClientProtocolException, IOException {
        LogIn login = new LogIn();
        HttpResponse httpResponse = login.logIn("867285912@qq.com", "luwei13320367983");
        StatusLine responseState = httpResponse.getStatusLine();
        System.out.println(responseState.toString());
        Header[] headers = httpResponse.getAllHeaders();
        for (int i = 0; i < headers.length; i++) {
            System.out.println(headers[i].getName() + ": " + headers[i].getValue());
        }
        HttpEntity httpEntiey = httpResponse.getEntity();
        String responseString = EntityUtils.toString(httpEntiey);
        System.out.println(responseString);
//		{"r":0,
//			 "msg": "\u767b\u5f55\u6210\u529f"
//			}

        login.visitURL(httpResponse, "http://www.zhihu.com/question/following");
    }

}
