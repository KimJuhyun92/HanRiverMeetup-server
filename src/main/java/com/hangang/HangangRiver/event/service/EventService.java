package com.hangang.HangangRiver.event.service;

import com.hangang.HangangRiver.event.dao.EventMapper;
import com.hangang.HangangRiver.event.model.Event;
import com.hangang.HangangRiver.exceptions.InvalidEventException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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
