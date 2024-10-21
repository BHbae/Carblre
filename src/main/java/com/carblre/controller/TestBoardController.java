package com.carblre.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.carblre.dto.CommentDTO;
import com.carblre.dto.DetailDTO;
import com.carblre.repository.model.Comment;
import com.carblre.repository.model.User;
import com.carblre.service.CommentService;
import com.carblre.utils.Define;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.*;
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

	@Autowired
	private CommentService commentService;

	@Autowired
	private HttpSession session;
	
	//-----게시글 상세보기
	@GetMapping("/detail/{id}")
	public String detailPage(@PathVariable(name = "id") int postId, Model model,
							 @RequestParam(name = "sortBy", required = false, defaultValue = "newest") String sortBy) {
		System.out.println("Post Id : " + postId);
		System.out.println("Sort By : " + sortBy);
		DetailDTO detailDTO = boardService.selectByPostId(postId);
		List<CommentDTO> commentDTOs = commentService.getCommentsByCriteria(postId, sortBy);

		model.addAttribute("post", detailDTO);
		model.addAttribute("comments", commentDTOs); // 댓글 목록 추가

		return "Board/postDetail"; // 게시글 상세보기 뷰 리턴
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

	// 댓글

	/**
	 * 댓글 작성
	 * @param commentDTO
	 * @return
	 */
	@PostMapping("/comment")
	public ResponseEntity<Map<String, Object>> handleCommentInsert(@RequestBody CommentDTO commentDTO) {

		System.out.println("HERERERE");
		User principal = (User)session.getAttribute("principal");
		commentDTO.setUserId(1); // 하드 코딩
		System.out.println("Post ID : " + commentDTO.getPostId());
		System.out.println("Comment : " + commentDTO.getComment());

		commentDTO = CommentDTO.builder()
				.postId(commentDTO.getPostId())
				.userId(principal.getId())
				.comment(commentDTO.getComment())
				.createAt(commentDTO.getCreateAt())
				.userName(principal.getUserName())
				.build();

		int result = commentService.writeComment(commentDTO);
		System.out.println(commentDTO.toString());

		// 1 = 글쓰기 성공 . 0 = 실패
		Map<String, Object> response = new HashMap<>();
		if(result == 1){
			response.put("success", true); // 응답으로 성공 여부를 전송
		}else if( result == 0){
			response.put("fail" , false);
		}

		return ResponseEntity.ok(response);
	}

	/**
	 * 댓글 리스트
	 * @param postId
	 * @param sortBy
	 * @return
	 */
	@GetMapping("/detail/comment")
	public ResponseEntity<List<CommentDTO>> getComments(
			@RequestParam("postId") String id,  // 수정된 부분
			@RequestParam("sortBy") String sortBy) {
		System.out.println("Post ID : " + id);
		System.out.println("SortBy : " + sortBy);
		int postId = Integer.parseInt(id);
		List<CommentDTO> commentList = commentService.getCommentsByCriteria(postId, sortBy);
		System.out.println("Comment List : " + commentList);
		return ResponseEntity.ok(commentList);
	}

}