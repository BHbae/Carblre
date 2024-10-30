package com.carblre.repository.Interface;

import com.carblre.repository.model.AccidentDamageCount;
import com.carblre.repository.model.DeathToYearCount;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ChartRepository {

    public List<DeathToYearCount> seoulCount(int regionCode);

}
