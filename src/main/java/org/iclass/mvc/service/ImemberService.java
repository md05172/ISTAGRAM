package org.iclass.mvc.service;

import java.util.List;
import java.util.Map;

import org.iclass.mvc.dao.ImemberMapper;
import org.iclass.mvc.vo.ChangeImg;
import org.iclass.mvc.vo.Imember;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class ImemberService {

	@Autowired
	private ImemberMapper dao;

	public int insert(Imember vo) {
		return dao.insert(vo);
	}

	public Imember login(Map<String, String> map) {
		return dao.login(map);
	}
	
	// 회원 검색
	public List<Imember> find(String find) {
		return dao.find(find);
	}

	// 아이디 중복 체크
	public int idCheck(String email) { // id중복체크
		return dao.idCheck(email);
	}

	public int update(Imember vo) {
		return dao.update(vo);
	}

	public Imember member(String email) {
		return dao.selectOne(email);
	}

	public Imember selectBynickName(String nickname) {
		return dao.selectBynickName(nickname);
	}

	public List<Imember> notifi(String notifi) {
		return dao.notifi(notifi);
	}
	
	// 이미지 바꾸기
	public int changeImg(ChangeImg ci) {
		return dao.changeImg(ci);
	}
	
	// 닉네임 중복확인
	public int nickCheck(String nick) {
      return dao.nickCheck(nick);
   }
	
	// 비밀번호 바꾸기
	public int updatepw(Imember vo) {
		return dao.updatepw(vo);
	}
}
