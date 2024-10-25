package com.carblre.repository.interfaces;

import com.carblre.repository.model.LawyerDetail;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
@Mapper
public interface LawyerRepository {

    public List<LawyerDetail> getAllLawyers();
}
