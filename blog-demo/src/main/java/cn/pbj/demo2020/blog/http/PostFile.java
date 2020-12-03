package cn.pbj.demo2020.blog.http;

import okhttp3.*;

import java.io.File;
import java.io.IOException;

/**
 * @ClassName: PostFile
 * @Author: pbj
 * @Date: 2020/6/4 19:22
 * @Description: TODO
 */
public class PostFile {
    public static final MediaType MEDIA_TYPE_MARKDOWN
            = MediaType.parse("text/x-markdown; charset=utf-8");

    private final OkHttpClient client = new OkHttpClient();

    public void run() throws Exception {
        File file = new File("README.md");

        Request request = new Request.Builder()
                .url("https://api.github.com/markdown/raw")
                .post(RequestBody.create(MEDIA_TYPE_MARKDOWN, file))
                .build();

        try (Response response = client.newCall(request).execute()) {
            if (!response.isSuccessful()) throw new IOException("Unexpected code " + response);

            System.out.println(response.body().string());
        }
    }
}
