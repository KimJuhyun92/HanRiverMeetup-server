package com.hangang.HangangRiver.weather.model;

public class Weather {
	private int weather_seq;
	private String t1h; //현재기온
	private String sky;	//하늘상태
	private String pty;	//강수상태
	private String tmn; //최저기온
	private String tmx; //최고기온

	public int getWeather_seq() {
		return weather_seq;
	}

	public void setWheather_seq(int wheather_seq) {
		this.weather_seq = wheather_seq;
	}

	public String getT1h() {
		return t1h;
	}

	public void setT1h(String t1h) {
		this.t1h = t1h;
	}

	public String getSky() {
		return sky;
	}

	public void setSky(String sky) {
		this.sky = sky;
	}

	public String getPty() {
		return pty;
	}

	public void setPty(String pty) {
		this.pty = pty;
	}

	public String getTmn() {
		return tmn;
	}

	public void setTmn(String tmn) {
		this.tmn = tmn;
	}

	public String getTmx() {
		return tmx;
	}

	public void setTmx(String tmx) {
		this.tmx = tmx;
	}

	@Override
	public String toString() {
		return "Weather [wheather_seq=" + weather_seq + ", t1h=" + t1h + ", sky=" + sky + ", pty=" + pty + ", tmn="
				+ tmn + ", tmx=" + tmx + "]";
	}

}
