package com.hangang.HangangRiver.matching.web;

import com.hangang.HangangRiver.matching.model.ContactedMeeting;
import com.hangang.HangangRiver.matching.service.MatchingService;
import com.hangang.HangangRiver.meeting.model.Meeting;
import com.hangang.HangangRiver.meeting.model.MeetingForm;
import com.hangang.HangangRiver.meeting.service.MeetingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping("/match")
public class MatchingController {

	@Autowired
	MatchingService matchingService;

	@PostMapping("/contact")
	private ContactedMeeting saveMeeting(HttpServletRequest request, @RequestBody ContactedMeeting meeting){
		try {
			return matchingService.save(meeting);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@GetMapping("/detail/{contact_seq}")
	private ContactedMeeting contactedMeetingDetail(@PathVariable int contact_seq) throws Exception{
		return matchingService.getContactedMeetingById(contact_seq);
	}

	@PostMapping("/detail")
	private ContactedMeeting contactedMeetingDetailByMachingInfo( HttpServletRequest request,
			@RequestBody ContactedMeeting meeting) throws Exception{
		try {
			return matchingService.getContactedMeetingByMatchingInfo(
					meeting.getMeeting_seq(),
					meeting.getApplication_seq()
			);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@GetMapping("/remove/{contact_seq}")
	private void remove(@PathVariable int contact_seq) throws Exception{
		matchingService.remove(contact_seq);
	}
}
