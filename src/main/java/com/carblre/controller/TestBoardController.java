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
import com.carblre.dto.ReplyCommentDTO;
import com.carblre.dto.Response;
import com.carblre.dto.userdto.UserDTO;
import com.carblre.handler.exception.DataDeliveryException;
import com.carblre.handler.exception.UnAuthorizedException;
import com.carblre.repository.model.Comment;
import com.carblre.repository.model.User;
import com.carblre.service.CommentService;
import com.carblre.service.UserService;
import com.carblre.utils.Define;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
@RequestMapping("/board")
public class TestBoardController {
	
	@Autowired
	private TestBoardService boardService;

	@Autowired
	private CommentService commentService;

	@Autowired
	private HttpSession session;
    @Autowired
    private UserService userService;

	//-----게시글 상세보기
	@GetMapping("/detail/{id}")
	public String detailPage(@PathVariable(name ="id") int postId, Model model) {
		DetailDTO detailDTO = boardService.selectByPostId(postId);
		System.out.println(detailDTO);
		model.addAttribute("post",detailDTO);

		return "Board/postDetail";
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
    public String test(@SessionAttribute(name=Define.PRINCIPAL) UserDTO dto){
    	if(dto == null) {
    		throw new UnAuthorizedException(Define.ENTER_YOUR_LOGIN, HttpStatus.UNAUTHORIZED);
    	}
		
		
        return "/Board/createPost";
    }

	@PostMapping("/savePost")
	// TODO - 세션값 받아와서 User_id 셋 해야됨
    public String postMethodName(
    							@RequestParam(name="category")String category,
    							@RequestParam(name="title")String title,
    							@RequestParam(name="content")String content,
    							@RequestParam(name="uploardFileName")MultipartFile vidio,
    							@SessionAttribute(name=Define.PRINCIPAL) UserDTO dto) {
        
		boardService.savePost(dto.getId(), category, title, content, vidio);
		
		
        return "redirect:/board/boardList";
    }
	// --- END 게시글 작성 로직

	/**
	 * [GET] 댓글 목록 불러옵니다.
	 * @param postId 글 고유 식별 아이디입니다.
	 * @param sortBy 정렬 기준입니다.
	 * @return commentList ToJSON 이라 생각하시면 됩니다.(ResponseBody)
	 */
	@GetMapping("/comment")
	@ResponseBody
	public List<CommentDTO> getComment(@RequestParam(name="id") int postId, @RequestParam(name="sortBy") String sortBy) {
        return commentService.getCommentsByCriteria(postId, sortBy); // List 반환
	}

	/**
	 * [POST] 댓글 작성 기능입니다.
	 * @param commentDTO postDetail.jsp 를 통해서 받아온 값 (postId, userId, comment) 을 DTO/BUILDER 이용으로 작성
	 * @return
	 */
	@PostMapping("/comment")
	public ResponseEntity<?> addComment(@RequestBody CommentDTO commentDTO) {

		UserDTO principal = (UserDTO) session.getAttribute("principal");

		Map<String, String> response = new HashMap<>();

		if (principal == null)
		{
			response.put("message", Define.ENTER_YOUR_LOGIN);
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(response);
		}

		CommentDTO commentBuilder = CommentDTO.builder()
				.commentId(commentDTO.getCommentId())
				.postId(commentDTO.getPostId())
				.userId(principal.getId())
				.comment(commentDTO.getComment())
				.createdAt(commentDTO.getCreatedAt())
				.userName(principal.getUserName())
				.build();

		commentService.writeComment(commentBuilder);

		return ResponseEntity.ok(commentBuilder);
	}

	/**
	 * [GET] 댓글 삭제 기능입니다.
	 * @param commentId fetch 를 통해 commentId를 받고 WHERE 절에 넣어 쿼리를 진행합니다.
	 * @return ResponseEntity
	 */
	@GetMapping("/deleteComment")
	public ResponseEntity<?> deleteComment(@RequestParam(name="commentId") int commentId, @RequestParam(name="userId") int userId)
	{
		System.out.println("HELLO IT IS DELETE METHOD");
		UserDTO principal = (UserDTO) session.getAttribute("principal");
		Map<String, String> response = new HashMap<>();
		if (principal == null)
		{
			response.put("message", Define.ENTER_YOUR_LOGIN);
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(response);
		}

		if (principal.getId() != userId)
		{
			response.put("message", Define.NOT_AN_AUTHENTICATED_USER);
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(response);
		}

		int result = commentService.deleteComment(commentId);

		if(result != 1)
		{
			return ResponseEntity.status(500).build();
		}
		response.put("message", "삭제하였습니다.");
		return ResponseEntity.ok(response);

	}

	/**
	 * [GET] 대댓글을 불러오는 기능입니다.
	 * @param postId = 게시글 아이디입니다.
	 * @return
	 */
	@GetMapping("replyComment")
	@ResponseBody
	public List<ReplyCommentDTO> getReplyComment(@RequestParam(name="id") int postId)
	{
		System.out.println("POST ID : " + postId);
		List<ReplyCommentDTO> replyCommentList = commentService.getReplyComments(postId);
		System.out.println("Reply Comment List 입니다 : " + replyCommentList.toString());
		return commentService.getReplyComments(postId);
	}


	/**
	 * [POST] 대댓글을 작성하는 기능입니다.
	 * @param replyCommentDTO commentId, userId, comment 를 받습니다.
	 * @return
	 */
	@PostMapping("/replyComment")
	public ResponseEntity<?> addReplyComment(@RequestBody ReplyCommentDTO replyCommentDTO)
	{
		UserDTO principal = (UserDTO) session.getAttribute("principal");
		System.out.println("Reply Comment " + replyCommentDTO.getPostId());
		System.out.println("Reply Comment " + replyCommentDTO.getCommentId());
		System.out.println("Reply Comment " + principal.getId());
		System.out.println("Reply Comment " + replyCommentDTO.getComment());
		ReplyCommentDTO replyCommentBuilder = ReplyCommentDTO.builder()
				.commentId(replyCommentDTO.getCommentId())
				.postId(replyCommentDTO.getPostId())
				.userId(principal.getId())
				.userName(principal.getUserName())
				.comment(replyCommentDTO.getComment())
				.build();
		commentService.writeReplyComment(replyCommentBuilder);

		return ResponseEntity.ok(replyCommentBuilder);
	}

	/**
	 * [GET] 대댓글 삭제 기능입니다.
	 * @param replyId fetch 를 통해 replyId를 받고 WHERE 절에 넣어 쿼리를 진행합니다.
	 * @return ResponseEntity
	 */
	@GetMapping("/deleteReply")
	public ResponseEntity<String> deleteReply(@RequestParam(name="replyId") int replyId, @RequestParam(name="userId") int userId)
	{
		UserDTO principal = (UserDTO) session.getAttribute("principal");

		if (principal == null)
		{
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(Define.ENTER_YOUR_LOGIN);
		}

		if (principal.getId() != userId)
		{
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(Define.NOT_AN_AUTHENTICATED_USER);
		}

		int result = commentService.deleteReply(replyId);

		if(result != 1)
		{
			return ResponseEntity.status(500).build();
		}

		return ResponseEntity.ok("삭제하였습니다.");

	}







}