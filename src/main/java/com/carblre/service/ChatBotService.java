package com.carblre.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.carblre.dto.BotResponseDTO;
import com.carblre.dto.ChoiceDTO;
import com.carblre.dto.ContentDTO;
import com.carblre.dto.FaultRatioDTO;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

@Service
public class ChatBotService {
	
	@Value("${openai.api.key}")
	private String apiKey;
	
	 // 요청 API 주소
    private final String apiUrl = "https://api.openai.com/v1/chat/completions";
	
	// RestTemplate 인스턴스
	private final RestTemplate restTemplate = new RestTemplate();
	// Gson 인스턴스
	private final Gson gson = new GsonBuilder().create();
	
	public String chatOpenAi(String message, int id) {
		String strId = id + "";
		
		// 가져온 요청 직렬화
		Map<String, String> getRequest = new HashMap<>();
		getRequest.put("id", strId);
		getRequest.put("accident_scenario", message);
		String content = gson.toJson(getRequest);
		
		
		HttpHeaders headers = new HttpHeaders();
		headers.set("Content-Type", "application/json");
		headers.set("Authorization", "Bearer " + apiKey);

		// 내용 추가 메세지
		Map<String, String> messages = new HashMap<>();
	    messages.put("role", "user");
	    messages.put("content", content);
	    
	    // 메세지 리스트 화 
	    List<Map<String, String>> messageList = new ArrayList<>();
	    messageList.add(messages);
	    
	    
	    // 바디구성
	    Map<String, Object> requestBody = new HashMap<>();
	    requestBody.put("model", "ft:gpt-4o-mini-2024-07-18:personal:carblre-gpt:AKxT3jTK");
	    requestBody.put("messages", messageList);
	    requestBody.put("temperature", 1);
        requestBody.put("max_tokens", 256);
        requestBody.put("top_p", 1);
        requestBody.put("frequency_penalty", 0);
        requestBody.put("presence_penalty", 0);
        
        String jsonBody = gson.toJson(requestBody);
        
        // 엔티티 생성
        HttpEntity<String> requestEntity = new HttpEntity<>(jsonBody,headers);
        
        // 통신요성
        ResponseEntity<BotResponseDTO> responseEntity = restTemplate.exchange(apiUrl, HttpMethod.POST, requestEntity, BotResponseDTO.class);
        ChoiceDTO choice = responseEntity.getBody().getChoices().get(0);
        
        System.out.println(choice.getMessage().getContent());
        ContentDTO fromJson = gson.fromJson(choice.getMessage().getContent(), ContentDTO.class);
        FaultRatioDTO ratio = fromJson.getFaultRatio(); 
        
        String respon = "A : "+ ratio.getA() + ", B :"+ratio.getB();

		return respon;
	}
	
}
