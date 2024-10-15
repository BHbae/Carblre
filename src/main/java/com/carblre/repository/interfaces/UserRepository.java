package com.carblre.repository.interfaces;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.carblre.dto.userdto.SignDTO;
import com.carblre.dto.userdto.UserDTO;
import com.carblre.repository.model.User;

@Mapper
public interface UserRepository {
	//id(pk)
	UserDTO findById(String id);

	// 전체 조회
    List<UserDTO> findAll();

    // 일반회원 가입
    void saveUser(User user);
    
    // 회원수정 (쿼리 실행안해봄)
    void update(User user);

    // 회원 탈퇴( 쿼리실행안해봄)
    void deleteById(int id);

    // 간편회원 가입
	void saveApiUser(User user);

	// 로그인
	UserDTO findByNickPassword(@Param("nickName") String nickName, @Param("password") String password);
	
	// 아이디 찾기
	UserDTO findByNickId(String nick);
}
