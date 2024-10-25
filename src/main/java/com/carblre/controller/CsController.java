package com.carblre.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttribute;

import com.carblre.dto.userdto.UserDTO;

@Controller
@RequestMapping("/cs")
public class CsController {
	
	
	
	/**
	 * 고객센터
	 * @return
	 */
    @GetMapping("/cs")
    public String csPage() {
        return "cs/cs";
    }
    
    
    
    /**
     * 고객센터 글작성 폼
     * @return
     */
    @GetMapping("/write")
    public String csWritePage(@RequestParam(name="title")String tilte, 
    	        	          @RequestParam(name="content")String content,
    	        	          @SessionAttribute(name = "Define.PRINCIPAL")UserDTO user) {
    	
    	user.getId();
    	
    	
        return "cs/write";
    }

}
