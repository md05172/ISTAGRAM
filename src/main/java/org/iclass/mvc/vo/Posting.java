package org.iclass.mvc.vo;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter@Setter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Posting {

	private int pno;
	private String photofiles;
	private String hashtag;
	private String content;
	private int hearts;
	private String nickname;
	private int cno;
	
	// 테이블 컬럼x 파일 업로드를 위한 선언
	private List<MultipartFile> pics;		// html에 pics
}
