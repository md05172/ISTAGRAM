package org.iclass.mvc.service;
/*
프로젝트 팀명:ISTAGRAM(인스타그램 흉내)
작성자: 김종훈(팀장)
*/
import java.util.List;
import java.util.Map;

import org.iclass.mvc.dao.FollowMapper;
import org.iclass.mvc.vo.Follow;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

//팔로우관련 Service
@Service
@RequiredArgsConstructor
public class FollowService {
	
	@Autowired
	private final FollowMapper followmapper;
	
	public int followInsert(Follow follow) {
		return followmapper.followInsert(follow);
	}
	
	public List<Follow> followselect(String receiver){
		return followmapper.followselect(receiver);
	}
	
	public List<Follow> followerselect(String receiver){
		return followmapper.followerselect(receiver);
	}
	
	public List<Follow> followselectcheck(String receiver){
		return followmapper.followselectcheck(receiver);
	}
	
	public List<Follow> followerselectcheck(String nickname){
		return followmapper.followerselectcheck(nickname);
	}
	
	public Follow followfind(Map<String, String> map) {
		return followmapper.followfind(map);
	}
	
	//팔로우 개수
	public int followcount(String nickname) {
		return followmapper.followcount(nickname);
	}
	//팔로워 개수
	public int followercount(String nickname) {
		return followmapper.followercount(nickname);
	}
	
	//팔로워 insert
	public int followerInsert(Follow follow) {
		return followmapper.followerInsert(follow);
	}
	
	public int followdelete(Follow follow) {
		return followmapper.followdelete(follow);
	}
	
	public int followerdelete(Follow follow) {
		return followmapper.followerdelete(follow);
	}
	
	public int followerupdate(String nickname) {
		return followmapper.followerupdate(nickname);
	}
	
}
