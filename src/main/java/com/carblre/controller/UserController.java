package com.carblre.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.apache.ibatis.javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.carblre.config.MyWebSocketHandler;
import com.carblre.dto.MyCounselDTO;
import com.carblre.dto.SignUpDTO;
import com.carblre.dto.userdto.KakaoOAuthToken;
import com.carblre.dto.userdto.SignInDTO;
import com.carblre.dto.userdto.SocialSignUpDTO;
import com.carblre.dto.userdto.UserDTO;
import com.carblre.handler.exception.DataDeliveryException;
import com.carblre.handler.exception.UnAuthorizedException;
import com.carblre.service.CounselService;
import com.carblre.service.QrcodeService;
import com.carblre.service.UserService;
import com.carblre.utils.Define;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {
	@Value("${google.client-id}")
	private String googleClientId;

	@Value("${google.client-secret}")
	private String googleClientSecret;

	@Value("${google.redirect-uri}")
	private String googleRedirectUri;

	@Value("${naver.client-id}")
	private String naverClientId;

	@Value("${naver.client-secret}")
	private String naverClientSecret;

	@Value("${naver.redirect-uri}")
	private String naverRedirectUri;

	@Value("${kakao.client-id}")
	private String kakaoClientId;

	@Value("${kakao.redirect-uri}")
	private String kakaoRedirectUri;

	private final UserService userService;
	private final QrcodeService qrcodeService;
	private final CounselService counselService;

	private final HttpSession session;

	private final MyWebSocketHandler webSocketHandler;

	private final PasswordEncoder passwordEncoder;

	@GetMapping("/signIn")
	public String signPage() {

		return "user/signin";
	}

	/**
	 * [POST] 로그인 프로세스입니다.
	 *
	 * @param dto = SignInDTO <- String nickName, String password(유저가 입력한 값)
	 * @return 유효성 검사, 회원 존재 여부, 회원 정보 일치 여부 확인 후 맞는 응답(Exception OR Return jsp) 내려줌
	 */
	@PostMapping("/signIn")
	public String signInProc(SignInDTO dto) {

		String nickName = dto.getNickName(); // 유저가 입력한 아이디
		String password = dto.getPassword(); // 유저가 입력한 비밀번호

		// nickName 을 사용하여 유저가 존재하는지 판단합니다.
		UserDTO userDTO = userService.findByNickId(nickName);

		if (userDTO == null) {
			// 유저가 존재하지 않는다면 DataDeliveryException 을 사용하여 Alert
			throw new DataDeliveryException(Define.NOT_EXISTING_USER, HttpStatus.BAD_REQUEST);
		}

		// DB에 존재하는 유저의 암호화된 비밀번호를 받아옵니다.
		String hashedPassword = userDTO.getPassword();

		// PasswordEncoder 의 matches 를 이용하여 유저가 입력한 비밀번호와 DB에 있는 암호화된 비밀번호를 비교합니다.
		boolean isMatch = passwordEncoder.matches(password, hashedPassword);

		if (!isMatch) {
			// 비밀번호가 일치하지 않는다면 DataDeliveryException 을 이용하여 Alert
			throw new DataDeliveryException(Define.NOT_MATCH_ACCOUNT_INFO, HttpStatus.BAD_REQUEST);
		}

		// 위의 검사를 모두 통과했다면 'principal' Session 부여합니다.
		session.setAttribute("principal", userDTO);

		// 마지막으로 페이지 이동 처리를 진행합니다.
		return "redirect:/user/index";// 임시 인덱스 장소로 이동함
	}

	@GetMapping("/signIn/token={token}")
	public String signinQrcode(@PathVariable(name = "token") String token, HttpSession session) throws IOException {
		System.out.println(token);
		if (qrcodeService.isValid(token)) {
			if (webSocketHandler.getActiveSessionsCount() > 0) { // 세션이 있는 경우에만 메시지 전송
				webSocketHandler.sendMessageToAll("login_success");
			} else {
				log.warn("No active WebSocket sessions found. Cannot send message.");
			}
			return "user/successphone"; // 핸드폰에서의 처리
		} else {
			return "error";
		}
	}

	@GetMapping("/signUp")
	public String signupPage() {
		System.out.println("Here in signUpPage(UserController)");
		return "user/signup";
	}

	/**
	 * [POST] 회원가입 로직
	 *
	 * @param signUpDTO = 사용자의 입력값
	 * @return signIn.jsp
	 */
	@PostMapping("/signUp")
	public String signUpProc(SignUpDTO signUpDTO) {
		// HTML required 속성으로 null 체크 X
		if (signUpDTO.getNickName() == null) {
			System.out.println("HBERERRERE");
		}
		userService.createUser(signUpDTO);
		System.out.println(signUpDTO);
		// signIn (Login Page) 이동 처리
		return "redirect:/user/signIn";
	}

	@GetMapping("/logout")
	public String logout(HttpSession session) {
		// 세션 무효화
		session.invalidate();
		// 로그아웃 후 리다이렉트
		return "redirect:/user/signIn";
	}

	/**
	 * 홈페이지 이동(아이디찾기)
	 *
	 * @return
	 */
	@GetMapping("/findId")
	public String findPage() {

		// signIn (Login Page) 이동 처리
		return "user/findId";
	}

	/**
	 * ID찾기
	 *
	 * @param email
	 * @return
	 */
	@ResponseBody
	@GetMapping("/email")
	public UserDTO FindPageProc(@RequestParam(name = "email") String email) {
		// HTML required 속성으로 null 체크 X
		UserDTO dto = userService.findIdByEmail(email);
		System.out.println(dto);
		if (!dto.getSite().equals("서버") || dto == null || dto.getEmail() == null) {// 값이 없거나 서버가 안맞거나
			return new UserDTO();

		}
		// signIn (Login Page) 이동 처리
		return dto;
	}

	/**
	 * 임시 인덱스-> 인덱스로 수정
	 *
	 * @return
	 */
	@GetMapping("/index")
	public String Index() {

		return "index";
	}

	/**
	 * 카카오 api 로그인(가입)
	 *
	 * @param code
	 * @return
	 */
	@GetMapping("/kakao")
	public String kakaoPage(@RequestParam(name = "code") String code) {
		String kakaoClientId = "74ae415425bf1b53dc8f8dcf38efc2d4"; // 카카오 클라이언트 ID
		String kakaoRedirectUri = "http://localhost:8080/user/kakao"; // 리다이렉트 URI

		RestTemplate rt1 = new RestTemplate();
		HttpHeaders header1 = new HttpHeaders();
		header1.add("Content-type", "application/x-www-form-urlencoded;charset=utf-8");
		MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
		params.add("grant_type", "authorization_code");
		params.add("client_id", kakaoClientId);
		params.add("redirect_uri", kakaoRedirectUri);
		params.add("code", code);

		HttpEntity<MultiValueMap<String, String>> responseKakaoToken = new HttpEntity<>(params, header1);

		// 카카오 서버로 토큰 요청
		ResponseEntity<KakaoOAuthToken> response = rt1.postForEntity("https://kauth.kakao.com/oauth/token",
				responseKakaoToken, KakaoOAuthToken.class);

		// 응답 데이터에서 액세스 토큰 추출
		KakaoOAuthToken kakaoToken = response.getBody();
		if (kakaoToken != null) {
			System.out.println("카카오 액세스 토큰: " + kakaoToken.getAccessToken());
		} else {
			System.out.println("카카오 토큰을 받지 못했습니다.");
		}

		// 토큰을 가지고 추가 작업 수행 가능 (ex: 카카오 사용자 정보 요청)

		// 사용자 정보 요청
		RestTemplate rt2 = new RestTemplate();
		HttpHeaders header2 = new HttpHeaders();
		header2.add("Authorization", "Bearer " + kakaoToken.getAccessToken());
		header2.add("Content-type", "application/x-www-form-urlencoded;charset=utf-8");

		HttpEntity<HttpHeaders> reqUserInfo = new HttpEntity<>(header2);

		// 카카오 서버로 사용자 정보 요청
		ResponseEntity<Map> userInfoResponse = rt2.postForEntity("https://kapi.kakao.com/v2/user/me", reqUserInfo,
				Map.class);

		Map<String, Object> kakaoUserInfo = userInfoResponse.getBody();

		// 사용자 정보 추출
		Map<String, Object> kakaoAccount = (Map<String, Object>) kakaoUserInfo.get("kakao_account");
		String email = (String) kakaoAccount.get("email");
		String nickname = (String) ((Map<String, Object>) kakaoUserInfo.get("properties")).get("nickname");
		Long kakaoId = (Long) kakaoUserInfo.get("id"); // Long으로 변환

		String kakaoIdStr = String.valueOf(kakaoId);

		UserDTO principial = userService.findByNickId(kakaoIdStr);

		// 최초 시도
		if (principial == null) {

			SocialSignUpDTO socialSignUpDTO = SocialSignUpDTO.builder().email(email).nickName(kakaoIdStr)
					.userName(nickname).site("카카오").role("user").status(1).build();
			userService.saveUser(socialSignUpDTO); // MyBatis Mapper를 사용하여 DB에 저장

		}

		session.setAttribute("principal", principial);

		// DB에 사용자 정보 저장

		return "redirect:/user/index";

	}

	// 네이버-----
	@GetMapping("/naver-login")
	/**
	 * 네이버 로그인 페이지로 리다이렉트하기 전에 state 값을 생성
	 *
	 * @return
	 */
	public String naverLogin() {
		// state 값 생성 (UUID 등을 이용한 임의 문자열 생성)
		String state = UUID.randomUUID().toString();

		// state 값을 세션에 저장
		session.setAttribute("state", state);

		// 네이버 인증 URL 생성
		String naverClientId = "Qus9K_Ha8Jkwvf5bdI_n"; // 네이버 클라이언트 ID
		String naverRedirectUri = "http://localhost:8080/user/naver"; // 리다이렉트 URI

		String naverLoginUrl = "https://nid.naver.com/oauth2.0/authorize?client_id=" + naverClientId + "&redirect_uri="
				+ naverRedirectUri + "&response_type=code&state=" + state;

		// 네이버 API에서 리다이렉트된 후 토큰 및 사용자 정보 처리
		return "redirect:" + naverLoginUrl;
	}

	/**
	 * 네이버 로그인
	 *
	 * @param code
	 * @param state
	 * @return
	 */
	@GetMapping("/naver")
	public String naverPage(@RequestParam(name = "code") String code, @RequestParam(name = "state") String state) {
		String naverClientId = "Qus9K_Ha8Jkwvf5bdI_n"; // 네이버 클라이언트 ID
		String naverClientSecret = "T4LBmHLPwo"; // 네이버 클라이언트 시크릿

		String sessionState = (String) session.getAttribute("state");
		if (sessionState == null || !sessionState.equals(state)) {
			throw new IllegalStateException("Invalid state value");
		}

		// 1. 액세스 토큰 요청
		RestTemplate rt1 = new RestTemplate();
		HttpHeaders headers1 = new HttpHeaders();
		headers1.add("Content-type", "application/x-www-form-urlencoded;charset=utf-8");

		MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
		params.add("grant_type", "authorization_code");
		params.add("client_id", naverClientId);
		params.add("client_secret", naverClientSecret);
		params.add("code", code);
		params.add("state", state);

		HttpEntity<MultiValueMap<String, String>> naverTokenRequest = new HttpEntity<>(params, headers1);
		ResponseEntity<Map> naverTokenResponse = rt1.postForEntity("https://nid.naver.com/oauth2.0/token",
				naverTokenRequest, Map.class);

		Map<String, Object> naverTokenMap = naverTokenResponse.getBody();
		String accessToken = (String) naverTokenMap.get("access_token");

		if (accessToken == null) {
			System.out.println("네이버 토큰을 받지 못했습니다.");
			return "redirect:/user/signup";
		}

		// 2. 사용자 정보 요청
		RestTemplate rt2 = new RestTemplate();
		HttpHeaders headers2 = new HttpHeaders();
		headers2.add("Authorization", "Bearer " + accessToken);

		HttpEntity<HttpHeaders> userInfoRequest = new HttpEntity<>(headers2);
		ResponseEntity<Map> naverUserInfoResponse = rt2.postForEntity("https://openapi.naver.com/v1/nid/me",
				userInfoRequest, Map.class);

		Map<String, Object> naverUserInfo = naverUserInfoResponse.getBody();
		Map<String, Object> responseMap = (Map<String, Object>) naverUserInfo.get("response");
		String email = (String) responseMap.get("email");
		String name = (String) responseMap.get("name");
		String naverId = (String) responseMap.get("id");

		// 3. 사용자 정보 DB 저장 또는 로그인 처리
		UserDTO principial = userService.findByNickId(naverId);

		if (principial == null) {
			SocialSignUpDTO socialSignUpDTO = SocialSignUpDTO.builder().email(email).nickName(naverId).userName(name)
					.site("네이버").role("user").status(1).build();

			userService.saveUser(socialSignUpDTO);
			principial = userService.findByNickId(naverId);
		}
		System.out.println("프린시펄" + principial);
		session.setAttribute("principal", principial);

		return "redirect:/user/index";
	}

	// 구글

	/**
	 * 구글 로그인
	 *
	 * @param code
	 * @return
	 */
	@GetMapping("/google")
	public String googlePage(@RequestParam(name = "code") String code) {

		try {
			// 1. 액세스 토큰 요청
			RestTemplate rt1 = new RestTemplate();
			HttpHeaders headers1 = new HttpHeaders();
			headers1.add("Content-type", "application/x-www-form-urlencoded;charset=utf-8");

			MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
			params.add("grant_type", "authorization_code");
			params.add("client_id", googleClientId);
			params.add("client_secret", googleClientSecret);
			params.add("code", code);
			params.add("redirect_uri", googleRedirectUri);

			HttpEntity<MultiValueMap<String, String>> googleTokenRequest = new HttpEntity<>(params, headers1);

			ResponseEntity<Map> googleTokenResponse = rt1.postForEntity("https://oauth2.googleapis.com/token",
					googleTokenRequest, Map.class);

			Map<String, Object> googleTokenMap = googleTokenResponse.getBody();
			if (googleTokenMap == null || !googleTokenMap.containsKey("access_token")) {
				System.out.println("구글 토큰을 받지 못했습니다.");
				return "redirect:/user/signup";
			}

			String accessToken = (String) googleTokenMap.get("access_token");
			System.out.println("토큰 확인: " + accessToken);

			// 2. 사용자 정보 요청
			RestTemplate rt2 = new RestTemplate();
			HttpHeaders headers2 = new HttpHeaders();
			headers2.add("Authorization", "Bearer " + accessToken);
			headers2.add("Content-Type", "application/json"); // 헤더 추가

			HttpEntity<HttpHeaders> userInfoRequest = new HttpEntity<>(headers2);

			ResponseEntity<Map> googleUserInfoResponse = rt2.exchange(
					"https://www.googleapis.com/oauth2/v1/userinfo?alt=json", HttpMethod.GET, userInfoRequest,
					Map.class);

			Map<String, Object> googleUserInfo = googleUserInfoResponse.getBody();
			if (googleUserInfo == null) {
				System.out.println("구글 사용자 정보를 받지 못했습니다.");
				return "redirect:/user/signup";
			}

			String email = (String) googleUserInfo.get("email");
			String name = (String) googleUserInfo.get("name");
			String googleId = (String) googleUserInfo.get("id");

			UserDTO principial = userService.findByNickId(googleId);

			if (principial == null) {
				SocialSignUpDTO socialSignUpDTO = SocialSignUpDTO.builder().email(email).nickName(googleId)
						.userName(name).site("구글").role("user").status(1).build();
				System.out.println(socialSignUpDTO);
				userService.saveApiUser(socialSignUpDTO); // 사용자 정보 저장
				principial = userService.findByNickId(googleId); // 다시 조회하여 세션에 저장
			}

			session.setAttribute("principal", principial);
			return "redirect:/user/index";

		} catch (HttpClientErrorException e) {
			System.out.println("Google API 호출 시 오류 발생: " + e.getResponseBodyAsString());
			return "redirect:/user/signup";
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("구글 인증 과정에서 오류 발생");
			return "redirect:/user/signup";
		}
	}

	/**
	 * signUp.jsp checkDuplicate Method(In JavaScript)
	 *
	 * @param userId = 유저가 입력한 값 (아이디)
	 * @return ResponseEntity(OK or Error)
	 */
	@GetMapping("/checkId")
	public ResponseEntity<Map<String, Object>> checkId(@RequestParam(name = "userId") String userId) {
		System.out.println("User ID : " + userId);
		// 응답(ResponseEntity)을 담을 HashMap<> 선언
		Map<String, Object> responseMessage = new HashMap<>();

		// 사용자가 입력한 nickName
		UserDTO userDTO = userService.findByNickId(userId);

		if (userDTO == null) {
			responseMessage.put("message", true);
		} else {
			responseMessage.put("message", false);
		}

		return ResponseEntity.ok(responseMessage);
	}

	/**
	 * 비밀번호 찾기 경로
	 *
	 * @return
	 */
	@GetMapping("/findPass")
	public String findPassPage() {

		return "user/findPass";
	}

	/**
	 * 비밀번호 변경을 위한 아이디 확인 로직( 아이디,이메일)
	 *
	 * @param email
	 * @param nickName
	 * @param model
	 * @return
	 */
	@GetMapping("/emailNick")
	public String FindPageGetEmail(@RequestParam(name = "email") String email,
			@RequestParam(name = "nickName") String nickName, Model model) {
		UserDTO dto = userService.findIdByEmailNick(email, nickName);
		System.out.println(dto);
		model.addAttribute("UserId", dto.getId());
		return "user/updatePass";
	}

	/**
	 * 비밀번호 변경 로직 비밀번호 찾기-> 비밀번호 변경 , 내정보 수정 -> 비밀번호 변경
	 *
	 * @param reqData
	 * @return
	 */
	@PostMapping("/updatePass")
	public ResponseEntity<Map<String, Object>> updatePassProc(@RequestBody Map<String, String> reqData) {
		String changedPassword = reqData.get("changedPassword");
		String checkedPassword = reqData.get("checkedPassword");
		int id = Integer.parseInt(reqData.get("id"));
		Map<String, Object> response = new HashMap<>();

		int result = userService.updatePassword(changedPassword, id);
		System.out.println(result);
		if (result == 1) {
			response.put("status", 1); // 성공
			response.put("message", "비밀번호 변경 성공");
		}

		return ResponseEntity.ok(response);
	}

	@GetMapping("/infoUpdate")
	public String infoUpdatePage(Model model) throws NotFoundException {
		UserDTO userDTO = (UserDTO) session.getAttribute("principal");
		if (userDTO == null) {
			// 엔티티가 존재하지 않을 때 NotFoundException 던짐
			throw new UnAuthorizedException("로그인을 해주세요", HttpStatus.UNAUTHORIZED);
		}

		UserDTO originUser = userService.findById((userDTO.getId()));

		model.addAttribute("originUser", originUser);
		return "user/infoUpdate";
	}

	/**
	 * 유저 가입 선택 페이지
	 *
	 * @return
	 */
	@GetMapping("/selectSignUp")
	public String selectSignupPage() {
		System.out.println("Here in selectSignUpPage(UserController)");
		return "user/selectSignup";
	}

	/**
	 * TODO 정확히 어떤 정보를 수정할껀지 모르겠음 -ex:email 밖에 안떠오름 ,handler(email 중복에대한)
	 *
	 * @param updateDto
	 * @return
	 */
	@PostMapping("/infoUpdate")
	public String infoUpdateProc(UserDTO updateDto, RedirectAttributes redirectAttributes) {
		UserDTO userDTO = (UserDTO) session.getAttribute("principal");

		int result = userService.updateInfo(updateDto.getEmail(), userDTO.getId());
		if (result == 1) {
			redirectAttributes.addFlashAttribute("message", "정보가 수정되었습니다.");
		} else {
			redirectAttributes.addFlashAttribute("message", "수정에 실패했습니다.");
		}

		return "redirect:/user/infoUpdate";
	}

	@GetMapping("/infoUpdatePass")
	public String infoUpdatePassPage(Model model) {
		UserDTO userDTO = (UserDTO) session.getAttribute("principal");
		if (userDTO == null) {
			// 엔티티가 존재하지 않을 때 NotFoundException 던짐
			throw new UnAuthorizedException("로그인을 해주세요", HttpStatus.UNAUTHORIZED);
		}
		UserDTO dto = userService.findIdByEmailNick(userDTO.getEmail(), userDTO.getNickName());
		System.out.println(dto);
		model.addAttribute("UserId", dto.getId());
		return "user/infoUpdatePass";
	}

	/**
	 * 비동기 비밀번호 대조
	 *
	 * @param reqData
	 * @return
	 */
	@PostMapping("/checkOriginPass")
	public ResponseEntity<Map<String, Object>> checkOriginPassProc(@RequestBody Map<String, String> reqData) {
		UserDTO userDTO = (UserDTO) session.getAttribute("principal");
		String dbCheckPass = userService.findById((userDTO.getId())).getPassword();
		System.out.println("db비번" + dbCheckPass);

		String originpass = reqData.get("originPass");
		System.out.println("기입비번" + originpass);

		Map<String, Object> response = new HashMap<>();
		boolean result = userService.findPassword(originpass, dbCheckPass);// 패스워드 확인
		System.out.println("result" + result);
		if (result) {
			response.put("status", 1); // 성공
			response.put("message", "비밀번호 일치");
		} else {
			response.put("status", 0); // 실패
			response.put("message", "비밀번호 불일치");
		}
		return ResponseEntity.ok(response);
	}

	/**
	 * 
	 * 유저의 예약현황 확인 페이지
	 * 
	 * @param model
	 * @return
	 */
	@GetMapping("checkUserCounsel")
	public String checkUserCounselPage(Model model) {
		UserDTO userDTO = (UserDTO) session.getAttribute("principal");
		if (userDTO == null) {// 엔티티가 존재하지 않을 때 NotFoundException 던짐
			throw new UnAuthorizedException("로그인을 해주세요", HttpStatus.UNAUTHORIZED);
		} // 유저 인포 해야됨
		List<MyCounselDTO> counsel = counselService.findMyCounselByUserId(userDTO.getId());
		System.out.println("counsel확인" + counsel);
		model.addAttribute("counselList", counsel);

		return "counsel/checkUserCounsel";
	}

	@GetMapping("/myPage")
	public String myPage(Model model) {
		UserDTO userDTO = (UserDTO) session.getAttribute("principal");
		if (userDTO == null) {
			// 엔티티가 존재하지 않을 때 NotFoundException 던짐
			throw new UnAuthorizedException("로그인을 해주세요", HttpStatus.UNAUTHORIZED);
		}
		// 유저 인포 해야됨
		return "user/myPage";
	}

}
