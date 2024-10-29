package com.carblre.dto;

import com.carblre.repository.model.Counsel;
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
public class MyCounselDTO {
    private int id;
    private int userId;
    private String startTime;
    private String endTime;
    private String content;
    private int lawyerId;
    private int status;
    private int userStatus;


    public Counsel toCounselDTO(){
        // Timestamp를 분까지 String타입으로 변환
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

        // String을 LocalDateTime으로 변환
        LocalDateTime startDateTime = LocalDateTime.parse(startTime, formatter);
        LocalDateTime endDateTime = LocalDateTime.parse(endTime, formatter);

        // LocalDateTime을 Timestamp로 변환
        Timestamp startTimestamp = Timestamp.valueOf(startDateTime);
        Timestamp endTimestamp = Timestamp.valueOf(endDateTime);

        return  Counsel.builder()
                .id(id)
                .userId(userId).startTime(startTimestamp).endTime(endTimestamp).content(content)
                .lawyerId(lawyerId).status(status).userStatus(userStatus)
                .build();
    }

}
