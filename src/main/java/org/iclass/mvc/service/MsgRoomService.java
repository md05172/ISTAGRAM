package org.iclass.mvc.service;
/*
프로젝트 팀명:ISTAGRAM(인스타그램 흉내)
작성자: 김종훈(팀장)
*/
import java.util.List;
import java.util.Map;

import org.iclass.mvc.dao.MsgRoomMapper;
import org.iclass.mvc.vo.ChatMessage;
import org.iclass.mvc.vo.CheckRoom;
import org.iclass.mvc.vo.MsgRoom;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

//채팅방 관련 Service
@Service
@RequiredArgsConstructor
public class MsgRoomService {
	
	private final MsgRoomMapper dao;
	
	public int createRoom(MsgRoom msg) {
		return dao.createRoom(msg);
	}
	
	public CheckRoom checkRoom(Map<String, String> map) {
		return dao.checkRoom(map);
	}
	
	public List<MsgRoom> getRoom(String id) {
		return dao.getRoom(id);
	}
}
