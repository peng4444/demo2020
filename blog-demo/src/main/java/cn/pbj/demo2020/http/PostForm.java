package cn.pbj.demo2020.http;

import okhttp3.*;

import java.io.IOException;

/**
 * @ClassName: PostForm
 * @Author: pbj
 * @Date: 2020/6/4 19:23
 * @Description: TODO
 */
public class PostForm {
    private final OkHttpClient client = new OkHttpClient();

    public void run() throws Exception {
        RequestBody formBody = new FormBody.Builder()
                .add("search", "Jurassic Park")
                .build();
        Request request = new Request.Builder()
                .url("https://en.wikipedia.org/w/index.php")
                .post(formBody)
                .build();

        try (Response response = client.newCall(request).execute()) {
            if (!response.isSuccessful()) throw new IOException("Unexpected code " + response);

            System.out.println(response.body().string());
        }
    }
}
