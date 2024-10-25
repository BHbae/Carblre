package com.carblre.repository.model;

import com.carblre.dto.MyCounselDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Counsel {
    private int id;
    private int userId;
    private Timestamp reservationTime;
    private String content;
    private int lawyerId;
    private int status;
    private int userStatus;

    public MyCounselDTO toMycounselDTO(){
        // Timestamp를 분까지 String타입으로 변환
        LocalDateTime localDateTime = reservationTime.toLocalDateTime();

        // 초를 제외한 "yyyy-MM-dd HH:mm" 형식으로 포맷
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        String formattedDate = localDateTime.format(formatter);

        return  MyCounselDTO.builder()
                .id(id)
                .userId(userId).reservationTime(formattedDate).content(content)
                .lawyerId(lawyerId).status(status).userStatus(userStatus)
                .build();
    }

}
