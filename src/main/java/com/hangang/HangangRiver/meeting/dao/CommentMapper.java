package com.hangang.HangangRiver.meeting.dao;

import java.util.List;

import com.hangang.HangangRiver.meeting.model.Comment;

public interface CommentMapper {
	void insert(Comment comment);
	void delete(int comment_seq);
	List<Comment> selectCommentList(int meeting_seq);
}
