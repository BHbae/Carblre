package com.carblre.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.carblre.repository.Post;
import com.carblre.service.TestBoardService;

@Controller
@RequestMapping("/")
public class TestBoardController {
	
	@Autowired
	private TestBoardService boardService;
	
	
	
	//-----게시글 상세보기
	
	
	// --- 게시글 리스트 
	@GetMapping("/boardList")
	public String getMethodName(Model model) {
		
		List<Post> boards =  boardService.findAllBoards();
		
		model.addAttribute("boards",boards);
		
		return "/Board/BoardList";
	}
	// --- END 게시글 리스트 로직 
	
	
	
	// --- 게시글 작성 
	@GetMapping("/createBoard")
    public String test(){
    	
        return "/Board/createPost";
    }

	@PostMapping("/savePost")
	// TODO - 세션값 받아와서 User_id 셋 해야됨
    public String postMethodName(@RequestParam(name="status")int status,
    							@RequestParam(name="category")String category,
    							@RequestParam(name="title")String title,
    							@RequestParam(name="content")String content,
    							@RequestParam(name="uploardFileName")MultipartFile vidio) {
        
		boardService.savePost(status, category, title, content, vidio);
		
		
        return "redirect:/createBoard";
    }
	// --- END 게시글 작성 로직
	
	
	


}
