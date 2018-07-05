package com.hangang.HangangRiver.meeting.dao;

import java.util.List;

import com.hangang.HangangRiver.meeting.model.Comment;

public interface CommentMapper {
	public void insert(Comment comment);
	public void delete(int comment_seq);
	public List<Comment> selectCommentList(int meeting_seq);
}
