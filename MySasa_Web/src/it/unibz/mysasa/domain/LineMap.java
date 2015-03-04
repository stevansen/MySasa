package it.unibz.mysasa.domain;

import java.util.List;
import java.util.Vector;

public class LineMap {

	private List<LinePos> linePos = new Vector<LinePos>();
	private List<Fermata> fermate = new Vector<Fermata>();
	
	public List<LinePos> getLinea() {
		return linePos;
	}
	public void setLinea(List<LinePos> linePos) {
		this.linePos = linePos;
	}
	
	public List<Fermata> getFermate() {
		return fermate;
	}
	public void setFermate(List<Fermata> fermate) {
		this.fermate = fermate;
	}
	
}
