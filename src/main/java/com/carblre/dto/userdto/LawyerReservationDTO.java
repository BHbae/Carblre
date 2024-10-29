package com.carblre.dto.userdto;

import com.carblre.repository.model.Counsel;
import com.carblre.repository.model.LawyerDetail;
import lombok.*;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class LawyerReservationDTO {

    private int lawyerId;
    private String lawyerName;
    private int counselingAmount;
    private String lawFirm ;
    private String startTime;
    private String endTime;
    private String content;


    //깃푸쉬
    public Counsel toCounsel() {
        // Timestamp를 분까지 String타입으로 변환
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

        // String을 LocalDateTime으로 변환
        LocalDateTime startDateTime = LocalDateTime.parse(startTime, formatter);
        LocalDateTime endDateTime = LocalDateTime.parse(endTime, formatter);

        // LocalDateTime을 Timestamp로 변환
        Timestamp startTimestamp = Timestamp.valueOf(startDateTime);
        Timestamp endTimestamp = Timestamp.valueOf(endDateTime);

        return  Counsel.builder()
                .lawyerId(lawyerId).startTime(startTimestamp).endTime(endTimestamp).content(content)
                .build();
    }

}
