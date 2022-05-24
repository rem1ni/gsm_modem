package com.example.demo.service;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.json.simple.JSONObject;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
@Service
public class Json {



    public static void SendTo(String number,String text) throws IOException {
        // для простоты примера просто хардкодим нужные данные в методе
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("number", number);
        jsonObject.put("text", text);
        String json = String.valueOf(jsonObject);
        HttpPost post = new HttpPost("https://1b9091c4-5edf-45e5-991b-bc2980ed0df0.mock.pstmn.io/test");
        StringEntity entity = new StringEntity(json);
        post.setEntity(entity);
        System.out.println(json);
        try (CloseableHttpClient httpClient = HttpClients.createDefault();
             CloseableHttpResponse response = httpClient.execute(post)) {
            System.out.println("Answer from modem: " + EntityUtils.toString(response.getEntity()));
        }
    }

}

