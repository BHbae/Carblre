package com.carblre.service;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.carblre.dto.LawyerChoiceDTO;
import com.carblre.dto.LawyerDetailDTO;
import com.carblre.dto.userdto.LawyerSignUpDTO;
import com.carblre.dto.userdto.UserDTO;
import com.carblre.repository.interfaces.LawyerRepository;
import com.carblre.repository.interfaces.UserRepository;
import com.carblre.repository.model.LawyerDetail;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class LawyerService {

	@Autowired
	private final LawyerRepository lawyerRepository;

	@Autowired
	private final UserRepository userRepository;

	@Autowired
	private final PasswordEncoder passwordEncoder;

	/**
	 * 변호사 가입
	 * 
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
			String uuidProFileName = signUpDTO.UUIDUploardProfileName();
			String uuidLawyerName = signUpDTO.UUIDUploardLawyerName();
			System.out.println("uuid" + uuidProFileName);
			System.out.println("uuid" + uuidLawyerName);
			String uploadProfileName = signUpDTO.getUPLOAD_PROFILE_DIR() + uuidProFileName;// 파일경로와 UUID파일이름
			String uploadLicenseName = signUpDTO.getUPLOAD_LAWYER_DIR() + uuidLawyerName;// 파일경로와 UUID파일이름

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
				profileDirectory.mkdirs(); // 디렉토리가 없으면 생성
			}
			if (!LicenseDirectory.exists()) {
				LicenseDirectory.mkdirs(); // 디렉토리가 없으면 생성
			}

			// 파일 저장 (바이트 배열로 파일을 쓰기)
			Files.write(path, signUpDTO.getProfileImage().getBytes());
			Files.write(LicensePath, signUpDTO.getLicenseImage().getBytes());
			System.out.println(signUpDTO.toString());
			int result = lawyerRepository.insertLawyerDetail(signUpDTO.toLawyerDetail()); // laywer_detail_tb insert
			System.out.println("성공여부:" + result);
		} catch (Exception e) {
			System.out.println("Error in Create User : " + e.getMessage());
		}
	}

	/**
	 * 마이페이지 변호사 정보
	 * 
	 * @param id
	 * @return
	 */
	public com.carblre.dto.userdto.LawyerDetailDTO findLawyerInfoById(int id) {
		LawyerDetail lawyerDetail = lawyerRepository.findLawyerInfoById(id);

		return lawyerDetail.toLawyerDetailDTO();
	}

	/**
	 *
	 * @return
	 */
	public List<UserDTO> findAllLawyer() {
		return lawyerRepository.findAllLawyer();

	}

	/**
	 * 변호사 리스트 조회
	 * 
	 * @return
	 */
	public List<LawyerDetailDTO> LawyerList() {
		List<LawyerDetailDTO> lawyers = new ArrayList<>();
		try {
			lawyers = lawyerRepository.getAllLawyers();
		} catch (Exception e) {

		}
		return lawyers;
	}

	/**
	 * 변호사 상세보기 페이지 조회
	 * 
	 * @param userId
	 * @return
	 */
	public LawyerDetailDTO selectByLawyerId(int userId) {
		LawyerDetailDTO lawyerDetailDTO = new LawyerDetailDTO();

		lawyerDetailDTO = lawyerRepository.getLawyerById(userId);
		return lawyerDetailDTO;

	}
	
    /**
     * 상담가 수정
     * @param id
     * @param amount
     */
    public int updateAmount(int id, int amount) {
        return lawyerRepository.updateAmount(id,amount);
    }
    
    public LawyerChoiceDTO lawyerChoice(int id) {
    	return lawyerRepository.lawyerChoice(id);
    }
    
    
}
