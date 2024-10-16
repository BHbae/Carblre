package com.carblre.repository.interfaces;

import org.springframework.http.ResponseEntity;

import java.util.Map;

public interface EmailRepository {

    // 이메일 코드 전송
    public ResponseEntity<Map<String, String>> sendCode();

    // 이메일 코드 인증(타임 계산)
    public String validateUserCode();


}
