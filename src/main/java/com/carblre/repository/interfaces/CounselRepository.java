package com.carblre.repository.interfaces;

import com.carblre.repository.model.Counsel;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface CounselRepository {
    Counsel findCounselOfUserById(int userId);

}
