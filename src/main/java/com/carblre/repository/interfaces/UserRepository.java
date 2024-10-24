package com.carblre.repository.interfaces;

import java.util.List;

import com.carblre.dto.SignUpDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.carblre.dto.userdto.SignDTO;
import com.carblre.dto.userdto.UserDTO;
import com.carblre.repository.model.User;

@Mapper
public interface UserRepository {
	//id(pk)
	UserDTO findById(int id);

	// 전체 조회
    List<UserDTO> findAll();

    // 일반회원 가입
    void saveUser(User user);
    
    // 회원수정 (쿼리 실행안해봄) //삭제예정
    void update(User user);

    // 회원 탈퇴( 쿼리실행안해봄)
    void deleteById(int id);

    // 간편회원 가입
	void saveApiUser(User user);

	// 로그인
	UserDTO findByNickPassword(@Param("nickName") String nickName, @Param("password") String password);
	
	// 아이디 로 정보찾기
	UserDTO findByNickId(String nick);

	// 회원가입
	public void insert(User user);

	// E-mail 중복 체크
	public int checkDuplicateEmail(String email);

	// 이메일로 아이디찾기
	UserDTO findByEmail(String email);

	// 닉네임(아이디) 이메일 비밀번호 찾기
	UserDTO findByEmailNick(@Param("email")String email,@Param("nickName") String nickName);

	// 비밀번호 변경
	int updatePass(@Param("password")String password,@Param("id")int id);

	// 회원정보 변경
	int updateInfo(String email, int id);


	// 가장 최근 auto_increment된 id
	@Select("SELECT LAST_INSERT_ID()")
	int getLastInsertId();

	// 변호사 디테일
	int insertLawyerDetail(LawyerDetail lawyerDetail);
}
