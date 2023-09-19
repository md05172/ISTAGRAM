package org.iclass.mvc.controller;
/*
프로젝트 팀명:ISTAGRAM(인스타그램 흉내)
작성자: 김종훈(팀장)
*/
import java.util.List;

import org.iclass.mvc.service.FollowService;
import org.iclass.mvc.vo.Follow;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.RequiredArgsConstructor;

//팔로우 팔로워 비동기통신을 위한 RestController
@RestController
@RequiredArgsConstructor
public class FollowRestController {
	
	private final FollowService followService;
	
	ObjectMapper objmapper = new ObjectMapper();
	
	@PostMapping("/follow/{nickname}")
	public String follow(@PathVariable String nickname) throws JsonProcessingException {
		System.out.println("follow/{nickname} = " + nickname);
		List<Follow> list = followService.followselect(nickname);
		System.out.println("list = " + list);
		return objmapper.writeValueAsString(list);
	}
	
	@PostMapping("/follower/{nickname}")
	public String follower(@PathVariable String nickname) throws JsonProcessingException {
		System.out.println("follow/{nickname} = " + nickname);
		List<Follow> list = followService.followerselect(nickname);
		System.out.println("list = " + list);
		return objmapper.writeValueAsString(list);
	}
	
	@PostMapping("/followdelete")
	@ResponseBody
	public String followerdelete(@RequestBody String name) throws JsonMappingException, JsonProcessingException {
		System.out.println("name = " + name);
		Follow f = objmapper.readValue(name, Follow.class);
		int cnt = followService.followdelete(f);
		int delcnt = followService.followerdelete(f);
		System.out.println("cnt = " + cnt);
		System.out.println("delcnt = " + cnt);
		List<Follow> list = followService.followselect(f.getWsender());
		System.out.println("list = " + list);
		return objmapper.writeValueAsString(list);
	}
	
}
