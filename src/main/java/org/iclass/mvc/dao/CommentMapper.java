package org.iclass.mvc.dao;

import java.util.List;


import org.apache.ibatis.annotations.Mapper;
import org.iclass.mvc.vo.Comment;
@Mapper
public interface CommentMapper {
    
	// 댓글 개수
    public int commentCount(int cno);
	
    // 댓글 목록
    public List<Comment> commentList(int pno);
    
    // 댓글 작성
    public int commentInsert(Comment comment);
    
    // 댓글 삭제
    public int commentDelete(int cno);

}
