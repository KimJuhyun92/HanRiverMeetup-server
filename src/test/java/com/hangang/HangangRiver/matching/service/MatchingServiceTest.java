package com.hangang.HangangRiver.matching.service;

import org.junit.Test;
import org.junit.runner.RunWith;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MatchingServiceTest {
    @Autowired
    MatchingService matchingService;

    @Test
    public void save() {
        // TODO:  테스트케이스작성을 하기 위해선 mocking이 필요함
        //ContactedMeeting meetingInfo = new ContactedMeeting(1,2, 3,  new Date());
        //Assert.assertEquals(matchingService.save(meetingInfo), meetingInfo);
    }

    @Test
    public void getContactedMeeting() {
    }
}