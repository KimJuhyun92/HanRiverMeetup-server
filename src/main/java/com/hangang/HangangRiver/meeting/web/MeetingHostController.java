package com.hangang.HangangRiver.meeting.web;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.hangang.HangangRiver.meeting.model.MeetingDetail;
import com.hangang.HangangRiver.meeting.model.MeetingDetailForm;
import com.hangang.HangangRiver.meeting.service.MeetingHostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/host")
public class MeetingHostController {

	@Autowired
	MeetingHostService meetingHostService;

	@PostMapping("/meeting/{meeting_seq}")
	private void createMeeting(HttpServletRequest request, @RequestBody MeetingDetail meetingDetail){
		try {
			meetingHostService.createMeeting(meetingDetail);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@GetMapping("/meeting/{meeting_seq}")
	private MeetingDetail getMeetingDetail(@PathVariable int meeting_seq) throws Exception{
		return meetingHostService.getMeetingDetailById(meeting_seq);
	}

	@PutMapping("/meeting/{meeting_seq}")
	private void modifyMeeting(@PathVariable int meeting_seq, @RequestBody MeetingDetail meetingDetail){
		try {
			meetingHostService.modifyMeeting(meeting_seq, meetingDetail);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@DeleteMapping("/meeting/{meeting_seq}")
	private void removeMeeting(@PathVariable int meeting_seq) throws Exception{
		meetingHostService.removeMeeting(meeting_seq);
	}

	@GetMapping("/meetings/today") //TODO:추후 검색기능 추가 시 searchvalue 파라미터로 넘겨줘야함
	private List<MeetingDetailForm> getMeetingsAtToday(HttpServletRequest request, MeetingDetailForm meetingForm) throws Exception{
		return meetingHostService.selectTodayMeeting(meetingForm);
	}
}
