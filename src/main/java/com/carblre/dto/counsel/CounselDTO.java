package com.carblre.dto.counsel;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CounselDTO {

    private int id;
    private int lawyerId;
    private int userId;
    private String date;
    private String startTime;
    private String endTime;
    private String content;
    private int status;

}
