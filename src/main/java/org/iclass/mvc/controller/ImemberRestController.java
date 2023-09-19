package org.iclass.mvc.controller;
/*
프로젝트 팀명:ISTAGRAM(인스타그램 흉내)
작성자: 김종훈(팀장)
*/
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.iclass.mvc.service.ChatService;
import org.iclass.mvc.service.FollowService;
import org.iclass.mvc.service.ImemberService;
import org.iclass.mvc.vo.ChangeImg;
import org.iclass.mvc.vo.ChatMessage;
import org.iclass.mvc.vo.Follow;
import org.iclass.mvc.vo.Imember;
import org.iclass.mvc.vo.Message;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class ImemberRestController {

	private final ImemberService service;
	private final ChatService chatservice;
	private final FollowService followService;

	private ObjectMapper objMapper = new ObjectMapper();

	@GetMapping("/search/{find}")
	public String Search(@PathVariable String find) throws JsonProcessingException {
		List<Imember> list = service.find(find);
		return objMapper.writeValueAsString(list);
	}

	@PostMapping("/getMsg/{nickname}")
	public String getMsg(HttpServletRequest request, @PathVariable String nickname) throws JsonProcessingException {
		Imember member = (Imember) request.getSession().getAttribute("user");
		System.out.println("nickname =" + nickname);
		Map<String, String> map = new HashMap<>();
		map.put("user1", member.getNickname());
		map.put("user2", nickname);
		List<ChatMessage> list = chatservice.allMsg(map);
		System.out.println("list = " + list);
		return objMapper.writeValueAsString(list);
	}
	
	//채팅이 몇개 왔는지 확인 기능
	@PostMapping("/getCnt")
	public String getCnt(@RequestBody String str) throws JsonMappingException, JsonProcessingException {
		System.out.println("getCnt(Post)들어옴" + str);
		ChatMessage msg = objMapper.readValue(str, ChatMessage.class);
		int cnt = chatservice.msgcount(msg.getMsgreceivernick());
		System.out.println("cnt = " + cnt);
		return objMapper.writeValueAsString(cnt);
	}
	
	
	//DM(1:1채팅)누른 유저 확인과 그 유저가 안읽은 채팅 수와 읽음처리 기능
	@PostMapping("/updateMsg")
	public String updateMsg(@RequestBody String str) throws JsonMappingException, JsonProcessingException {
		System.out.println("updateMsg(str) = " + str);
		ChatMessage msg = objMapper.readValue(str, ChatMessage.class);
		System.out.println("msg.getMsgreceivernick() = " + msg.getMsgreceivernick());
		int scnt = chatservice.msgcount(msg.getMsgreceivernick());
		int ucnt = chatservice.updateMsg(msg.getMsgreceivernick());
		Map<String, Integer> map = new HashMap<>();
		map.put("scnt", scnt);
		map.put("ucnt", ucnt);
		return objMapper.writeValueAsString(map);
	}

	@PutMapping("/userUpdate/{email}")
	public String update(@PathVariable String email, @RequestBody String imember) throws JsonProcessingException {
		Imember vo = objMapper.readValue(imember, Imember.class);
		int count = service.update(vo);
		Map<String, Object> result = new HashMap<>();
		result.put("count", count);
		return objMapper.writeValueAsString(result);
	}

	@GetMapping("/userUpdate/{email}")
	public String imember(@PathVariable String email) throws JsonProcessingException {
		Imember vo = service.member(email);
		Map<String, Object> result = new HashMap<>();
		String isOk = "fail";
		if (vo != null) { // 검색 결과 없을 때
			isOk = "success";
			result.put("imember", vo);
		}
		result.put("isOk", isOk);
		return objMapper.writeValueAsString(result);
	}
	
	// 팔로우 갯수
	@PostMapping("/getfcnt/{nickname}")
	public String getfcnt(@PathVariable String nickname) throws JsonProcessingException {
		System.out.println("nickname = " + nickname);
		List<Follow> list = followService.followerselectcheck(nickname);
		System.out.println("====================================");
		System.out.println("list = " + list);
		System.out.println("====================================");
		return objMapper.writeValueAsString(list);
	}
	
	// 알람 갯수 초기화
	@PostMapping("/fCntClear/{nickname}")
	public String fCntClear(@PathVariable String nickname) throws JsonProcessingException {
		followService.followerupdate(nickname);
		List<Follow> list = followService.followerselect(nickname);
		return objMapper.writeValueAsString(list);
	}
	
	//유저 이미지 사진 바꾸기
	@PostMapping("/changeImg")
	public String changeImg(@RequestParam("input") MultipartFile file, @RequestParam("user") String user)
			throws IllegalStateException, IOException {
		System.out.println("input = " + file);
		System.out.println("오리지널이름 = " + file.getOriginalFilename());
		System.out.println("user = " + user);
		String uuid = UUID.randomUUID().toString();
		String originalFileName = file.getOriginalFilename();
		String fileName = uuid + "_" + originalFileName;
		File save = new File(
				"C:\\Javaiclass\\springproject\\project\\src\\main\\resources\\static\\images\\" + fileName);
		// 파일 저장
		file.transferTo(save);
		ChangeImg ci = new ChangeImg();
		ci.setSrc(fileName);
		ci.setUser(user);
		int cnt = service.changeImg(ci);
		System.out.println("cnt ==== " + cnt);
		return "성공";
	}
	
	// 닉네임 중복확인
	@GetMapping("/api/nickCheck/{nick}")
	public String nickCheck(@PathVariable String nick) throws JsonProcessingException {
		int cnt = service.nickCheck(nick);
		System.out.println("cnt " + cnt);
		return objMapper.writeValueAsString(cnt);
	}

	@PutMapping("/update/{email}")
	public String updatepw(@PathVariable String email, @RequestBody String imember) throws JsonProcessingException {
		Imember vo = objMapper.readValue(imember, Imember.class);
		int result = service.updatepw(vo);
		return objMapper.writeValueAsString(result);
	}
}
