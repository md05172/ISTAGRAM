package org.iclass.mvc.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.iclass.mvc.service.FollowService;
import org.iclass.mvc.service.ImemberService;
import org.iclass.mvc.service.PostingService;
import org.iclass.mvc.vo.Follow;
import org.iclass.mvc.vo.Imember;
import org.iclass.mvc.vo.Posting;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.multipart.MultipartFile;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class PostingController {

	@Autowired
	private PostingService service;
	
	private final FollowService followService;
	private final ImemberService memberservice;
	
	@GetMapping("/posting")
	public void posting() {}
	
	@PostMapping("/save")
	public String save(Posting p, String nickname) {
		
		// pics : 파일명들을 불러올 맵 새로 선언
		Map<String, List<MultipartFile>> maps = new HashMap<>();
		maps.put("pics", p.getPics());
		
		// posting 객체를 담을 map 선언
		Map<String, String> map = new HashMap<>();
		map.put("photofiles", p.getPhotofiles());
		map.put("hashtag", p.getHashtag());
		map.put("content", p.getContent());
		map.put("nickname", nickname);
		
		int result = service.insert(map, maps);
		return "redirect:/main";
	}
	
	@GetMapping("/myposting")
	public String myposting(HttpServletRequest request, String nickname, Model model) {
		Imember vo = null;
		if(nickname == null) {
			vo = (Imember)request.getSession().getAttribute("user");
			
			model.addAttribute("followCnt", followService.followcount(vo.getNickname()));
			System.out.println("followCnt ============= " + followService.followcount(vo.getNickname()));
			model.addAttribute("followerCnt", followService.followercount(vo.getNickname()));
			System.out.println("followerCnt ============ " + followService.followercount(vo.getNickname()));
			model.addAttribute("me", memberservice.selectBynickName(vo.getNickname()));
			model.addAttribute("mylist",service.myposting(vo.getNickname()));
		} else {
			vo = (Imember)request.getSession().getAttribute("user");
			Map<String, String> map = new HashMap<>();
			map.put("wsender", vo.getNickname());
			map.put("wreceiver", nickname);
			Follow fw = followService.followfind(map);
			System.out.println("fw = " + fw);
			model.addAttribute("follow", followService.followfind(map));
			model.addAttribute("followCnt", followService.followcount(nickname));
			System.out.println("(else)followCnt ========= " + followService.followcount(vo.getNickname()));
			model.addAttribute("followerCnt", followService.followercount(nickname));
			System.out.println("(else)followerCnt ======== " + followService.followercount(vo.getNickname()));
			model.addAttribute("me", memberservice.selectBynickName(nickname));
			model.addAttribute("mylist",service.myposting(nickname));
		}
		return "myposting";
	}
}
