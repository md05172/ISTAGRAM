package org.iclass.mvc.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.iclass.mvc.service.FollowService;
import org.iclass.mvc.service.ImemberService;
import org.iclass.mvc.service.LikesService;
import org.iclass.mvc.service.PostingService;
import org.iclass.mvc.vo.Follow;
import org.iclass.mvc.vo.Imember;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class ImemberController {

	private final LikesService lservice;
	private final PostingService pservice;
	private final ImemberService service;

	@GetMapping("/")
	public String index() {
		return "member/login";
	}

	@GetMapping("/main")
	public String imember(HttpServletRequest request, Model model) {
		model.addAttribute("list", pservice.selectAll());
		Imember vo = (Imember) request.getSession().getAttribute("user");
		model.addAttribute("likes", lservice.selectLike(vo.getNickname()));
		return "main";
	}

	@GetMapping("/search")
	public String search() {
		return "search";
	}

	@GetMapping("/nav")
	public void nav() {

	}

	@GetMapping("/join")
	public String join() {
		return "member/join"; // join.jsp
	}

	@PostMapping("/join")
	public String insert(Imember vo) {
		System.out.println("들어옴");
		service.insert(vo);
		return "member/login";
	}

	@GetMapping("/userUpdate")
	public String userUpdate() {
		return "member/userUpdate";
	}

	@GetMapping("/userUpdate2")
	public String userUpdate2() {
		return "member/userUpdate2";
	}

	@GetMapping("/set")
	public String set() {
		return "member/set";
	}

}
