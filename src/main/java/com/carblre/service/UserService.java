package com.carblre.service;

import org.springframework.stereotype.Service;

import com.carblre.dto.userdto.SignDTO;
import com.carblre.dto.userdto.UserDTO;
import com.carblre.repository.interfaces.UserRepository;
import com.carblre.repository.model.User;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserService {

	private final UserRepository userRepository;
	
	/**
	 * id(pk)값으로 찾기-사용자확인
	 * @param id
	 * @return
	 */
	 public UserDTO findById(int id) {
		 return userRepository.findById(id);
	 }

	 /**
	  *  로그인 -닉네임,패스워드
	  * @param nickname
	  * @param password
	  * @return
	  */
	 public UserDTO login(String nickname,String password) {
		 
		 return userRepository.findByNickPassword(nickname,password);	
	 }
	 
	 /**
	  * 간편로그인 사용자 최초인증
	  * @param dto
	  */
    public void saveApiUser(SignDTO dto) {
    	User user=dto.toUsern();
    	userRepository.saveApiUser(user);
    }
    
    /**
     * 아이디 찾기
     * @param
     * @return ㅇㅇ
     */
	public UserDTO findByNickId(String nick) {
		
		 return userRepository.findByNickId(nick);
	}
	 

	    public void saveUser(SignDTO dto) {
	    	User user= dto.toUsern();
	    	userRepository.saveApiUser(user);
	    }
}
