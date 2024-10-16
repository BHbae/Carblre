package com.carblre.service;

import com.carblre.dto.SignUpDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.carblre.dto.userdto.SignDTO;
import com.carblre.dto.userdto.UserDTO;
import com.carblre.repository.interfaces.UserRepository;
import com.carblre.repository.model.User;

import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserService {

	private final UserRepository userRepository;
	@Autowired
	private final PasswordEncoder passwordEncoder;

	// *** User Sign Up ***
	@Transactional
	public void createUser(SignUpDTO signUpDTO) {
		try {
			System.out.println("Here in Create User Method");
			System.out.println("User Name : " + signUpDTO.getUserName());
			System.out.println("User Nickname : " + signUpDTO.getNickName());
			System.out.println("User Password : " + signUpDTO.getPassword());
			System.out.println("User Email : " + signUpDTO.getEmail());
			System.out.println("User phoneNumber : " + signUpDTO.getPhoneNum());
			String hashPassword = passwordEncoder.encode(signUpDTO.getPassword());
			signUpDTO.setPassword(hashPassword);
			userRepository.insert(signUpDTO.toUser());
		} catch (Exception e) {
			System.out.println("Error in Create User : " + e.getMessage());
		}


	}

	/**
	 * id(pk)값으로 찾기-사용자확인
	 *
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
		public UserDTO login (String nickname, String password){

			return userRepository.findByNickPassword(nickname, password);
		}

		/**
		 * 간편로그인 사용자 최초인증
		 * @param dto
		 */
		public void saveApiUser (SignDTO dto){
			User user = dto.toUsern();
			userRepository.saveApiUser(user);
		}

		/**
		 * 아이디 찾기
		 * @param
		 * @return ㅇㅇ
		 */
		public UserDTO findByNickId (String nick){
			return userRepository.findByNickId(nick);
		}


		public void saveUser (SignDTO dto){
			User user = dto.toUsern();
			userRepository.saveApiUser(user);
		}

		// User E-mail 중복 체크를 합니다. 중복이라면 -> 1 || 중복이 아니라면 -> 0
		@Transactional
		public int checkDuplicateEmail (String email){
			System.out.println("Here is UserService !!! checkDuplicateEmail");
			int result = 0;
			result = userRepository.checkDuplicateEmail(email);
			if (result != 0) {
				return 1;
			}
			return 0;
		}
	}


