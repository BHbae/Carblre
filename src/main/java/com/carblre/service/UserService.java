package com.carblre.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.carblre.dto.SignUpDTO;
import com.carblre.dto.userdto.SocialSignUpDTO;
import com.carblre.dto.userdto.UserDTO;
import com.carblre.repository.interfaces.CounselRepository;
import com.carblre.repository.interfaces.UserRepository;
import com.carblre.repository.model.User;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserService {

	private final UserRepository userRepository;
	@Autowired
	private final PasswordEncoder passwordEncoder;
    @Autowired
    private CounselRepository counselRepository;

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
			signUpDTO.setSite("서버");
			signUpDTO.setRole("user");
			signUpDTO.setStatus(1);
			System.out.println(signUpDTO);
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
		public void saveApiUser (SocialSignUpDTO dto){
			User user = dto.toUser();
			userRepository.saveApiUser(user);
		}

		/**
		 * 아이디로 찾기
		 * @param
		 * @return
		 */
		public UserDTO findByNickId (String nick){
			return userRepository.findByNickId(nick);
		}

		public boolean findPassword(CharSequence changedPass,String originPass){
			return passwordEncoder.matches(changedPass,originPass);
		}

		public void saveUser (SocialSignUpDTO dto){
			User user = dto.toUser();
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

		// 아이디찾기
	public UserDTO findIdByEmail(String email) {
		UserDTO userDTO=userRepository.findByEmail(email);
			return userDTO;
	}

	public UserDTO findIdByEmailNick(String email,String nickName) {
			return userRepository.findByEmailNick(email, nickName);
	}

	@Transactional
	/**
	 * 비밀번호 변경
	 */
	public int updatePassword(String password1,int id) {

			return  userRepository.updatePass(passwordEncoder.encode(password1),id);
	}

	@Transactional
	/**
	 * 회원정보 변경
	 */
	public int updateInfo(String email,int id) {

		return  userRepository.updateInfo(email,id);
	}




}