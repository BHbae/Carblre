package com.carblre.repository.model;

import lombok.*;

@Data
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Chart {


    private Integer accYear; // 사고년도
    private Integer dthDnvCnt; // 사망자수
    private Integer injpsnCnt; // 부상자수
    private Integer seDnvCnt; // 중상자수
    private Integer slDnvCnt; // 경상자수
    private Integer occrrncLcSidoCd; // 위치 시도 코드
    private Integer occrrncLcSggCd; // 위치 시군구 코드
    private String accTyLclasCd; // 사고유형대분류
    private String accTyMlsfcCd; // 사고유형중분류
    private String accTyCd; // 사고유형
    private String asltVtrCd; // 가해자법규위반
    private String roadFrmLclasCd; // 도로형태대분류
    private String roadFrmCd; // 도로형태
}

