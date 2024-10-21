package com.carblre.util;

import com.carblre.repository.model.request.Message;
import com.carblre.repository.model.response.MessageModel;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.HeaderMap;
import retrofit2.http.POST;

import java.util.Map;

public interface CoolSMSAPI {

    @POST("send")
    Call<MessageModel> sendMessage(@HeaderMap Map<String, String> headers, @Body Message message);
}