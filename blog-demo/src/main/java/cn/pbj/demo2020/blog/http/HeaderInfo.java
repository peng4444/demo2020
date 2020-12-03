package cn.pbj.demo2020.blog.http;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;

/**
 * @ClassName: HeaderInfo
 * @Author: pbj
 * @Date: 2020/6/4 19:21
 * @Description: TODO
 */
public class HeaderInfo {
    private final OkHttpClient client = new OkHttpClient();

    public void run() throws Exception {
        Request request = new Request.Builder()
                .url("https://api.github.com/repos/square/okhttp/issues")
                .header("User-Agent", "OkHttp Headers.java")
                .addHeader("Accept", "application/json; q=0.5")
                .addHeader("Accept", "application/vnd.github.v3+json")
                .build();

        try (Response response = client.newCall(request).execute()) {
            if (!response.isSuccessful()) throw new IOException("Unexpected code " + response);

            System.out.println("Server: " + response.header("Server"));
            System.out.println("Date: " + response.header("Date"));
            System.out.println("Vary: " + response.headers("Vary"));
        }
    }
}
