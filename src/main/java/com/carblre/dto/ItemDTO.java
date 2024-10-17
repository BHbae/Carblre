package com.carblre.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.*;

/**
 * 법규위반별 사고다발지역 API
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class ItemDTO {

    private Integer accYear; // 사고년도
    private Double occrrncDt; // 월일시
    private Integer dghtCd; // 주야구분코드
    private Integer occrrncDayCd; // 요일코드
    private Integer dthDnvCnt; // 사망자수
    private Integer injpsnCnt; // 부상자수
    private Integer seDnvCnt; // 중상자수
    private Integer slDnvCnt; // 경상자수
    private Integer wndDnvCnt; // 부상신고자수
    private Integer occrrncLcSidoCd; // 위치 시도 코드
    private Integer occrrncLcSggCd; // 위치 시군구 코드
    private String accTyLclasCd; // 사고유형 대분류 코드
    private String accTyMlsfcCd; // 사고유형 중분류 코드
    private String accTyCd; // 사고유형 코드
    private String asltVtrCd; // 가해자 법규위반 코드
    private String roadFrmLclasCd; // 도로형태 대분류 코드
    private String roadFrmCd; // 도로형태 코드
    private String wrngdoIsrtyVhctyLclasCd;
    private String dmgeIsrtyVhctyLclasCd; // 피해당사자 차종별 대분류 코드

    @JsonProperty("occrrnc_lc_x_crd")
    private Integer occrrncLcXCrd; // 위치 X 좌표

    @JsonProperty("occrrnc_lc_y_crd")
    private Integer occrrncLcYCrd; // 위치 Y 좌표
    private Double loCrd; // 경도 좌표
    private Double laCrd; // 위도 좌표

}
