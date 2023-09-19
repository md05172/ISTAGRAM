package org.iclass.mvc.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MsgRoom {
	
	private int roomno;
	private String user1;
	private String user2;
}
