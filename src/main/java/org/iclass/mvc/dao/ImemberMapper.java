package org.iclass.mvc.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.iclass.mvc.vo.ChangeImg;
import org.iclass.mvc.vo.Imember;

@Mapper
public interface ImemberMapper {

	// 성윤 회원가입
	int insert(Imember vo);

	// 성윤 로그인
	Imember login(Map<String, String> map);

	// 성윤 아이디 중복 체크
	public int idCheck(String email); // id중복체크

	// 닉네임으로 회원조회
	Imember selectBynickName(String nickname);

	List<Imember> find(String find);

	// 완기형 회원수정
	int update(Imember imember);

	// email로 회원조회
	Imember selectOne(String email);

	List<Imember> notifi(String notifi);

	// 이미지 바꾸기
	int changeImg(ChangeImg ci);
	
	int nickCheck(String nick);
	
	int updatepw(Imember vo);
}
