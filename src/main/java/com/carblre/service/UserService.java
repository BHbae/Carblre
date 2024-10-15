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
	
	 public User findById(int id) {
	        return userRepository.findById(id);
	    }
	 public UserDTO findById(String id) {
		 return userRepository.findById(id);
	 }

	 public UserDTO login(String nickname,String password) {
		 
		 return userRepository.findByNickPassword(nickname,password);	
	 }
	 
	 
	    public User findByUsername(String username) {
	        return userRepository.findByUsername(username);
	    }

	    public void saveUser(SignDTO dto) {
	    	userRepository.saveUser(dto);
	    }
		public UserDTO findByNickId(String kakaoIdStr) {
			
			 return userRepository.findByNickId(kakaoIdStr);
		}
}
