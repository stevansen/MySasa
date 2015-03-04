package it.unibz.mysasa.domain;

public class Ort {

	private String ORT_NAME = null;
	private String ORT_GEMEINDE = null;
	private Fermata[] busstops = null;
	
	public String getLuogo(){
		return ORT_NAME;
	}
	
	public void setLuogo(String val){
		ORT_NAME = val;
	}
	
	public String getComune(){
		return ORT_GEMEINDE;
	}
	
	public void setComune(String val){
		ORT_GEMEINDE = val;
	}
	
	public Fermata[] getFermate(){
		return busstops;
	}
	
	public void setFermate(Fermata[] val){
		busstops = val;
	}
	
	
}
