package com.carblre.repository.interfaces;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.carblre.dto.userdto.SignDTO;
import com.carblre.dto.userdto.UserDTO;
import com.carblre.repository.model.User;

@Mapper
public interface UserRepository {
	
	User findById(int id);
	UserDTO findById(String id);

    List<User> findAll();

    void save(User user);

    void update(User user);

    void deleteById(int id);

	User findByUsername(String username);

	void saveUser(SignDTO dto);

	UserDTO findByNickPassword(@Param("nickName") String nickName, @Param("password") String password);
	
	UserDTO findByNickId(String nick);
}
