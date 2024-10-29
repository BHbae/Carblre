package com.carblre.controller;

import com.carblre.dto.MyCounselDTO;
import com.carblre.dto.Response;
import com.carblre.dto.userdto.LawyerDetailDTO;
import com.carblre.dto.userdto.LawyerReservationDTO;
import com.carblre.dto.userdto.UserDTO;
import com.carblre.repository.model.Counsel;
import com.carblre.repository.model.LawyerDetail;
import com.carblre.repository.model.User;
import com.carblre.service.CounselService;
import com.carblre.service.UserService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequiredArgsConstructor
@RequestMapping("/counsel")
public class CounselController {

    private final CounselService counselService;
    private final UserService userService;


    @PostMapping("/updateStatus")
    public ResponseEntity<Map<String,Object>> updateStatus(@RequestBody Map<String, String> reqData,
                                                           HttpSession session){
        UserDTO userDTO = (UserDTO) session.getAttribute("principal");
        int counselId = Integer.parseInt(reqData.get("counselId"));
        int statusValue = Integer.parseInt(reqData.get("statusValue"));
        System.out.println("counselId"+counselId +"statusValue"+statusValue);
        // 값 받아오기
        Map<String, Object> response = new HashMap<>();
        int result= counselService.updateStatusById(counselId,statusValue);
        MyCounselDTO dto=counselService.findMyCounselByLawyerId(userDTO.getId());
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
     *  유저의 예약 취소
     * @param reqData
     * @return
     */
    @PostMapping("/cancelStatus")
    public ResponseEntity<Map<String, Object>> cancelStatusProc(@RequestBody Map<String, String> reqData,
                                        HttpSession session ) {
        UserDTO userDTO = (UserDTO) session.getAttribute("principal");

        int status = Integer.parseInt(reqData.get("status"));
        System.out.println("status"+status);
        Map<String, Object> response = new HashMap<>();
        int result = counselService.updateUserStatusById(userDTO.getId(),status);// 패스워드 확인
        MyCounselDTO counselDTO=counselService.findMyStatusById(userDTO.getId());
        System.out.println("result" + result);
        if (result==1) {
            response.put("success", true);
            response.put("newStatus", counselDTO.getStatus());
        } else {
            response.put("success", false);
        }
        return ResponseEntity.ok(response);
    }


    @GetMapping("/reservation")
    public String reservation(Model model){
        List<LawyerReservationDTO> lawyerList= userService.findReservation();
        System.out.println(lawyerList);;
        model.addAttribute("dtoList",lawyerList);
        return "counsel/counselReservation";
    }


    @PostMapping("/reservation")
    public ResponseEntity<Map<String,Object>> reservationProc(HttpSession session,
                                                              @RequestBody Map<String, String> reqData){
        UserDTO userDTO = (UserDTO) session.getAttribute("principal");
        int lawyerId = Integer.parseInt(reqData.get("lawyerId"));
        String startTime =reqData.get("startTime");
        String endTime =reqData.get("endTime");
        String content =reqData.get("content");
        startTime = startTime.replace("T", " ");
        endTime = endTime.replace("T", " ");

         LawyerReservationDTO dto=LawyerReservationDTO.builder()
                .lawyerId(lawyerId).startTime(startTime)
                .endTime(endTime).content(content)
                .build();
        System.out.println("dto:"+dto);
        int result =counselService.insertCounselReservation(userDTO.getId(),dto);
        Map<String, Object> response = new HashMap<>();
        if (result==1) {
            response.put("success", true);
        } else {
            response.put("success", false);
        }

        return ResponseEntity.ok(response);
    }
}
