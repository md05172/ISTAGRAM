package org.iclass.mvc.service;
/*
프로젝트 팀명:ISTAGRAM(인스타그램 흉내)
작성자: 김종훈(팀장)
*/
import java.util.List;
import java.util.Map;

import org.iclass.mvc.dao.ChatMapper;
import org.iclass.mvc.vo.ChatMessage;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

// DM(1:1채팅) Service
@Service
@RequiredArgsConstructor
public class ChatService {
	
	private final ChatMapper dao; 
	
	public int msgInsert(ChatMessage msg) {
		return dao.msgInsert(msg);
	}
	
	public int msgDelete(Map<String, Object> map) {
		return dao.msgDelete(map);
	}
	
	public int msgRoomUpdate(Map<String, Object> map) {
		return dao.msgRoomUpdate(map);
	}
	
	public List<ChatMessage> allMsg(Map<String, String> map){
		return dao.allMsg(map);
	}
	
	public int msgcount(String receivernick) {
		System.out.println("receivernick = " + receivernick);
		return dao.msgcount(receivernick);
	}
	
	public int updateMsg(String msgreceivernick) {
		return dao.updateMsg(msgreceivernick);
	}
}
