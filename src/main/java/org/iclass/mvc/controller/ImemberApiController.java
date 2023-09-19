package org.iclass.mvc.controller;

import java.util.HashMap;
import java.util.Map;

import org.iclass.mvc.service.ImemberService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class ImemberApiController {

   private ObjectMapper objMapper = new ObjectMapper();
   private final ImemberService service;

   @GetMapping("/api/idCheck/{email}")
   public String valid(@PathVariable String email) throws JsonProcessingException {
       int count = service.idCheck(email);
      
      Map<String, Object> map = new HashMap<>();
      map.put("count", count);

      return objMapper.writeValueAsString(map);
   }
   
   
}