package org.iclass.mvc.service;
/*
프로젝트 팀명:ISTAGRAM(인스타그램 흉내)
작성자: 김종훈(팀장)
*/
import java.util.List;
import java.util.Map;

import org.iclass.mvc.dao.LikesMapper;
import org.iclass.mvc.vo.Likes;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;


//좋아요 관련 Service
@Service
@RequiredArgsConstructor
public class LikesService {

	private final LikesMapper dao;
	
	public int insert(Likes vo) {
		return dao.insert(vo);
	}
	
	public int selectCount(Map<String, Object> map) {
		return dao.selectCount(map);
	}
	
	public int delete(Map<String, Object> map) {
		return dao.delete(map);
	}
	
	public List<Likes> selectLike(String nickname) {
		return dao.selectLike(nickname);
	}
}
