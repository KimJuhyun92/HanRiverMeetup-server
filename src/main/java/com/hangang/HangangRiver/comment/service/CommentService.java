package com.hangang.HangangRiver.comment.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hangang.HangangRiver.comment.dao.CommentMapper;
import com.hangang.HangangRiver.comment.model.Comment;

@Service
public class CommentService {

	@Autowired
	CommentMapper commentMapper;

	public void save(Comment comment){
		commentMapper.insert(comment);
	}

	public List<Comment> getCommentByMeeting(int meeting_seq){
		return commentMapper.selectCommentList(meeting_seq);
	}

	public void remove(int comment_seq){
		commentMapper.delete(comment_seq);
	}

}
