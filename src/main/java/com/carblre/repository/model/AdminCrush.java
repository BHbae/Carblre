package com.carblre.repository.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class AdminCrush {
	private int accYear;
	private int dthDnvCnt;
	private int injpsnCnt;
	private int seDnvCnt;
	private int slDnvCnt;
	private int occrrncLcSidoCd;
	private int occrrncLcSggCd;
	private int accTyLclasCd;
	private int accTyMlsfcCd;
	private int accTyCd;
	private int asltVtrCd;
	private int roadFrmLclasCd;
	private int roadFrmCd;

}
