package it.unibz.mysasa.domain;

import it.unibz.mysasa.util.Tools;

public class PercorsoLine {

	private String LI_NR = null;
	private PercorsoRotta[] varlist = null;
	
	public String getLineNr() {
		return LI_NR;
	}
	public void setLineNr(String lI_NR) {
		LI_NR = lI_NR;
	}
	public PercorsoRotta[] getRotta() {
		return varlist;
	}
	public void setRotta(PercorsoRotta[] varlist) {
		this.varlist = varlist;
	}
	public Integer getLiNr() {
		return Tools.getInt(LI_NR);
	}
	
}
