package org.iclass.mvc.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.iclass.mvc.vo.Likes;

@Mapper
public interface LikesMapper {

	int insert(Likes vo);
	int selectCount(Map<String, Object> map);
	int delete(Map<String, Object> map);
	List<Likes> selectLike(String nickname);
}
