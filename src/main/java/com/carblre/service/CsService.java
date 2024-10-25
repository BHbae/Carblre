package com.carblre.service;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

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


	public List<CsAllDTO> findAllCs(int limit,int offset) {
		return csRepository.findAllCs(limit, offset);
	} // end findAllCs

	public int countAllCs() {
		return csRepository.countAllCs();
	}


	public CsFindByIdDTO findById(int id) {
		 return csRepository.findById(id);
	}
	
	
}
