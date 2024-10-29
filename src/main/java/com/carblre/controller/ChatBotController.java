package com.carblre.controller;

import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.carblre.service.ChatBotService;

import lombok.RequiredArgsConstructor;

@RequestMapping("/")
@RequiredArgsConstructor
@Controller
public class ChatBotController {
	
	private final ChatBotService chatBotService;
	
	@GetMapping("/aiounseling")
	public String getaimethod() {
		return "/chatBot";
	}
	
	
	@PostMapping("/chatbot")
	@ResponseBody
	public String postMethodName(@RequestBody Map<String, String> payload) {
		String message = payload.get("message");
		return chatBotService.chatOpenAi(message, 15);
	}
	
	

}