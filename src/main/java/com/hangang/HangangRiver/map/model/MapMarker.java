package com.hangang.HangangRiver.map.model;

public class MapMarker {
	private int map_seq;
	private double lat;
	private double lng;
	private int map_category_seq;

	public int getMap_seq() {
		return map_seq;
	}

	public void setMap_seq(int map_seq) {
		this.map_seq = map_seq;
	}

	public double getLat() {
		return lat;
	}

	public void setLat(double lat) {
		this.lat = lat;
	}

	public double getLng() {
		return lng;
	}

	public void setLng(double lng) {
		this.lng = lng;
	}

	public int getMap_category_seq() {
		return map_category_seq;
	}

	public void setMap_category_seq(int map_category_seq) {
		this.map_category_seq = map_category_seq;
	}

	@Override
	public String toString() {
		return "MapMarker [map_seq=" + map_seq + ", lat=" + lat + ", lng=" + lng + ", map_category_seq="
				+ map_category_seq + "]";
	}

}
