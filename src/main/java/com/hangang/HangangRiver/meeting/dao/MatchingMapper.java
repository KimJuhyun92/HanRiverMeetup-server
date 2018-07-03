package com.hangang.HangangRiver.meeting.dao;

import com.hangang.HangangRiver.meeting.model.ContactedMeeting;
import org.apache.ibatis.annotations.Param;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface MatchingMapper {
	void insert(ContactedMeeting meeting);
	ContactedMeeting detail(int contact_seq);
	ContactedMeeting detailByMatchingInfo(@Param("meeting_seq") int meeting_seq,
										  @Param("application_seq") int application_seq);
	void delete(int contact_seq);
}
