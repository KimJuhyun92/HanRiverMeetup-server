package com.hangang.HangangRiver.timeline.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hangang.HangangRiver.exceptions.InvalidEventException;
import com.hangang.HangangRiver.exceptions.InvalidMeetingException;
import com.hangang.HangangRiver.meeting.model.MeetingDetail;
import com.hangang.HangangRiver.timeline.dao.EventMapper;
import com.hangang.HangangRiver.timeline.model.Event;

@Service
public class EventService {

    @Autowired
    public EventMapper eventMapper;

    public Event createEvent(Event event) {
    	eventMapper.insert(event);
    	return event;
    }

    public void removeEvent(int event_seq){
    	eventMapper.delete(event_seq);
    }

    public void modifyEvent(int event_seq, Event event) throws InvalidEventException{
        if (eventMapper.detail(event_seq) == null) {
            throw new InvalidEventException();
        }
    	eventMapper.update(event_seq, event);
    }

    public Event getEventById(int event_seq){
        return eventMapper.detail(event_seq);
    }

    public List<Event> selectEvent(){
    	Event event = new Event();
    	event.setIsongoing(true);
    	return eventMapper.selectEventList(event);
    }
}
