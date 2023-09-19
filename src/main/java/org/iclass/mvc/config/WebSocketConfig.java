package org.iclass.mvc.config;

/*
프로젝트 팀명:ISTAGRAM(인스타그램 흉내)
작성자: 김종훈(팀장)
*/
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;
import org.springframework.web.socket.server.support.HttpSessionHandshakeInterceptor;

//DM (1:1 채팅)을 위한 설정파일
@Configuration
@EnableWebSocket
public class WebSocketConfig implements WebSocketConfigurer{

	@Override
	public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
		registry.addHandler(serviceChatt(), "/ws/chatt")
			.setAllowedOrigins("*")
			.addInterceptors(new HttpSessionHandshakeInterceptor());
			//http의 session을 가져오기 위한 인터셉터를 추가
	}
	
	@Bean
	public ServiceChatt serviceChatt() {
		return new ServiceChatt();
	}

}
