package com.carblre.repository.model;

import lombok.*;

@Data
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AccidentType {

    private String asltVtrNm; // 가해자법규위반명
    private Integer dthDnvCnt; // 법규 위반별 사망
    private Integer injpsnCnt; // 법규 위반별 부상
    private Integer seDnvCnt; // 법규 위반별 중상

}
