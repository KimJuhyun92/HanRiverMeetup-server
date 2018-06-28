package com.hangang.HangangRiver.application.web;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hangang.HangangRiver.application.model.Application;
import com.hangang.HangangRiver.application.service.ApplicationService;
import com.hangang.HangangRiver.comment.model.Comment;
import com.hangang.HangangRiver.comment.service.CommentService;

@RestController
@RequestMapping("/application")
public class ApplicationController {

	@Autowired
	ApplicationService applicationService;

	@PostMapping("/save")
	private void saveApplication(HttpServletRequest request, @RequestBody Application application){
		try {
			applicationService.save(application);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@GetMapping("/remove/{application_seq}") 
	private void remove(@PathVariable int application_seq) throws Exception{
		applicationService.remove(application_seq);
	}

	@GetMapping("/getApplications/{meeting_seq}") 
	private List<Application> getApplications(@PathVariable int meeting_seq) throws Exception{
		return applicationService.getApplicationsByMeetingId(meeting_seq);
	}
}
