package com.hangang.HangangRiver.weather.model;

public class Weather {
	private double th1; //현재기온
	private int sky;	//하늘상태
	private int pty;	//강수상태
	private double tmn; //최저기온
	private double tmx; //최고기온

	public double getTh1() {
		return th1;
	}

	public void setTh1(double th1) {
		this.th1 = th1;
	}

	public int getSky() {
		return sky;
	}

	public void setSky(int sky) {
		this.sky = sky;
	}

	public int getPty() {
		return pty;
	}

	public void setPty(int pty) {
		this.pty = pty;
	}

	public double getTmn() {
		return tmn;
	}

	public void setTmn(double tmn) {
		this.tmn = tmn;
	}

	public double getTmx() {
		return tmx;
	}

	public void setTmx(double tmx) {
		this.tmx = tmx;
	}

	@Override
	public String toString() {
		return "Weather [th1=" + th1 + ", sky=" + sky + ", pty=" + pty + ", tmn=" + tmn + ", tmx=" + tmx + "]";
	}

}
