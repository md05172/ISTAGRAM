package org.iclass.mvc.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.iclass.mvc.vo.CheckRoom;
import org.iclass.mvc.vo.MsgRoom;

@Mapper
public interface MsgRoomMapper {
	
	//채팅방 생성
	int createRoom(MsgRoom room);
	
	//채팅방 확인
	CheckRoom checkRoom(Map<String, String> map);
	
	//채팅방 가져오기?
	List<MsgRoom> getRoom(String nickName);
}	
