package com.carblre.service;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.carblre.dto.MyCounselDTO;
import com.carblre.dto.successDTO;
import com.carblre.dto.counsel.CounselDTO;
import com.carblre.dto.userdto.LawyerReservationDTO;
import com.carblre.handler.exception.DataDeliveryException;
import com.carblre.repository.interfaces.CounselRepository;
import com.carblre.repository.model.Counsel;
import com.carblre.utils.Define;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class CounselService {

	private final CounselRepository counselRepository;

	/**
	 * 유저측 예약현황 체크
	 * 
	 * @param id //userId
	 * @return
	 */
	public List<MyCounselDTO> findMyCounselByUserId(int id) {
		List<Counsel> counsel = counselRepository.findCounselOfUserById(id);
		List<MyCounselDTO> dto = new ArrayList<>();
		System.out.println(counsel);
		for (Counsel coun : counsel) {
			dto.add(coun.toMycounselDTO());
		}
		return dto;
	}

	/**
	 * 변호사측 예약현황 체크
	 * 
	 * @param id
	 * @return
	 */
	public List<MyCounselDTO> findMyCounselByLawyerId(int id) {
		List<Counsel> counselList = counselRepository.findCounselOfLawyerById(id);
		List<MyCounselDTO> myCounselDTOS = new ArrayList<>();

		for (Counsel counsel : counselList) {
			// counsel이 null이 아니고 id가 0인 경우 리스트에 추가하지 않음
			if (counsel != null && counsel.getId() != 0) {
				myCounselDTOS.add(counsel.toMycounselDTO());
			}
		}

		return myCounselDTOS.isEmpty() ? null : myCounselDTOS;
	}

	/**
	 * 
	 * 특정 예약 수정
	 * 
	 * @param id
	 * @return
	 */
	public MyCounselDTO findCounselOfIdLawyerById(int lawyerId, int id) {
		Counsel counsel = counselRepository.findCounselByIdLawyerId(lawyerId, id);

		return counsel.toMycounselDTO();
	}

	/**
	 * 
	 * 변호사의 예약 상태변경
	 * 
	 * @param id
	 * @param status
	 * @return
	 */
	public int updateStatusById(int id, int status) {

		return counselRepository.updateStatusById(id, status);
	}

	public MyCounselDTO findMyStatusById(int userId, int id) {
		Counsel counsel = counselRepository.findStatusById(userId, id);
		return MyCounselDTO.builder().status(counsel.getStatus()).build();
	}

	/**
	 * 
	 * 유저 예약 취소
	 * 
	 * @param id
	 * @param status
	 * @return
	 */
	public int updateUserStatusById(int userId, int status, int id) {

		return counselRepository.updateUserStatusById(userId, status, id);

	}

	/**
	 * 전체 예약현황 조회
	 * 
	 * @return
	 */
	public List<LawyerReservationDTO> findCounselAll() {
		List<LawyerReservationDTO> reservationDTOList = new ArrayList<>();
		List<Counsel> counsel = counselRepository.findCounselAll();
		for (Counsel list : counsel) {
			reservationDTOList.add(list.toReservationDTO());
		}
		return reservationDTOList;
	}

	public List<LawyerReservationDTO> findReservation() {

		return counselRepository.findReservation();
	}

	/**
	 * 변호 상담 예약
	 * 
	 * @param id
	 * @param dto
	 * @return
	 */
	public int insertCounselReservation(CounselDTO counselDTO) {
		int result = counselRepository.insertCounsel(counselDTO);

		if (result != 1) {
			return 0;
		}
		return 1;
	}

	/**
	 * 특정 변호사의 예약 정보 불러오기
	 * 
	 * @param id
	 * @return
	 */
	public List<CounselDTO> getCounselReservationByLawyerId(int id) {
		return counselRepository.findReservationByLawyerId(id);
	}

	/**
	 * 특정 변호사 ID + 날짜로 예약 정보 불러오기
	 *
	 */
	public List<CounselDTO> getCounselReservationByLawyerIdAndDate(int id, String date) {
		return counselRepository.findReservationByLawyerIdAndDate(id, date);
	}
	
	// 예약 insert
	@Transactional
	public void insertCounsel(successDTO suDTO, int id) {
	    String date = suDTO.getDate();
	    String startTilmeStr = suDTO.getStartTime() + "";
	    String endTilmeStr = suDTO.getEndTime() + "";
	    
	    
	    
	    // startTime과 endTime을 "HH:mm" 형식으로 변환
        String startTimeFormatted = String.format("%02d:00", Integer.parseInt(startTilmeStr));
        String endTimeFormatted = String.format("%02d:00", Integer.parseInt(endTilmeStr));
        
        String startTime = String.format("%s %s", date, startTimeFormatted);
        String endTime = String.format("%s %s", date, endTimeFormatted);
        
        List<CounselDTO> existingReservations = getCounselReservationByLawyerId(suDTO.getLawyerId());
        
        // 새로운 예약의 시작과 끝 시간을 LocalTime으로 변환
        LocalTime newStartTime = LocalTime.parse(startTimeFormatted); // :00이 이미 추가됨
        LocalTime newEndTime = LocalTime.parse(endTimeFormatted); // :00이 이미 추가됨
        
        for (CounselDTO existingCounsel : existingReservations) {
            LocalTime existingStartTime = LocalTime.parse(existingCounsel.getStartTime().substring(11, 16));
            LocalTime existingEndTime = LocalTime.parse(existingCounsel.getEndTime().substring(11, 16));

            // 겹치는 시간 확인
            if ((newStartTime.isBefore(existingEndTime) && newEndTime.isAfter(existingStartTime)) ||
                    (newStartTime.equals(existingStartTime) || newEndTime.equals(existingEndTime))) {
                throw new DataDeliveryException(Define.EXISTING_COUNSEL, HttpStatus.CONFLICT);
            }
        }
        
        CounselDTO newCounselDTO = CounselDTO.builder()
                .lawyerId(suDTO.getLawyerId())
                .userId(id)
                .startTime(startTime)
                .endTime(endTime)
                .date(date)
                .content(suDTO.getContent())
                .status(0) // 초기 상태 설정
                .build();
        int result = insertCounselReservation(newCounselDTO);
        if (result != 1) {
            throw new DataDeliveryException(Define.UNKNOWN_ERROR, HttpStatus.INTERNAL_SERVER_ERROR);
        }
		
	}
}
