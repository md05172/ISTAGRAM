package org.iclass.mvc.controller;

/*
프로젝트 팀명:ISTAGRAM(인스타그램 흉내)
작성자: 김종훈(팀장)
*/
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.iclass.mvc.service.ChatService;
import org.iclass.mvc.service.ImemberService;
import org.iclass.mvc.service.MsgRoomService;
import org.iclass.mvc.vo.Imember;
import org.iclass.mvc.vo.MsgRoom;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.RequiredArgsConstructor;

//DM (1:1채팅) Controller
@Controller
@RequiredArgsConstructor
public class ChatController {
	
	private final ChatService service;
	private final ImemberService memberservice;
	private final MsgRoomService roomservice;
	ObjectMapper mapper = new ObjectMapper();
	
	@GetMapping("/chat")
	public String chat(HttpServletRequest request, Model model) {
		Imember member = (Imember)request.getSession().getAttribute("user");
		List<MsgRoom> roomList = roomservice.getRoom(member.getNickname());
		System.out.println("roomList = " + roomList);
		model.addAttribute("roomList", roomList);
		return "chat";
	}
}
