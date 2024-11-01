package com.carblre.util;

import static com.carblre.config.AppConfig.apiKey;
import static com.carblre.config.AppConfig.apiNumber;
import static com.carblre.config.AppConfig.apiSecret;

import java.nio.charset.StandardCharsets;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.codec.binary.Hex;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class SmsCertificationUtil {

	private final RestTemplate restTemplate;

	public void sendSMS(String phoneNumber, String message) {
		String url = "https://api.coolsms.co.kr/messages/v4/send-many/detail"; // CoolSMS 엔드포인트

		// 헤더 생성 (HMAC-SHA256 방식)
		HttpHeaders headers = new HttpHeaders();
		headers.add("Authorization", createSignature());
		headers.add("Content-Type", "application/json");

		// 요청 바디 설정
		Map<String, Object> requestBody = new HashMap<>();
		System.out.println("API NUMBER : " + apiNumber);
		requestBody.put("messages", new Object[] { // 메시지 배열 추가
				new HashMap<String, Object>() {
					{
						put("to", phoneNumber); // 수신자 번호
						put("text", message); // 메시지 내용
						put("from", apiNumber); // 발신자 번호
//                    put("type", "SMS"); // 메시지 타입 (SMS, LMS 등)
					}
				} });

		// HttpEntity에 요청 바디와 헤더 포함
		HttpEntity<Map<String, Object>> entity = new HttpEntity<>(requestBody, headers);

		// API 호출
		ResponseEntity<Void> response = restTemplate.exchange(url, HttpMethod.POST, entity, Void.class);
		if (response.getStatusCode().is2xxSuccessful()) {
			System.out.println("SMS 전송 성공");
		} else {
			System.out.println("SMS 전송 실패: " + response.getStatusCode());
		}
	}

	public static String createSignature() {
		// 생성 시 apiKey와 apiSecret 사용
		try {
			String salt = UUID.randomUUID().toString().replaceAll("-", "");
			String date = ZonedDateTime.now(ZoneId.of("Asia/Seoul")).toString().split("\\[")[0];

			Mac sha256_HMAC = Mac.getInstance("HmacSHA256");
			SecretKeySpec secretKey = new SecretKeySpec(apiSecret.getBytes(StandardCharsets.UTF_8), "HmacSHA256");
			sha256_HMAC.init(secretKey);

			String signature = new String(
					Hex.encodeHex(sha256_HMAC.doFinal((date + salt).getBytes(StandardCharsets.UTF_8))));

			return "HMAC-SHA256 apiKey=" + apiKey + ", date=" + date + ", salt=" + salt + ", signature=" + signature;
		} catch (NoSuchAlgorithmException | InvalidKeyException e) {
			e.printStackTrace();
			return null;
		}
	}
}