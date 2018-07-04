package com.zenvia.zcc.sample;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ZenviaZccSampleDispatchMain implements CommandLineRunner {

    Logger logger = LoggerFactory.getLogger(ZenviaZccSampleDispatchMain.class);

    public static void main(String[] args) {
        SpringApplication.run(ZenviaZccSampleDispatchMain.class, args);
    }

    @Override
    public void run(String... args) throws Exception {

        String token = "<ZENVIA_API_TOKEN>";
        
        OkHttpClient client = new OkHttpClient();

        MediaType mediaType = MediaType.parse("application/json");

        RequestBody body = RequestBody.create(mediaType, "{"
            + "\"workflowId\": \"YOU-BOT-ID\","
            + "\"fileHeader\": \"name;sms\","
            + "\"contacts\": [\"John Doe;5551999999999\"]"
            + "}");

        Request request = new Request.Builder()
            .url("https://api.zenvia.com/messages/v1/bot/bulk")
            .post(body)
            .addHeader("content-type", "application/json")
            .addHeader("authorization", token)
            .build();

        Response response = client.newCall(request).execute();

        logger.info(response.code() 
            + " - " + response.message()
            + " - " + response.body().string());

    }
}
