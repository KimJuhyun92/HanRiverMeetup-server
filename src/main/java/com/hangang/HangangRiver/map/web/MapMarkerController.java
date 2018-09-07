package com.hangang.HangangRiver.map.web;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hangang.HangangRiver.map.model.MapMarker;
import com.hangang.HangangRiver.map.service.MapMarkerService;

@RestController
@RequestMapping("/mapMarker")
public class MapMarkerController {

    @Autowired
    MapMarkerService mapMarkerService;

    @PostMapping("/mapMarker")
    private ResponseEntity<MapMarker> createMapMarker(HttpServletRequest request, @RequestBody MapMarker mapMarker){
    	MapMarker createdMapMarker = mapMarkerService.createMapMarker(mapMarker);
        return ResponseEntity.ok().body(createdMapMarker);
    }

    @GetMapping("/mapMarkers/{map_category_seq}")
    private ResponseEntity<List<MapMarker>> getMapMarkers(HttpServletRequest request, @PathVariable int map_category_seq)throws Exception{
        return ResponseEntity.ok().body(mapMarkerService.getMapMarkerByCategory(map_category_seq));
    }
}
