package com.carblre.controller;

import com.carblre.dto.LawyerDetailDTO;
import com.carblre.dto.MyCounselDTO;
import com.carblre.dto.userdto.LawyerSignUpDTO;
import com.carblre.dto.userdto.UserDTO;
import com.carblre.handler.exception.UnAuthorizedException;
import com.carblre.service.CounselService;
import com.carblre.service.LawyerService;
import com.carblre.service.UserService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping("/lawyer")
public class LawyerController {

    @Autowired
    private final LawyerService lawyerService;

    @Autowired
    private final UserService userService;

    @Autowired
    private final CounselService counselService;

    @Autowired
    private final HttpSession session;

    /**
     * 변호사 가입페지이 이동
     *
     * @return
     */
    @GetMapping("/lawyerSignUp")
    public String lawyerSignupPage() {
        System.out.println("Here in lawyerSignUpPage(UserController)");
        return "lawyer/lawyerSignup";
    }

    /**
     * [POST] 변호사 회원가입 로직
     *
     * @param lawyerSignUpDTO = 사용자의 입력값
     * @return signIn.jsp
     */
    @PostMapping("/lawyerSignUp")
    public String lawyerSignUpProc(LawyerSignUpDTO lawyerSignUpDTO) {
        // HTML required 속성으로 null 체크 X
        lawyerService.createLawyerUser(lawyerSignUpDTO);

        // signIn (Login Page) 이동 처리
        return "redirect:/user/signIn";
    }

    @GetMapping("/myPage")
    public String myPage(Model model) {
        UserDTO userDTO = (UserDTO) session.getAttribute("principal");
        if (userDTO == null) {
            // 엔티티가 존재하지 않을 때 NotFoundException 던짐
            throw new UnAuthorizedException("로그인을 해주세요", HttpStatus.UNAUTHORIZED);
        }
        if(userDTO.getRole().equals("lawyer")){
            com.carblre.dto.userdto.LawyerDetailDTO lawyerDetailDTO= lawyerService.findLawyerInfoById(userDTO.getId());
            System.out.println(lawyerDetailDTO);
            model.addAttribute("lawyer",lawyerDetailDTO);
        }else{
            throw new UnAuthorizedException("변호사 전용 입니다", HttpStatus.UNAUTHORIZED);
        }
        // 유저 인포 해야됨
        return "lawyer/lawyerPage";
    }

    @GetMapping("/lawyers")
    public String getAllLawyers(Model model) {
        List<LawyerDetailDTO> lawyers = lawyerService.LawyerList();

        model.addAttribute("lawyers", lawyers);
        return "lawyer/lawyerList";
    }

    // 변호사 상세보기 페이지 조회
    @GetMapping("/lawyerInfo/{userId}")
    public String LawyerInfoPage(@PathVariable(name = "userId") int userId , Model model){
        LawyerDetailDTO dto = lawyerService.selectByLawyerId(userId);
        model.addAttribute("lawyer" ,dto);
        return "lawyer/lawyerInfo";
    }

    /**
     *  변호사 예약 체크 현황
     * @param model
     * @return
     */
    @GetMapping("checkLawyerCounsel")
    public String checkLawyerCounselPage(Model model){
        UserDTO userDTO = (UserDTO) session.getAttribute("principal"); //dto변경해야함
        if (userDTO == null) {
            // 엔티티가 존재하지 않을 때 NotFoundException 던짐
            throw new UnAuthorizedException("로그인을 해주세요", HttpStatus.UNAUTHORIZED);
        }
        // 유저 인포 해야됨
        List< MyCounselDTO> counsel= counselService.findMyCounselByLawyerId(userDTO.getId());
        System.out.println("counsel"+counsel);


        model.addAttribute("counselList",counsel);

        return  "counsel/checkLawyerCounsel";
    }
}
