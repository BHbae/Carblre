package com.carblre.controller;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import com.carblre.dto.SignUpDTO;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import com.carblre.dto.userdto.KakaoOAuthToken;
import com.carblre.dto.userdto.SignDTO;
import com.carblre.dto.userdto.UserDTO;
import com.carblre.service.UserService;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;

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

	private final HttpSession session;

	@GetMapping("/signIn")
	public String signPage() {

		return "user/signin";
	}

	@PostMapping("/signIn")
	public String signInProc(SignDTO dto, Model model) {
		UserDTO principial = userService.findByNickId(dto.getNickName());

		if (principial == null) {
			model.addAttribute("alertMessage", "아이디를 확인해주세요.");
			return "user/signin";
		}
		if(!principial.getPassword().equals(dto.getPassword())) {
			model.addAttribute("alertMessage", "비밀번호를 확인해주세요");
			return "user/signin";
		}

		session.setAttribute("principal", principial);
		return "redirect:/user/tempindex";// 임시 인덱스 장소로 이동함
	}

	@GetMapping("/signUp")
	public String signupPage() {
		System.out.println("Here in signUpPage(UserController)");
		return "user/signup";
	}

	/**
	 * [POST] 회원가입 로직
	 * @param signUpDTO = 사용자의 입력값
	 * @return signIn.jsp
	 */
	@PostMapping("/signUp")
	public String signUpProc(SignUpDTO signUpDTO)
	{
		// HTML required 속성으로 null 체크 X
		userService.createUser(signUpDTO);

		// signIn (Login Page) 이동 처리
		return "redirect:/user/signin";
	}

	/**
	 * 임시 인덱스
	 * 
	 * @return
	 */
	@GetMapping("/tempindex")
	public String tempIndex() {

		return "user/tempindex";
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

			SignDTO signDTO = SignDTO.builder().email(email).nickName(kakaoIdStr).userName(nickname).build();

			userService.saveUser(signDTO); // MyBatis Mapper를 사용하여 DB에 저장

		}

		session.setAttribute("principal", principial);

		// DB에 사용자 정보 저장

		return "redirect:/user/tempindex";

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
			SignDTO signDTO = SignDTO.builder().email(email).nickName(naverId).userName(name).build();

			userService.saveUser(signDTO);
			principial = userService.findByNickId(naverId);
		}
		System.out.println("프린시펄" + principial);
		session.setAttribute("principal", principial);

		return "redirect:/user/tempindex";
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
				SignDTO signDTO = SignDTO.builder().email(email).nickName(googleId).userName(name).build();

				userService.saveApiUser(signDTO); // 사용자 정보 저장
				principial = userService.findByNickId(googleId); // 다시 조회하여 세션에 저장
			}

			session.setAttribute("principal", principial);
			return "redirect:/user/tempindex";

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
	 * @param userId = 유저가 입력한 값 (아이디)
	 * @return ResponseEntity(OK or Error)
	 */
	@GetMapping("/checkId")
	public ResponseEntity<Map<String, Object>> checkId(@RequestParam(name = "userId") String userId)
	{
		System.out.println("User ID : " + userId);
		// 응답(ResponseEntity)을 담을 HashMap<> 선언
		Map<String, Object> responseMessage = new HashMap<>();

		// 사용자가 입력한 nickName
		UserDTO userDTO = userService.findByNickId(userId);

		if(userDTO == null)
		{
			responseMessage.put("message", true);
		}
		else
		{
			responseMessage.put("message", false);
		}

		return ResponseEntity.ok(responseMessage);
	}

}
