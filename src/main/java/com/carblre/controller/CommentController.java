package com.carblre.controller;

import com.carblre.dto.CommentDTO;
import com.carblre.repository.model.Comment;
import com.carblre.repository.model.User;
import com.carblre.service.CommentService;
import com.carblre.utils.Define;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@RequestMapping("/")
@RequiredArgsConstructor
@Controller
public class CommentController{

    private final CommentService commentService;

    @PostMapping("/comment")
    public  @ResponseBody Map<String , Object> handleCommentInsert(
            @ModelAttribute CommentDTO commentDTO,
            @SessionAttribute(Define.PRINCIPAL)User principal){

        Map<String ,Object> response = new HashMap<>();

        // TODO! 세션에 유저 정보를 저장할수 있을때 주석 해제
        // int result = commentService.writeComment(commentDTO , principal.getUserId());

//        if(result > 0){
//            response.put("success" , true);
//        } else {
//            response.put("success" ,false);
//        }

        return  response; // JSON 형태로 응답

    }

    @GetMapping("/detail/comments/{postId}")
    public @ResponseBody List<Comment> getComments(
            @RequestParam("postId") int postId,
            @RequestParam(name = "sortBy" , defaultValue = "newest")String sortBy ){

        List<Comment> commentList = commentService.getCommentsByCriteria(postId , sortBy);

        return commentList;
    }



}
