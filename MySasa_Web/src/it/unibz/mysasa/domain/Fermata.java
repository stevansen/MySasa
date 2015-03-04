package it.unibz.mysasa.domain;

public class Fermata {

	private Integer ORT_NR = null;
	private Double ORT_POS_BREITE = null;
	private Double ORT_POS_LAENGE = null;
	
	public Integer getOrtNr() {
		return ORT_NR;
	}
	
	public void setOrtNr(Integer oRT_NR) {
		ORT_NR = oRT_NR;
	}
	
	public Double getLat() {
		return ORT_POS_BREITE;
	}
	
	public void setLat(Double oRT_POS_BREITE) {
		ORT_POS_BREITE = oRT_POS_BREITE;
	}
	
	public Double getLon() {
		return ORT_POS_LAENGE;
	}
	
	public void setLon(Double oRT_POS_LAENGE) {
		ORT_POS_LAENGE = oRT_POS_LAENGE;
	}
	
}
