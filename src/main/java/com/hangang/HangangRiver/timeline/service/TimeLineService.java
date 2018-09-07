package com.hangang.HangangRiver.timeline.service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hangang.HangangRiver.timeline.dao.TimeLineMapper;
import com.hangang.HangangRiver.timeline.model.TimeLine;
import com.hangang.HangangRiver.timeline.model.TimeLineForm;

@Service
public class TimeLineService {

    @Autowired
    public TimeLineMapper timeLineMapper;

    public TimeLine createTimeLine(TimeLine timeLine) {
    	timeLineMapper.insert(timeLine);
    	return timeLine;
    }

    public void removeTimeLine(int timeLine_seq){
    	timeLineMapper.delete(timeLine_seq);
    }

    public List<TimeLineForm> selectTimeLine(TimeLineForm timeLineForm){
        SimpleDateFormat SimpleDateFormat = new SimpleDateFormat ( "yyyy-MM-dd", Locale.KOREA );
        Date currentTime = new Date ();
        String time = SimpleDateFormat.format ( currentTime );

        timeLineForm.setStartTime(time+" 00:00:00");
        timeLineForm.setEndTime(time+" 23:59:59");
    	return timeLineMapper.selectTimeLineList(timeLineForm);
    }
}
