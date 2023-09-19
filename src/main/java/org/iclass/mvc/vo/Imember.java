package org.iclass.mvc.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Imember {

	private int mno;
	private String email;
	private String nickname;
	private String pw;
	private String repw;
	private String info;
	private String mphotofiles;
}
