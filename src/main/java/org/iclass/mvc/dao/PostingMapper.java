package org.iclass.mvc.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.iclass.mvc.vo.Imember;
import org.iclass.mvc.vo.Posting;

@Mapper
public interface PostingMapper {
	
	int insert(Map<String,String>map);
	List<Posting> selectAll();
	List<Posting> myposting(String nickname);
	int delete(int pno);
}
