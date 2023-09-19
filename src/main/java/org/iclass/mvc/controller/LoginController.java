package org.iclass.mvc.controller;

/*
프로젝트 팀명:ISTAGRAM(인스타그램 흉내)
작성자: 김종훈(팀장)
*/
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.iclass.mvc.service.ImemberService;
import org.iclass.mvc.vo.Imember;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import lombok.RequiredArgsConstructor;

//로그인 성공과 실패에 따른 url설정 파일
@Controller
@RequiredArgsConstructor
public class LoginController {
	private final ImemberService service;

	// 로그인 화면
	@GetMapping("/login") // GET 요청일때 @GetMapping
	public String login() {
		// url과 리턴되는 view 파일이름이 같으면 생략가능합니다.
		return "member/login";
	}

	// id,password 검사해서 alert 또는 화면에 메시지 출력
	@PostMapping("/login") // POST 요청일때 @PostMapping
//   public String loginProc(String id, String password,RedirectAttributes reattr){
	public String loginProc(@RequestParam Map<String, String> param, RedirectAttributes reattr, HttpSession session) {
		System.out.println("로그인처리 들어옴");
		String message = "로그인 계정 정보가 다릅니다.";
		String url = "/"; // 계정정보가 일치할때 index로 이동
//      if(service.login(id, password)==null) {   //계정 정보가 틀릴때
		Imember member = service.login(param);
		if (member == null) { // 계정 정보가 틀릴때
			System.out.println("1");
			reattr.addFlashAttribute("alert", message);
			url = "/";
		} else {
			System.out.println("2");
			session.setAttribute("user", member);
			url = "main";
		}
		System.out.println("3");
		return "redirect:" + url;
	}

	@GetMapping("logout")
	public String logout(HttpSession session) {
		session.invalidate();
		return "member/logout";
	}

}