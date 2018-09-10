package com.hangang.HangangRiver.timeline.web;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

import javax.servlet.http.HttpServletRequest;

import com.hangang.HangangRiver.exceptions.InvalidJsonBody;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hangang.HangangRiver.timeline.model.TimeLine;
import com.hangang.HangangRiver.timeline.model.TimeLineForm;
import com.hangang.HangangRiver.timeline.service.TimeLineService;

@RestController
@RequestMapping("/timeLine")
public class TimeLineController {

    @Autowired
    TimeLineService timeLineService;

    @PostMapping("/post")
    private ResponseEntity<TimeLine> createPost(HttpServletRequest request, @RequestBody TimeLine timeLine){
    	TimeLine createdTimeLine = timeLineService.createTimeLine(timeLine);
        return ResponseEntity.ok().body(createdTimeLine);
    }

    @DeleteMapping("/post/{timeLine_seq}")
    private ResponseEntity<Object> removePost(@PathVariable int timeLine_seq) throws Exception{
    	timeLineService.removeTimeLine(timeLine_seq);
        return ResponseEntity.ok().body(true);
    }

    @GetMapping("/posts")
    private ResponseEntity<List<TimeLineForm>> getPosts(HttpServletRequest request, TimeLineForm timeLineForm)
            throws Exception{
        return ResponseEntity.ok().body(timeLineService.selectTodayTimeLine(timeLineForm));
    }

    @PostMapping("/posts")
    private ResponseEntity<List<TimeLineForm>> getPosts(HttpServletRequest request, @RequestBody Map<String, Object> jsonString)
            throws InvalidJsonBody, ParseException {

        JSONObject jsonObj = new JSONObject(jsonString);

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date date = sdf.parse((String)jsonObj.get("date"));
        Integer offset = (Integer)jsonObj.get("offset");
        Integer limit = (Integer)jsonObj.get("limit");

        if(date == null || offset == null || limit == null) {
            throw new InvalidJsonBody();
        }

        return ResponseEntity.ok().body(timeLineService.selectPosts(date, offset, limit));
    }
}