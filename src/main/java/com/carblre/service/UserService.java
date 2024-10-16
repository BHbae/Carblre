package com.carblre.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.carblre.dto.LawyerUserDTO;
import com.carblre.repository.interfaces.UserRepository;
import com.carblre.repository.model.User;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserService {

	private final UserRepository userRepository;

	@Transactional
	public List<User> readAllGeneralUser() {
		return userRepository.readAllGeneralUser();
	}

	public List<LawyerUserDTO> readAllCorporateUser() {
		return userRepository.readAllCorporateUser();
	}

}
