package it.unibz.mysasa.domain;

import java.sql.Timestamp;

public class LinePos {

	private Integer line = null;
	private Integer var = null;
	private String name = null;
	private Integer ort = null;
	private String ortname = null;
	private Double x = null;
	private Double y = null;
	private String color = null;
	private String color2 = null;
	private Double lat = null;
	private Double lon = null;
	private Integer fid = null;
	private Timestamp gps_date = null;
	private Integer delay_sec = null;
	private Timestamp cdat = null;
	
	public Integer getLine() {
		return line;
	}
	public void setLine(Integer line) {
		this.line = line;
	}
	
	public Double getX() {
		return x;
	}
	public void setX(Double x) {
		this.x = x;
	}
	
	public Double getY() {
		return y;
	}
	public void setY(Double y) {
		this.y = y;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getOrt() {
		return ort;
	}
	public void setOrt(Integer ort) {
		this.ort = ort;
	}
	public String getOrtname() {
		return ortname;
	}
	public void setOrtname(String ortname) {
		this.ortname = ortname;
	}
	
	public String getC(){
		String t = "000000";
		if(color!=null)
			t = color.substring(1);
		return t;
	}
	
	public String getC2(){
		String t = "999999";
		if(color2!=null)
			t = color2.substring(1);
		return t;
	}
	
	public String getColor() {
		return color;
	}
	public void setColor(String color) {
		this.color = color;
	}
	public Integer getVar() {
		return var;
	}
	public void setVar(Integer var) {
		this.var = var;
	}
	public String getColor2() {
		return color2;
	}
	public void setColor2(String color2) {
		this.color2 = color2;
	}
	public Double getLat() {
		return lat;
	}
	public void setLat(Double lat) {
		this.lat = lat;
	}
	public Double getLon() {
		return lon;
	}
	public void setLon(Double lon) {
		this.lon = lon;
	}
	public Integer getFahrtId() {
		return fid;
	}
	public void setFahrtId(Integer fid) {
		this.fid = fid;
	}
	public Timestamp getGpsDate() {
		return gps_date;
	}
	public void setGpsDate(Timestamp gps_date) {
		this.gps_date = gps_date;
	}
	public Integer getDelaySec() {
		return delay_sec;
	}
	public void setDelaySec(Integer delay_sec) {
		this.delay_sec = delay_sec;
	}
	public Timestamp getCreateDat() {
		return cdat;
	}
	public void setCreateDat(Timestamp cdat) {
		this.cdat = cdat;
	}
	
}
