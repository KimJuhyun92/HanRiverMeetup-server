package com.hangang.HangangRiver.tour.model;

public class TourInformation {
	private String originimgurl; //큰이미지
	private String smallimageurl;	//작은이미지

	public String getOriginimgurl() {
		return originimgurl;
	}

	public void setOriginimgurl(String originimgurl) {
		this.originimgurl = originimgurl;
	}

	public String getSmallimageurl() {
		return smallimageurl;
	}

	public void setSmallimageurl(String smallimageurl) {
		this.smallimageurl = smallimageurl;
	}

	@Override
	public String toString() {
		return "TourInformation [originimgurl=" + originimgurl + ", smallimageurl=" + smallimageurl + "]";
	}

}
