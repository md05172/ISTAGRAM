package org.iclass.mvc.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.iclass.mvc.vo.Follow;

@Mapper
public interface FollowMapper {
	
	//팔로우 insert
	public int followInsert(Follow follow);
	
	//나한테 팔로우한 사람들 가져오기
	public List<Follow> followselect(String receiver);
	
	//내가 팔로우한 사람들 가져오기
	public List<Follow> followerselect (String receiver);
	
	//팔로우 보지않았을때 개수?
	public List<Follow> followselectcheck(String receiver);
	
	public List<Follow> followerselectcheck(String nickname);
	
	//내가 팔로우 했는지 안했는지
	public Follow followfind(Map<String, String> map);
	
	//팔로우 개수
	public int followcount(String nickname);
	
	//팔로워 개수
	public int followercount(String nickname);
	
	//팔로워 insert
	public int followerInsert(Follow follow);
	
	//팔로우 삭제
	public int followdelete(Follow follow);
	
	//팔로워 삭제
	public int followerdelete(Follow follow);
	
	public int followerupdate(String nickname);
}
