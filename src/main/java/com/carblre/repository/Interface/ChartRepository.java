package com.carblre.repository.Interface;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.carblre.repository.model.DeathToYearCount;

@Mapper
public interface ChartRepository {

    public List<DeathToYearCount> seoulCount(int regionCode);

}