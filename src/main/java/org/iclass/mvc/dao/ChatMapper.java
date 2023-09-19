package org.iclass.mvc.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.iclass.mvc.vo.ChatMessage;

@Mapper
public interface ChatMapper {
	
	//채팅 insert
	int msgInsert(ChatMessage msg);
	
	//채팅 delete
	int msgDelete(Map<String, Object> map);
	
	//채팅방 update
	int msgRoomUpdate(Map<String, Object> map);
	
	//저장된 채팅내역 가져오기
	List<ChatMessage> allMsg(Map<String, String> map);
	
	//채팅 안읽은 개수
	public int msgcount(String receivernick);
	
	//채팅 읽음 처리
	public int updateMsg(String msgreceivernick);
}
