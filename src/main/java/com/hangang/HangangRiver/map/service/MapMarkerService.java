package com.hangang.HangangRiver.map.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hangang.HangangRiver.map.dao.MapMarkerMapper;
import com.hangang.HangangRiver.map.model.MapMarker;

@Service
public class MapMarkerService {

    @Autowired
    public MapMarkerMapper mapMarkerMapper;

    public MapMarker createMapMarker(MapMarker mapMarker) {
    	mapMarkerMapper.insert(mapMarker);
    	return mapMarker;
    }

    public List<MapMarker> getMapMarkersByCategory(int map_category_seq){
    	return mapMarkerMapper.selectMapMarkerList(map_category_seq);
    }
}
