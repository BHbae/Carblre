package com.carblre.repository.model;

import lombok.*;

import java.sql.Timestamp;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder

public class Qrcode {
    private String id; // 토큰 값
    private int userId; // 사용자 ID
    private String token;
    private Timestamp expirationTime; // 토큰 만료 시간
}
