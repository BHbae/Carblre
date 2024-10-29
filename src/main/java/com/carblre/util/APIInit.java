package com.carblre.util;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import java.util.HashMap;
import java.util.Map;

public class APIInit {
    private static final String BASE_URL = "https://api.coolsms.co.kr/messages/v4/";

    public static CoolSMSAPI getAPI() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        return retrofit.create(CoolSMSAPI.class);
    }

    public static Map<String, String> getHeaders() {
        Map<String, String> headers = new HashMap<>();
        headers.put("Authorization", SmsCertificationUtil.createSignature()); // HMAC-SHA256 서명
        headers.put("Content-Type", "application/json");
        return headers;
    }
}