package org.iclass.mvc.config;

/*
팀프로젝트 팀명: ISTAGRAM
작성자: 김종훈(팀장)
*/
import java.util.HashMap;
import java.util.Map;

import org.iclass.mvc.service.ChatService;
import org.iclass.mvc.service.MsgRoomService;
import org.iclass.mvc.vo.ChatMessage;
import org.iclass.mvc.vo.CheckRoom;
import org.iclass.mvc.vo.Imember;
import org.iclass.mvc.vo.MsgRoom;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.extern.slf4j.Slf4j;

//인스타 DM(1:1채팅)
@Slf4j
public class ServiceChatt extends TextWebSocketHandler {

	private ChatService service;
	private MsgRoomService roomservice;

	@Autowired
	public void SetService(ChatService service) {
		this.service = service;
	}

	@Autowired
	public void SetRoomService(MsgRoomService roomservice) {
		this.roomservice = roomservice;
	}

	ObjectMapper objMapper = new ObjectMapper();

	// 1. 새로운 클라이언트와 소켓이 연결되면 모든 접속자들에게 'id ... 님이 접속하였습니다. ' 라고 보낸다. 
//	private List<WebSocketSession> wsslist = new ArrayList<>();				//1-a.접속자들의 세션을 저장할 리스트

	// 2-d. 지정된 사용자에게만 1:1 메시지를 보내기 위해 계정id와 소켓세션 값을 Map에 저장합니다.
	private Map<String, WebSocketSession> wsmap = new HashMap<>();

	@Override
	public void afterConnectionEstablished(WebSocketSession session) throws Exception {
		log.info("새로운 클라이언트 : {}", session); // 지금까지 세션은 http프로토콜 세션, 전달받는 세션은 소켓세션
		Imember sessionId = (Imember) session.getAttributes().get("user");
		if (!wsmap.containsKey(sessionId.getNickname())) {
			System.out.println("id없어서 들어옴");
			wsmap.put(sessionId.getNickname(), session);
		} else {
			System.out.println("이미 들어있는 사람입니다");
		}
//		wsslist.add(session);
//		System.out.println(session.toString());
	}

	@Override
	public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
		Imember closeId = (Imember) session.getAttributes().get("user");
		log.info("접속 해제 클라이언트 : {}", session);
		System.out.println("closeId = " + closeId.getNickname());
		wsmap.remove(closeId.getNickname());
//		wsslist.remove(session);	//1-f. 접속이 해제되면 wsslist 에서 삭제
	}

	@Override
	protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
		log.info("데이터를 보낸 클라이언트 : {}", session);
		log.info("받은 메시지 : {}", message.getPayload());
		
		String content = message.getPayload();
		if (content.startsWith("msg")) {
			String data = content.substring(4);
			System.out.println("data = " + data);
			
			ChatMessage msg = objMapper.readValue(data, ChatMessage.class);
			if (msg.getMsgsendnick() != null && msg.getMsgreceivernick() != null) {
				service.msgInsert(msg); //채팅 DB에 저장
				Map<String, String> checkmap = new HashMap<>();
				checkmap.put("user1", msg.getMsgsendnick());
				checkmap.put("user2", msg.getMsgreceivernick());
				CheckRoom checkroom = roomservice.checkRoom(checkmap);
				if (checkroom != null) { // 방이 있다면 기존방 사용
					Map<String, Object> map = new HashMap<>();
					map.put("roomno", checkroom.getRoomno());
					map.put("msgsendnick", msg.getMsgsendnick());
					map.put("msgreceivernick", msg.getMsgreceivernick());
					MsgRoom msgroom = new MsgRoom();
					msgroom.setUser1(msg.getMsgsendnick());
					msgroom.setUser1(msg.getMsgreceivernick());
					service.msgRoomUpdate(map); //message 테이블에 roomno 기존에 있는 방번호로 update
				} else { // 만약 방이 없다면 새로운 방을 만든다
					MsgRoom msgroom = new MsgRoom();
					msgroom.setUser1(msg.getMsgsendnick());
					msgroom.setUser2(msg.getMsgreceivernick());
					roomservice.createRoom(msgroom); //채팅방 생성
					Map<String, Object> map = new HashMap<>();
					map.put("roomno", msgroom.getRoomno());
					map.put("msgsendnick", msg.getMsgsendnick());
					map.put("msgreceivernick", msg.getMsgreceivernick());
					service.msgRoomUpdate(map); //message 테이블에 roomno 기존에 있는 방번호로 update
				}
			} else {
				System.out.println("보내는사람 또는 받는사람이 null임");
			}
			
			WebSocketSession target = wsmap.get(msg.getMsgreceivernick());
			target.sendMessage(new TextMessage(objMapper.writeValueAsBytes(msg)));
		}
	}

}
