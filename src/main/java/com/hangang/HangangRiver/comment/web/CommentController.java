package com.hangang.HangangRiver.comment.web;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hangang.HangangRiver.comment.model.Comment;
import com.hangang.HangangRiver.comment.service.CommentService;

@RestController
@RequestMapping("/comment")
public class CommentController {

	@Autowired
	CommentService commentService;

	@PostMapping("/save")
	private void saveComment(HttpServletRequest request, @RequestBody Comment comment){
		try {
			commentService.save(comment);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@GetMapping("/remove/{comment_seq}") 
	private void remove(@PathVariable int comment_seq) throws Exception{
		commentService.remove(comment_seq);
	}

	@GetMapping("/getComment/{meeting_seq}") 
	private List<Comment> getComment(@PathVariable int meeting_seq) throws Exception{
		return commentService.getCommentByMeeting(meeting_seq);
	}
}
