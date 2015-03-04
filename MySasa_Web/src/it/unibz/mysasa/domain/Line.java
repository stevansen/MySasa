package it.unibz.mysasa.domain;

public class Line {

	private Integer LI_NR = null;
	private String LI_KUERZEL = null;
	private String LIDNAME = null;
	private String URI = null;
	private Integer[] varlist = null;

	public Integer getLineNr() {
		return LI_NR;
	}
	
	public void setLineNr(Integer li_nr) {
		this.LI_NR = li_nr;
	}
	
	public String getLineName() {
		return LIDNAME;
	}
	
	public void setLineName(String lidname) {
		this.LIDNAME = lidname;
	}

	public Integer[] getVarlist() {
		return varlist;
	}

	public void setVarlist(Integer[] varlist) {
		this.varlist = varlist;
	}

	public String getLineShort() {
		return LI_KUERZEL;
	}

	public void setLineShort(String li_kuerzel) {
		this.LI_KUERZEL = li_kuerzel;
	}
	
	public void setURI(String val){
		URI = val;
	}

	public String getURI() {
		if (URI == null) {
			String ret = null;
			for (Integer t : varlist) {
				if (ret != null)
					ret += ",";
				else
					ret = "";
				ret += LI_NR + ":" + t;
				URI = ret;
			}
		}
		return URI;
	}

}
