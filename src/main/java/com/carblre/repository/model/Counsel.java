package com.carblre.repository.model;

import com.carblre.dto.MyCounselDTO;
import com.carblre.dto.userdto.LawyerReservationDTO;
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
    private Timestamp startTime;
    private Timestamp endTime;

    private String content;
    private int lawyerId;
    private int status;

    public MyCounselDTO toMycounselDTO(){
        // Timestamp를 분까지 String타입으로 변환
        LocalDateTime startDateTime = startTime.toLocalDateTime();
        LocalDateTime endDateTime = endTime.toLocalDateTime();


        // 초를 제외한 "yyyy-MM-dd HH:mm" 형식으로 포맷
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        String startFormattedDate = startDateTime.format(formatter);
        String endFormattedDate = endDateTime.format(formatter);

        return  MyCounselDTO.builder()
                .id(id)
                .userId(userId).startTime(startFormattedDate).endTime(endFormattedDate).content(content)
                .lawyerId(lawyerId).status(status)
                .build();
    }

    public LawyerReservationDTO toReservationDTO(){
        // Timestamp를 분까지 String타입으로 변환
        LocalDateTime startDateTime = startTime.toLocalDateTime();
        LocalDateTime endDateTime = endTime.toLocalDateTime();


        // 초를 제외한 "yyyy-MM-dd HH:mm" 형식으로 포맷
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        String startFormattedDate = startDateTime.format(formatter);
        String endFormattedDate = endDateTime.format(formatter);

        return  LawyerReservationDTO.builder()
                .lawyerId(lawyerId).startTime(startFormattedDate).endTime(endFormattedDate).content(content)
                .build();
    }

}
