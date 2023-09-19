package org.iclass.mvc.vo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Follow {
	private String wsender;
	private String wreceiver;
	private String msg;
	private String wcheck;
	
}
