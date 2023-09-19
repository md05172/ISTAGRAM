package org.iclass.mvc.controller;

import java.util.List;

import org.iclass.mvc.service.CommentService;
import org.iclass.mvc.vo.Comment;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class CommentController {
    
    private final CommentService service;
    private ObjectMapper objmapper = new ObjectMapper();

    // 댓글 목록 조회
    @GetMapping("/commentsList/{pno}")
    public String getCommentList(@PathVariable int pno)throws JsonMappingException, JsonProcessingException {
    	System.out.println("pno = " + pno);
    	List<Comment> commentList = service.getCommentList(pno);
        return objmapper.writeValueAsString(commentList);
    }

    // 댓글 작성
    @PostMapping("/comment/insert")
    public String addComment(@RequestBody String comment) throws JsonMappingException, JsonProcessingException {
    	System.out.println("comment = " + comment);
    	Comment com = objmapper.readValue(comment, Comment.class);
    	//댓글 등록 DB에들어가는 메소드
        int result = service.addComment(com);
        System.out.println("result = " + result);
        List<Comment> commentList = service.getCommentList(com.getPno());
        return objmapper.writeValueAsString(commentList);
    }

    // 댓글 삭제
    @DeleteMapping("/comment/delete/{cno}")
    @ResponseBody
    public int deleteComment(@PathVariable int cno) {
        int result = service.deleteComment(cno);
        return result;
    }
}
