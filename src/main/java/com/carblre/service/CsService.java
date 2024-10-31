package com.carblre.service;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.carblre.dto.CsAllDTO;
import com.carblre.dto.CsFindByIdDTO;
import com.carblre.handler.exception.DataDeliveryException;
import com.carblre.repository.interfaces.CsRepository;
import com.carblre.utils.Define;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class CsService {

	private final CsRepository csRepository;

	public void saveCs(int id, String title, String content) {

		int row = csRepository.insetCs(id, title, content);

		if (row != 1) {
			throw new DataDeliveryException(Define.CREATED_CS_ERROR, HttpStatus.BAD_REQUEST);
		}

	} // end saveCs

	public List<CsAllDTO> findAllCs(int page, int size) {
		int offset = (page - 1) * size;
		return csRepository.findAllCs(offset, size);
	} // end findAllCs

	public int countAllCs() {
		return csRepository.countAllCs();
	}

	// 제목으로 검색
	@Transactional
	public List<CsAllDTO> searchByTitle(String title, int page, int size) {
		int offset = (page - 1) * size;
		return csRepository.findByTitle(title, offset, size);
	}

	@Transactional
	public int countCsByTitle(String title) {
		return csRepository.countByTitle(title);
	}

	// 내용으로 검색
	@Transactional
	public List<CsAllDTO> searchByContent(String content, int page, int size) {
		int offset = (page - 1) * size;
		return csRepository.findByContent(content, offset, size);
	}

	@Transactional
	public int countCsByContent(String content) {
		return csRepository.countByContent(content);
	}

	// 제목과 내용 모두 검색
	@Transactional
	public List<CsAllDTO> searchByAll(String query, int page, int size) {
		int offset = (page - 1) * size;
		return csRepository.findByAll(query, offset, size);
	}

	@Transactional
	public int countCsByAll(String query) {
		return csRepository.countByAll(query);
	}

	public CsFindByIdDTO findById(int id) {
		return csRepository.findById(id);
	}

	public int updateByIdAndUserId(int id, String title, String content) {
		return csRepository.updateByIdAndUserId(id, title, content);
	}

	// 답변하기 처리
	public int createResponse(int id, String response) {
		return csRepository.createResponse(id, response);
	}

	// 삭제하기 처리
	public int deleteCsById(int id) {
		return csRepository.deleteCsById(id);
	}

}
