package com.carblre.controller;

import com.carblre.dto.MyCounselDTO;
import com.carblre.dto.Response;
import com.carblre.dto.counsel.CounselDTO;
import com.carblre.dto.userdto.LawyerDetailDTO;
import com.carblre.dto.userdto.LawyerReservationDTO;
import com.carblre.dto.userdto.UserDTO;
import com.carblre.handler.exception.DataDeliveryException;
import com.carblre.repository.model.Counsel;
import com.carblre.repository.model.LawyerDetail;
import com.carblre.repository.model.User;
import com.carblre.service.CounselService;
import com.carblre.service.LawyerService;
import com.carblre.service.UserService;
import com.carblre.utils.Define;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.*;

@Controller
@RequiredArgsConstructor
@RequestMapping("/counsel")
public class CounselController {

    @Autowired
    private CounselService counselService;

    @Autowired
    private UserService userService;

    @Autowired
    private LawyerService lawyerService;

    @Autowired
    private HttpSession session;

    @PostMapping("/updateStatus")
    public ResponseEntity<Map<String,Object>> updateStatus(@RequestBody Map<String, Object> reqData,
                                                           HttpSession session){
        UserDTO userDTO = (UserDTO) session.getAttribute("principal");
        int counselId = (Integer) reqData.get("counselId");
        int statusValue = Integer.parseInt((String) reqData.get("statusValue"));

        System.out.println("counselId"+counselId +"statusValue"+statusValue);
        // 값 받아오기
        Map<String, Object> response = new HashMap<>();
        int result= counselService.updateStatusById(counselId,statusValue);
        MyCounselDTO dto=counselService.findCounselOfIdLawyerById(userDTO.getId(),counselId);
        System.out.println("result"+result);
        if (result == 1) {
            response.put("success", true);  // 성공
            response.put("message", "변경 완료");
            response.put("status", dto.getStatus());

        }else{
            response.put("success", false);  //
            response.put("message", "변경 실패");

        }
        return ResponseEntity.ok(response);
    }

    /**
     * 유저의 예약 취소
     *
     * @param reqData
     * @return
     */
    @PostMapping("/cancelStatus")
    public ResponseEntity<Map<String, Object>> cancelStatusProc(@RequestBody Map<String, String> reqData,HttpSession session ) {
        UserDTO userDTO = (UserDTO) session.getAttribute("principal");

        int status = Integer.parseInt(reqData.get("status"));
        int id = Integer.parseInt(reqData.get("id"));
        System.out.println("status"+status+"id"+id);
        Map<String, Object> response = new HashMap<>();
        int result = counselService.updateUserStatusById(userDTO.getId(),status,id);// 패스워드 확인
        MyCounselDTO counselDTO=counselService.findMyStatusById(userDTO.getId(),id);

        System.out.println("result" + result);
        if (result == 1) {
            response.put("success", true);
            response.put("newStatus", counselDTO.getStatus());
        } else {
            response.put("success", false);
        }
        return ResponseEntity.ok(response);
    }



    @GetMapping("/reservation")
    public String reservation(Model model) {
        List<LawyerReservationDTO> lawyerList = counselService.findReservation();
        System.out.println(lawyerList);
        ;
        model.addAttribute("dtoList", lawyerList);
        return "counsel/counselReservation";
    }


    @PostMapping("/reservation")
    public String reservationProc(CounselDTO counselDTO) {
        UserDTO principal = (UserDTO) session.getAttribute("principal");

        if (principal == null) {
            throw new DataDeliveryException(Define.ENTER_YOUR_LOGIN, HttpStatus.UNAUTHORIZED);
        }

        // 날짜와 시간을 조합하여 timestamp 형식으로 설정
        String date = counselDTO.getDate();

        // startTime과 endTime을 "HH:mm" 형식으로 변환
        String startTimeFormatted = String.format("%02d:00", Integer.parseInt(counselDTO.getStartTime()));
        String endTimeFormatted = String.format("%02d:00", Integer.parseInt(counselDTO.getEndTime()));

        String startTime = String.format("%s %s", date, startTimeFormatted);
        String endTime = String.format("%s %s", date, endTimeFormatted);

        // 기존 예약 목록을 조회하여 중복 체크
        List<CounselDTO> existingReservations = counselService.getCounselReservationByLawyerId(counselDTO.getLawyerId());

        // 새로운 예약의 시작과 끝 시간을 LocalTime으로 변환
        LocalTime newStartTime = LocalTime.parse(startTimeFormatted); // :00이 이미 추가됨
        LocalTime newEndTime = LocalTime.parse(endTimeFormatted); // :00이 이미 추가됨

        // 중복 체크
        for (CounselDTO existingCounsel : existingReservations) {
            LocalTime existingStartTime = LocalTime.parse(existingCounsel.getStartTime().substring(11, 16));
            LocalTime existingEndTime = LocalTime.parse(existingCounsel.getEndTime().substring(11, 16));

            // 겹치는 시간 확인
            if ((newStartTime.isBefore(existingEndTime) && newEndTime.isAfter(existingStartTime)) ||
                    (newStartTime.equals(existingStartTime) || newEndTime.equals(existingEndTime))) {
                throw new DataDeliveryException(Define.EXISTING_COUNSEL, HttpStatus.CONFLICT);
            }
        }

        // Builder 패턴을 사용하여 CounselDTO 생성
        CounselDTO newCounselDTO = CounselDTO.builder()
                .lawyerId(counselDTO.getLawyerId())
                .userId(principal.getId())
                .startTime(startTime)
                .endTime(endTime)
                .date(date)
                .content(counselDTO.getContent())
                .status(0) // 초기 상태 설정
                .build();

        // 상담 예약 삽입
        int result = counselService.insertCounselReservation(newCounselDTO);

        if (result != 1) {
            throw new DataDeliveryException(Define.UNKNOWN_ERROR, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return "redirect:/lawyer/lawyerInfo/" + counselDTO.getLawyerId();
    }
    @GetMapping("/api/available-times")
    @ResponseBody
    public ResponseEntity<Map<String, Object>> getAvailableTimes(@RequestParam String date, @RequestParam int lawyerId) {
        List<Integer> availableTimes = new ArrayList<>();

        // 9시부터 17시까지의 시간 리스트
        for (int i = 9; i <= 17; i++) {
            boolean isAvailable = true;

            // 기존 예약 목록 조회
            List<CounselDTO> existingReservations = counselService.getCounselReservationByLawyerIdAndDate(lawyerId, date);

            for (CounselDTO existing : existingReservations) {
                // 기존 예약의 시작과 끝 시간 파싱
                LocalTime existingStartTime = LocalTime.parse(existing.getStartTime().substring(11, 16));
                LocalTime existingEndTime = LocalTime.parse(existing.getEndTime().substring(11, 16));
                LocalTime newStartTime = LocalTime.of(i, 0); // 새 예약 시작 시간
                LocalTime newEndTime = LocalTime.of(i + 1, 0); // 새 예약 끝 시간

                // 겹치는 시간 확인
                if ((newStartTime.isBefore(existingEndTime) && newEndTime.isAfter(existingStartTime)) ||
                        (newStartTime.equals(existingStartTime) || newEndTime.equals(existingEndTime))) {
                    isAvailable = false;
                    break;
                }
            }

            if (isAvailable) {
                availableTimes.add(i); // 사용 가능한 시간 추가
            }
        }

        Map<String, Object> response = new HashMap<>();
        response.put("availableTimes", availableTimes);
        return ResponseEntity.ok(response);
    }
}
