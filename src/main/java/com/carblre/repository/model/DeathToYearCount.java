package com.carblre.repository.model;

import lombok.*;

@Data
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class DeathToYearCount {

    private Integer year; // acc_year 에서 가져온 년도
    private Integer deathCount; // SUM(dth_dnv_cnt) 에서 가져온 사망자수
    private Integer injuredCount; // SUM(injpsn_cnt) 에서 가져온 부상자수
    private Integer seriousInjuriesCount;// SUM(se_dnv_cnt) 중상자수
    private Integer minorInjuriesCount; // SUM(sl_dnv_cnt) 경상자수

}
