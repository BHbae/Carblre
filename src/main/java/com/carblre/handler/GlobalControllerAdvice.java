package com.carblre.handler;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import com.carblre.handler.exception.DataDeliveryException;
import com.carblre.handler.exception.UnAuthorizedException;

@ControllerAdvice
public class GlobalControllerAdvice {
	
	
	@ResponseBody
	@ExceptionHandler(DataDeliveryException.class)
	public String dataDelieryException(DataDeliveryException e) {
		StringBuffer sb = new StringBuffer();
		sb.append(" <script>");
		sb.append(" alert('"+ e.getMessage() + "');");
		sb.append(" window.history.back();");
		sb.append(" </script>");
		return sb.toString();
	}
	
	@ResponseBody
	@ExceptionHandler(UnAuthorizedException.class)
	public String unAuthorizedException(UnAuthorizedException e) {
		StringBuffer sb = new StringBuffer();
		sb.append(" <script>");
		sb.append(" alert('" + e.getMessage() + "');");
		sb.append(" location.href='/user/signIn';");
		sb.append(" </script>");

		return sb.toString();
	}
	
	
}