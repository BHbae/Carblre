package com.carblre.repository.interfaces;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.carblre.dto.LawyerUserDTO;
import com.carblre.repository.model.User;

@Mapper
public interface UserRepository {

	public List<User> readAllGeneralUser();

	public List<LawyerUserDTO> readAllCorporateUser();

}
