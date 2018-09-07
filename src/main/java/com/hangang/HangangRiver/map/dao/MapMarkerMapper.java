package com.hangang.HangangRiver.map.dao;

import java.util.List;


import com.hangang.HangangRiver.map.model.MapMarker;


public interface MapMarkerMapper {
	int insert(MapMarker mapMarker);
	List<MapMarker> selectMapMarkerList(int map_category_seq);
}
