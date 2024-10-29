package com.carblre.service;

import com.carblre.dto.SignUpDTO;
import com.carblre.dto.userdto.*;
import com.carblre.repository.model.LawyerDetail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.carblre.repository.interfaces.UserRepository;
import com.carblre.repository.model.User;

import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

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

	/**
	 *  변호사 가입
	 * @param signUpDTO
	 */
	@Transactional
	public void createLawyerUser(LawyerSignUpDTO signUpDTO) {
		try {
			System.out.println("Here in Create User Method");
			System.out.println("User Name : " + signUpDTO.getUserName());
			System.out.println("User Nickname : " + signUpDTO.getNickName());
			System.out.println("User Password : " + signUpDTO.getPassword());
			System.out.println("User Email : " + signUpDTO.getEmail());
			System.out.println("User phoneNumber : " + signUpDTO.getPhoneNum());
			String hashPassword = passwordEncoder.encode(signUpDTO.getPassword());
			signUpDTO.setPassword(hashPassword);
			signUpDTO.setRole("lawyer"); // 임시 변호사 role
			signUpDTO.setSite("서버");

			// 이름지정
			signUpDTO.setGetProfileName(signUpDTO.getProfileImage().getOriginalFilename()); // 파일객체에서 파일이름
			signUpDTO.setGetLicenseName(signUpDTO.getLicenseImage().getOriginalFilename());
			String uuidProFileName=signUpDTO.UUIDUploardProfileName();
			String uuidLawyerName=signUpDTO.UUIDUploardLawyerName();
			System.out.println("uuid"+uuidProFileName);
			System.out.println("uuid"+uuidLawyerName);
			String uploadProfileName=signUpDTO.getUPLOAD_PROFILE_DIR() +uuidProFileName  ;//파일경로와 UUID파일이름
			String uploadLicenseName=signUpDTO.getUPLOAD_LAWYER_DIR() +uuidLawyerName  ;//파일경로와 UUID파일이름

			Path path = Paths.get(uploadProfileName); // 경로설정
			Path LicensePath = Paths.get(uploadLicenseName); // 자격증경로설정
			signUpDTO.setUploadProfileName(uuidProFileName);
			signUpDTO.setUploadLicenseName(uuidLawyerName);

			// site,status 삽입
			signUpDTO.setSite("서버");
			signUpDTO.setStatus(0);
			// 여기까지 user_tb insert
			userRepository.insert(signUpDTO.toUser()); // USER_TB INSERT TODO 현재 User 모델에 Site가 없어서 추가해야됨
			// 가장최근 AUTO id값 바로받아 값 이식
			signUpDTO.setUserId(userRepository.getLastInsertId());

			// 파일 저장 경로 설정 (상대 경로, 로컬 디렉토리)
			// 디렉토리가 존재하지 않을 경우 생성
			File profileDirectory = new File(signUpDTO.getUPLOAD_PROFILE_DIR());
			File LicenseDirectory = new File(signUpDTO.getUPLOAD_LAWYER_DIR());
			if (!profileDirectory.exists()) {
				profileDirectory.mkdirs();  // 디렉토리가 없으면 생성
			}
			if (!LicenseDirectory.exists()) {
				LicenseDirectory.mkdirs();  // 디렉토리가 없으면 생성
			}

			// 파일 저장 (바이트 배열로 파일을 쓰기)
			Files.write(path,signUpDTO.getProfileImage().getBytes());
			Files.write(LicensePath,signUpDTO.getLicenseImage().getBytes());
			System.out.println(signUpDTO.toString());
			int result =userRepository.insertLawyerDetail(signUpDTO.toLawyerDetail()); // laywer_detail_tb insert
			System.out.println("성공여부:"+result);
		} catch (Exception e) {
			System.out.println("Error in Create User : " + e.getMessage());
		}
	}

	/**
	 * 마이페이지 변호사 정보
	 * @param id
	 * @return
	 */
	public LawyerDetailDTO findLawyerInfoById(int id) {
	LawyerDetail lawyerDetail=	userRepository.findLawyerInfoById(id);

		return lawyerDetail.toLawyerDetailDTO();
	}

	/**
	 *
	 * @return
	 */
	public List<UserDTO> findAllLawyer() {
		return userRepository.findAllLawyer();

	}

	public List<LawyerReservationDTO> findReservation(int id) {

		return userRepository.findReservation(id);
	}

	public List<LawyerReservationDTO> findReservationLawyer() {

		return userRepository.findReservationLawyer();
	}

}


