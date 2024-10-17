package com.carblre.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;

import com.carblre.dto.DetailDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.method.annotation.StreamingResponseBody;

import com.carblre.repository.model.Post;
import com.carblre.service.TestBoardService;

import jakarta.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/")
public class TestBoardController {

	@Autowired
	private TestBoardService boardService;



	//-----게시글 상세보기
	@GetMapping("/detail/{id}")
	public String detailPage(@PathVariable(name ="id")int postId,Model model) {
		DetailDTO detailDTO = boardService.selectByPostId(postId);
		model.addAttribute("post",detailDTO);

		return "Board/postDetail";
	}

	@GetMapping("/download")
	public StreamingResponseBody stream(HttpServletRequest req, @RequestParam("fileName") String fileName) throws Exception {

		String DIR = "C:\\Users\\KDP\\git\\Carblre\\src\\main\\resources\\static\\uploardVidio/";

		File file = new File(DIR + fileName);
		final InputStream is = new FileInputStream(file);
		return os -> {
			readAndWrite(is, os);
		};
	}

	private void readAndWrite(final InputStream is, OutputStream os) throws IOException {
		byte[] data = new byte[8192]; // 8KB 버퍼
		int totalRead = 0;
		int read;

		while ((read = is.read(data)) > 0) {
			totalRead += read;
			if (totalRead > 20 * 1024 * 1024) { // 20MB 초과 체크
				throw new IOException("파일 크기가 20MB를 초과했습니다.");
			}
			os.write(data, 0, read);
		}

		os.flush();
	}




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