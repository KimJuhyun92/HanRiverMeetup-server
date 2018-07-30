package com.hangang.HangangRiver.meeting.service;

import com.hangang.HangangRiver.exceptions.InvalidMatchingInfoException;
import com.hangang.HangangRiver.meeting.model.ContactedMeeting;
import com.hangang.HangangRiver.meeting.model.JoinDetail;
import com.hangang.HangangRiver.meeting.model.MeetingDetail;

import org.json.simple.JSONObject;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MyPageService extends MeetingBaseService{
    public List<MeetingDetail> getHopeMeetings(String userID) {
        List<MeetingDetail> hopeMeetingList = new ArrayList<>();

        // 1. 먼저 내 아이디에 해당하는 모든 join 리스트를 다 가지고 옴
        List<JoinDetail> joinRequests = joinDetailMapper.getJoinDetailsByUserId(userID);

        // 2. for 문을 돌면서 매칭된 모임에 포함될 경우, join List 에서 제거
        joinRequests.forEach((request)->{
            ContactedMeeting contactedMeeting = matchingMapper.detailByMatchingInfo(
                    request.getMeeting_seq(),
                    request.getApplication_seq());

            if(contactedMeeting == null) {
                // 3. 남은 join 리스트들의 meeting seq를 가지고 meeting detail List를 만들어줌
                MeetingDetail meetingDetail = meetingDetailMapper.detail(request.getMeeting_seq());

                // 4. 이미 다른 사람과 매칭되어 있는지 확인
                ContactedMeeting otherContactedMeeting = matchingMapper.detailByMeetingSeq(request.getMeeting_seq());

                if(otherContactedMeeting != null) {
                    meetingDetail.setContact_seq(otherContactedMeeting.getContact_seq());
                }

                hopeMeetingList.add(meetingDetail);
            }
        });

        return hopeMeetingList;
    }

    public List<JSONObject> getMyMatchings(String user_id) throws InvalidMatchingInfoException{
    	List<ContactedMeeting> contactedMeetingList = matchingMapper.selectMyMatchings(user_id);
    	List<JSONObject> matchingInfoList = new ArrayList<JSONObject>();

    	if(contactedMeetingList.isEmpty()) {
    		return matchingInfoList;
    	}

    	contactedMeetingList.forEach((contactedMeeting)->{
    		int meeting_seq = contactedMeeting.getMeeting_seq();
    		int application_seq = contactedMeeting.getApplication_seq();

    		MeetingDetail meetingDetail = meetingDetailMapper.detail(meeting_seq);
    		JoinDetail joinDetail = joinDetailMapper.getJoinDetail(application_seq);

    		JSONObject matchingInfo = new JSONObject();
			matchingInfo.put("meeting_detail", meetingDetail);
			matchingInfo.put("join_detail", joinDetail);
    		matchingInfoList.add(matchingInfo);
    	});
		return matchingInfoList;
    }

    public List<MeetingDetail> getMyMeetings(String user_id){
        return meetingDetailMapper.selectMyMeetings(user_id);
    }

}
