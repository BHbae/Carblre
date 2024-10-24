package com.carblre.repository.interfaces;

import com.carblre.repository.model.Counsel;
import com.carblre.repository.model.LawyerDetail;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CounselRepository {
    Counsel findCounselOfUserById(int userId);


}
