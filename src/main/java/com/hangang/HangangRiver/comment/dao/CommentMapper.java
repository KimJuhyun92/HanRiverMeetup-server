package com.hangang.HangangRiver.comment.dao;

import java.util.List;

import com.hangang.HangangRiver.comment.model.Comment;

public interface CommentMapper {
	public void insert(Comment comment);
	public void delete(int comment_seq);
	public List<Comment> selectCommentList(int meeting_seq);
}
