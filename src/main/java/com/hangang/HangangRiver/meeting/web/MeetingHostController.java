package com.hangang.HangangRiver.meeting.web;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.hangang.HangangRiver.exceptions.DuplicatedMeetingException;
import com.hangang.HangangRiver.exceptions.InvalidMeetingDetailException;
import com.hangang.HangangRiver.meeting.model.JoinDetail;
import com.hangang.HangangRiver.meeting.model.MeetingDetail;
import com.hangang.HangangRiver.meeting.model.MeetingDetailForm;
import com.hangang.HangangRiver.meeting.service.MeetingHostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/host")
public class MeetingHostController {
	@Autowired
	MeetingHostService meetingHostService;

	@PostMapping("/meeting")
	@ResponseBody
	private ResponseEntity<MeetingDetail> createMeeting(HttpServletRequest request, @RequestBody MeetingDetail meetingDetail) throws InvalidMeetingDetailException, DuplicatedMeetingException{
		String exceptions = checkErrorMeetingDetail(meetingDetail);
		if (exceptions !=null){
			throw new InvalidMeetingDetailException(exceptions);
		} else{
			MeetingDetail createdMeetingDetail = meetingHostService.createMeeting(meetingDetail);
			return ResponseEntity.ok().body(createdMeetingDetail);
		}
	}

	@GetMapping("/meeting/{meeting_seq}")
	@ResponseBody
	private ResponseEntity<MeetingDetail> getMeetingDetail(@PathVariable int meeting_seq){
		MeetingDetail meetingDetail = meetingHostService.getMeetingDetailById(meeting_seq);

		if(meetingDetail != null){
			return ResponseEntity.ok().body(meetingDetail);
		}

		return ResponseEntity.badRequest().body(null);
	}

	@PutMapping("/meeting/{meeting_seq}")
	private ResponseEntity<MeetingDetail> modifyMeeting(@PathVariable int meeting_seq, @RequestBody MeetingDetail meetingDetail)throws InvalidMeetingDetailException{
		String exceptions = checkErrorMeetingDetail(meetingDetail);
		if (exceptions !=null){
			throw new InvalidMeetingDetailException(exceptions);
		} else{
			MeetingDetail modifiedMeetingDetail = meetingHostService.modifyMeeting(meeting_seq, meetingDetail);
			return ResponseEntity.ok().body(modifiedMeetingDetail);
		}
	}

	public String checkErrorMeetingDetail (MeetingDetail meetingDetail) {
		String errors = null;
		if (meetingDetail.getTitle() == null){
			errors = "모임의 제목을 입력해주세요.\n";
		}
		if (meetingDetail.getMeeting_location() == null){
			errors += "모임 위치를 선택해주세요.\n";
		}
		if (meetingDetail.getMeeting_time() == null){
			errors += "모임 시간을 선택해주세요.\n";
		}
		if (meetingDetail.getParticipants_cnt() == null){
			errors += "모임 인원을 선택해주세요.\n";
		}
		if (meetingDetail.getExpected_cost() == null){
			errors += "모임 회비를 입력해주세요.\n";
		}
		if (meetingDetail.getContact() == null){
			errors += "모임 연락처를 입력해주세요.\n";
		}
		if (meetingDetail.getDescription() == null){
			errors += "모임 설명을 입력해주세요.\n";
		}
		return errors;
	}

	@DeleteMapping("/meeting/{meeting_seq}")
	private ResponseEntity<Object> removeMeeting(@PathVariable int meeting_seq) throws Exception{
		meetingHostService.removeMeeting(meeting_seq);
		return ResponseEntity.ok().body(null);
	}

	@GetMapping("/meetings/today") //TODO:추후 검색기능 추가 시 searchvalue 파라미터로 넘겨줘야함
	private ResponseEntity<List<MeetingDetailForm>> getMeetingsAtToday(HttpServletRequest request, MeetingDetailForm meetingForm) throws Exception{
		return ResponseEntity.ok().body(meetingHostService.selectTodayMeeting(meetingForm));
	}

	@GetMapping("/meetings/week/{activity_seq}")
	private ResponseEntity<List<MeetingDetailForm>> getMeetingsAtWeek(HttpServletRequest request, @PathVariable int activity_seq) throws Exception{
		return ResponseEntity.ok().body(meetingHostService.selectWeeklyMeetings(activity_seq));
	}

	@GetMapping("/requests/{meeting_seq}")
	private ResponseEntity<List<JoinDetail>> getJoinRequests(@PathVariable int meeting_seq) throws Exception{
		List<JoinDetail> requests = meetingHostService.getJoinDetailsByMeetingId(meeting_seq);
		return ResponseEntity.ok().body(requests);
	}


}
