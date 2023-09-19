package org.iclass.mvc.service;

import java.util.List;

import org.iclass.mvc.dao.CommentMapper;
import org.iclass.mvc.vo.Comment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CommentService {

    private final CommentMapper mapper;

    public List<Comment> getCommentList(int pno) {
        return mapper.commentList(pno);
    }

    public int addComment(Comment comments) {
        return mapper.commentInsert(comments);
    }

    public int deleteComment(int cno) {
        return mapper.commentDelete(cno);
    }
}
