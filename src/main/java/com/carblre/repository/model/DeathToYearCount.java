package com.carblre.repository.model;

import lombok.*;

@Data
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class DeathToYearCount {

	private Integer year; // acc_year 에서 가져온 년도
	private String occrrncLcSidoCd; // occrrnc_lc_sido_cd 에서 가져온 시도
	private String asltVtrCd; // aslt_vtr_cd 에서 가져온 가해자법규위반 코드
	private Integer deathCount; // SUM(dth_dnv_cnt) 에서 가져온 사망자수
	private Integer injuredCount; // SUM(injpsn_cnt) 에서 가져온 부상자수
	private Integer seriousInjuriesCount;// SUM(se_dnv_cnt) 중상자수
	private Integer minorInjuriesCount; // SUM(sl_dnv_cnt) 경상자수
}
