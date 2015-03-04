package it.unibz.mysasa.domain;

public class PercorsoRotta {

	private Integer STR_LI_VAR = null;
	private Integer[] routelist = null;
	
	public Integer getLineVariante() {
		return STR_LI_VAR;
	}
	public void setLineVariante(Integer sTR_LI_VAR) {
		STR_LI_VAR = sTR_LI_VAR;
	}
	public Integer[] getRoutelist() {
		return routelist;
	}
	public void setRoutelist(Integer[] routelist) {
		this.routelist = routelist;
	}
	
}
