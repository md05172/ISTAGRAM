package org.iclass.mvc.service;

import java.io.File;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.iclass.mvc.dao.PostingMapper;
import org.iclass.mvc.vo.Posting;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class PostingService {

	private PostingMapper dao;

	public PostingService(PostingMapper dao) {
		this.dao = dao;
	}

	public int insert(Map<String, String> map, Map<String, List<MultipartFile>> maps) { // controller 에서 넘어온 parameter
																						// 값들로 insert + fileUpload
		String path = "C:\\Javaiclass\\springproject\\project\\upload"; // application 이랑 경로 같아야함.
		StringBuilder filenames = new StringBuilder(); // 테이블 컬럼으로 전달될 파일명들

		// 파일업로드
		Posting vo = Posting.builder().hashtag(map.get("hashtag")).content(map.get("content"))
				.nickname(map.get("nickname")).build();
		for (MultipartFile f : maps.get("pics")) {
			if (f.getSize() != 0) {
				String oriName = f.getOriginalFilename();

				// 확장자 변수선언
				String postfix = oriName.substring(oriName.lastIndexOf("."));

				StringBuilder newfile = new StringBuilder("Pfile_") // 파일명시작 이름선언
						.append(UUID.randomUUID().toString().substring(0, 5)) // 파일명 뒷부분 랜덤이름 생성
						.append(postfix); // 확장자 가져오기

				// path 경로에 newfile 새로운 파일명으로 File 객체 생성해서 저장.
				File file = new File(path + "\\" + newfile); // 파일경로+//+파일명

				try {
					f.transferTo(file);
					filenames.append(newfile).append(",");
				} catch (Exception e) {
				}
			}
		}
		vo.setPhotofiles(filenames.toString());
		map.put("photofiles", vo.getPhotofiles());
		return dao.insert(map);
	}

	public List<Posting> selectAll() {
		return dao.selectAll();
	}

	public List<Posting> myposting(String nickname) {
		return dao.myposting(nickname);
	}

	public int delete(int pno) {
		return dao.delete(pno);
	}
}
