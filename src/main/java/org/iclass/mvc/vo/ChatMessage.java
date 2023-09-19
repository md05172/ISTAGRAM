package org.iclass.mvc.vo;

import java.sql.Timestamp;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class ChatMessage {
	private int pk;
    private String msgContent;
    private String msgsendnick;
    private String msgreceivernick;
    private Timestamp msgCreatedAt;
    private int roomno;
    
}