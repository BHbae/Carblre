package com.carblre.service;

import com.carblre.repository.model.request.Message;
import com.carblre.repository.model.response.MessageModel;
import com.carblre.util.APIInit;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import java.io.IOException;

public class SendMessageLMS {
    public static void main(String[] args) {
        // 실제 전송할 정보 입력
        Message message = new Message("01000000000", "029302266", "테스트 메시지입니다.", "테스트 제목");

        // API 호출
        Call<MessageModel> api = APIInit.getAPI().sendMessage(APIInit.getHeaders(), message);
        api.enqueue(new Callback<>() {
            @Override
            public void onResponse(Call<MessageModel> call, Response<MessageModel> response) {
                if (response.isSuccessful()) {
                    System.out.println("statusCode : " + response.code());
                    MessageModel body = response.body();
                    if (body != null) {
                        System.out.println("groupId : " + body.getGroupId());
                        System.out.println("messageId : " + body.getMessageId());
                        System.out.println("to : " + body.getTo());
                        System.out.println("from : " + body.getFrom());
                        System.out.println("type : " + body.getType());
                        System.out.println("statusCode : " + body.getStatusCode());
                        System.out.println("statusMessage : " + body.getStatusMessage());
                        System.out.println("customFields : " + body.getCustomFields());
                    }
                } else {
                    try {
                        System.out.println(response.errorBody().string());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onFailure(Call<MessageModel> call, Throwable throwable) {
                throwable.printStackTrace();
            }
        });
    }
}