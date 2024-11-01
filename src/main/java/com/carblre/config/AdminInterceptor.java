package com.carblre.config;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import com.carblre.dto.userdto.UserDTO;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class AdminInterceptor implements HandlerInterceptor {

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		// 세션에서 사용자 역할 확인
		UserDTO principal = (UserDTO) request.getSession().getAttribute("principal");
		if (principal == null) {
			response.sendRedirect("/access-denied"); // 접근 거부 페이지로 리다이렉트
			return false;
		}
		String userRole = principal.getRole();

		// 관리자 페이지 접근 확인
		if (request.getRequestURI().startsWith("/admin") && (userRole == null || !userRole.equals("admin"))) {
			response.sendRedirect("/access-denied"); // 접근 거부 페이지로 리다이렉트
			return false;
		}

		return true; // 접근 허용
	}
}