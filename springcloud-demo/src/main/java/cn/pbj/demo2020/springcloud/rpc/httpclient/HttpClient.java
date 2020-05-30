package cn.pbj.demo2020.springcloud.rpc.httpclient;

import com.alibaba.ans.shaded.org.apache.http.HttpEntity;
import com.alibaba.ans.shaded.org.apache.http.HttpResponse;
import com.alibaba.ans.shaded.org.apache.http.NameValuePair;
import com.alibaba.ans.shaded.org.apache.http.client.entity.UrlEncodedFormEntity;
import com.alibaba.ans.shaded.org.apache.http.client.methods.HttpPost;
import com.alibaba.ans.shaded.org.apache.http.impl.client.CloseableHttpClient;
import com.alibaba.ans.shaded.org.apache.http.impl.client.HttpClients;
import com.alibaba.ans.shaded.org.apache.http.message.BasicNameValuePair;
import com.alibaba.fastjson.JSON;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName: HttpClient
 * @Author: pbj
 * @Date: 2020/5/30 10:08
 * @Description: TODO
 */
public class HttpClient {
    public static void main(String[] args) throws IOException {
        //发送远程的http请求的地址
        String url = "http://localhost:7070/order/loadOrderList2";
        //创建HttpClient对象
        CloseableHttpClient client = HttpClients.createDefault();
        //创建HttpPost对象, 发送post请求
        HttpPost method = new HttpPost(url);
        //封装发送到服务提供者的参数
        NameValuePair id = new BasicNameValuePair("uid", "10001");
        List<NameValuePair> params = new ArrayList<>();
        params.add(id);
        //封装请求体数据
        method.setEntity(new UrlEncodedFormEntity(params, "UTF-8"));
        //发送具体的http请求
        HttpResponse response = client.execute(method);

        //获得服务提供者响应的具体数据
        HttpEntity entity = response.getEntity();
        //获得http的响应体
        InputStream is = entity.getContent();

        int len = 0;
        char[] buf = new char[1024];
        //使用字符流读
        InputStreamReader reader = new InputStreamReader(is);
        StringBuffer sb = new StringBuffer();
        while((len = reader.read(buf)) != -1){
            sb.append(String.valueOf(buf, 0, len));
        }
        System.out.println(sb);

        //将响应回来的json字符串解析为Order集合
        List<Order> list = JSON.parseArray(sb.toString(), Order.class);
        for(Order o : list){
            System.out.println(o.getId() + "\t" + o.getTotal() + "\t" + o.getDate());
        }
    }
}
