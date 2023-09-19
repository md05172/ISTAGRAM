package org.iclass.mvc.controller;

import org.iclass.mvc.service.PostingService;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class PostingRestController {

	private final PostingService service;
	private ObjectMapper objmapper = new ObjectMapper();
	
	@DeleteMapping("/delete/{pno}")
	public String PostingDelete(@PathVariable int pno) throws JsonProcessingException {
		int result = service.delete(pno);
		return objmapper.writeValueAsString(result);
	}
}
