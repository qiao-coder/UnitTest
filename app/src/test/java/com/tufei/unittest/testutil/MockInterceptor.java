package com.tufei.unittest.testutil;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.Protocol;
import okhttp3.Response;
import okhttp3.ResponseBody;
import okio.BufferedSource;
import okio.Okio;

/**
 * @author tufei
 * @date 2018/3/11.
 */

public class MockInterceptor implements Interceptor {

    private String fileName;

    public MockInterceptor(String fileName) {
        this.fileName = fileName;
    }

    @Override
    public Response intercept(Interceptor.Chain chain) throws IOException {
        InputStream inputStream = getClass().getClassLoader().getResourceAsStream("json/"+fileName);
        BufferedSource source = Okio.buffer(Okio.source(inputStream));
        return new Response.Builder()
                .code(200)
                .message(inputStream.toString())
                .request(chain.request())
                .protocol(Protocol.HTTP_1_1)
                .body(ResponseBody.create(MediaType.parse("application/json"), source.readString(StandardCharsets.UTF_8).getBytes()))
                .addHeader("content-type", "application/json")
                .build();
    }
}