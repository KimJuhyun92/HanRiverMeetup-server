package com.hangang.HangangRiver.timeline.web;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

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

    @PostMapping("/timeLine")
    private ResponseEntity<TimeLine> createTimeLine(HttpServletRequest request, @RequestBody TimeLine timeLine){
    	TimeLine createdTimeLine = timeLineService.createTimeLine(timeLine);
        return ResponseEntity.ok().body(createdTimeLine);
    }

    @DeleteMapping("/timeLine/{timeLine_seq}")
    private ResponseEntity<Object> removeTimeLine(@PathVariable int timeLine_seq) throws Exception{
    	timeLineService.removeTimeLine(timeLine_seq);
        return ResponseEntity.ok().body(true);
    }

    @GetMapping("/timeLines")
    private ResponseEntity<List<TimeLineForm>> gettimeLines(HttpServletRequest request, TimeLineForm timeLineForm)throws Exception{
        return ResponseEntity.ok().body(timeLineService.selectTodayTimeLine(timeLineForm));
    }
}
