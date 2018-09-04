package com.hangang.HangangRiver.timeline.web;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hangang.HangangRiver.exceptions.InvalidEventException;
import com.hangang.HangangRiver.exceptions.InvalidMeetingDetailException;
import com.hangang.HangangRiver.meeting.model.Comment;
import com.hangang.HangangRiver.meeting.model.MeetingDetail;
import com.hangang.HangangRiver.meeting.model.MeetingDetailForm;
import com.hangang.HangangRiver.timeline.model.Event;
import com.hangang.HangangRiver.timeline.service.EventService;

@RestController
@RequestMapping("/event")
public class EventController {

    @Autowired
    EventService eventService;

    @PostMapping("/event")
    private ResponseEntity<Event> createEvent(HttpServletRequest request, @RequestBody Event event){
    	Event createdEvent = eventService.createEvent(event);
        return ResponseEntity.ok().body(createdEvent);
    }

    @DeleteMapping("/event/{event_seq}")
    private ResponseEntity<Object> removeEvent(@PathVariable int event_seq) throws Exception{
    	eventService.removeEvent(event_seq);
        return ResponseEntity.ok().body(true);
    }

	@PutMapping("/event/{event_seq}")
	private ResponseEntity<Object> modifyEvent(@PathVariable int event_seq, @RequestBody Event event)throws InvalidEventException{
		eventService.modifyEvent(event_seq, event);
		return ResponseEntity.ok().body(true);
	}

    @GetMapping("/events")
    private ResponseEntity<List<Event>> getEvents(HttpServletRequest request)throws Exception{
        return ResponseEntity.ok().body(eventService.selectEvent());
    }
}
