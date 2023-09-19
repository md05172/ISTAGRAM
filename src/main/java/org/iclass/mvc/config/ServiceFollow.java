package org.iclass.mvc.config;
/*
팀프로젝트 팀명: ISTAGRAM
작성자: 김종훈(팀장)
*/
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.iclass.mvc.service.FollowService;
import org.iclass.mvc.vo.Follow;
import org.iclass.mvc.vo.Imember;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import com.fasterxml.jackson.databind.ObjectMapper;

//팔로우 (친구추가)
public class ServiceFollow extends TextWebSocketHandler{
	
	private FollowService fservice;
	ObjectMapper objMapper = new ObjectMapper();
	
	@Autowired
	public void SetServiceFollow(FollowService fservice) {
		this.fservice = fservice;
	}
	
	private Map<String, WebSocketSession> followmap = new HashMap<>();
	
	@Override
	public void afterConnectionEstablished(WebSocketSession session) throws Exception {
		Imember sessionId = (Imember) session.getAttributes().get("user");
		if (!followmap.containsKey(sessionId.getNickname())) {
			System.out.println("follow 없어서 들어옴");
			followmap.put(sessionId.getNickname(), session);
			System.out.println("followmap = " + followmap);
		} else {
			System.out.println("follow 이미 들어있는 사람입니다");
		}
	}
	
	@Override
	public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
		Imember closeId = (Imember) session.getAttributes().get("user");
		System.out.println("closeId = " + closeId.getNickname());
		followmap.remove(closeId.getNickname());
	}
	
	@Override
	protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
		System.out.println("보낸사람 = " + session);
		System.out.println("받은값 = " + message.getPayload());
		
		Follow f = objMapper.readValue(message.getPayload(), Follow.class);
		System.out.println("f = " + f);
		String msg = f.getWsender() + "님이 팔로우 요청하셨습니다";
		System.out.println("msg = " + msg);
		f.setMsg(msg);
		//팔로우insert
		int cnt = fservice.followInsert(f);
		System.out.println("cnt = " + cnt);
		//팔로워 insert
		int cnt2 = fservice.followerInsert(f);
		System.out.println("cnt2 = " + cnt2);
		List<Follow> list = fservice.followselectcheck(f.getWreceiver());
		System.out.println("list = " + list);
		
		WebSocketSession target = followmap.get(f.getWreceiver());
		System.out.println("target = " + target);
		target.sendMessage(new TextMessage(objMapper.writeValueAsBytes(list)));
	}
	
}
