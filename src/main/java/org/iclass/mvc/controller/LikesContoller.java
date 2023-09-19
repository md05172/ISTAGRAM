package org.iclass.mvc.controller;
/*
프로젝트 팀명:ISTAGRAM(인스타그램 흉내)
작성자: 김종훈(팀장)
*/
import java.util.HashMap;
import java.util.Map;

import org.iclass.mvc.service.LikesService;
import org.iclass.mvc.vo.Likes;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class LikesContoller {

	private final LikesService service;
	private ObjectMapper objMapper = new ObjectMapper();

	@PostMapping("/insert/likes")
	@ResponseBody
	public String Likesinsert(@RequestBody String lik) throws JsonProcessingException {
		Likes vo = null;
		try {
			vo = objMapper.readValue(lik, Likes.class);
			System.out.println(vo);
		} catch (Exception e) {
		}
		int result = service.insert(vo);
		return objMapper.writeValueAsString(result);
	}

	@DeleteMapping("/delete/likes/{nickname}/{pno}")
	public String LikesDelete(@PathVariable String nickname, @PathVariable String pno) throws JsonProcessingException {
		Map<String, Object> map = new HashMap<>();
		map.put("nickname", nickname);
		map.put("pno", pno);
		int result = service.delete(map);
		return objMapper.writeValueAsString(result);
	}

	
	  @GetMapping("/selectCount/{nickname}/{pno}") 
	  public String selectCount(@PathVariable String nickname, @PathVariable String pno) throws JsonProcessingException { 
		  Map<String, Object> map = new HashMap<>();
		  map.put("pno", pno);
		  map.put("nickname", nickname);
		  int result = service.selectCount(map);
		  System.out.println(nickname);
		  System.out.println(pno);
		  System.out.println(result); 
		  return objMapper.writeValueAsString(result); 
	  }
	 
}
