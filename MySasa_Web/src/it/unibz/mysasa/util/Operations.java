package it.unibz.mysasa.util;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;

import it.unibz.mysasa.db.ToolDB;
import it.unibz.mysasa.domain.Fermata;
import it.unibz.mysasa.domain.Line;
import it.unibz.mysasa.domain.LineMap;
import it.unibz.mysasa.domain.LinePos;
import it.unibz.mysasa.domain.timetable.TiTable;
import it.unibz.mysasa.geo.CoordConv;
import it.unibz.mysasa.geo.LatLon;

public class Operations {
	
	private static ToolDB db = new ToolDB();

	public static synchronized List<Fermata> getFermate(){
		return db.getFermate();
	}
	
	private static Double dist(Double x1, Double y1, Double x2, Double y2) {
		return Math.sqrt(Math.abs(x1 - x2) * Math.abs(x1 - x2)
				+ Math.abs(y1 - y2) * Math.abs(y1 - y2));
	}

	public static synchronized TiTable getClosestTimetable(String vlat, String vlon) {
		TiTable t = null;
		if (vlat == null || vlon == null)
			return t;
		Double lat = Tools.getDouble(vlat);
		Double lon = Tools.getDouble(vlon);
		if (lat == null || lon == null)
			return t;
		Double dist = null;
		Fermata sel = null;
		for (Fermata f : db.getFermate()) {
			Double dis = dist(f.getLat(), f.getLon(), lat, lon);
			if (dist == null || dis < dist) {
				dist = dis;
				sel = f;
			}
		}
		String ortnr = "" + sel.getOrtNr();
		ParseData pd = new ParseData();
		t = pd.getTimetableStation(ortnr);
		return t;
	}
	
	public static synchronized TiTable getClosestTimetableConv(String vlat, String vlon){
		TiTable t = null;
		if (vlat == null || vlon == null)
			return t;
		Double x = Tools.getDouble(vlat);
		Double y = Tools.getDouble(vlon);
		if (x == null || y == null)
			return t;
		CoordConv c = new CoordConv();
		LatLon pos = c.getEPSG32632toWGS84(y, x);
		t = getClosestTimetable(""+pos.getLat(), ""+pos.getLon());
		return t;
	}
	
	public static synchronized Fermata getFermata(String val){
		Fermata ret = null;
		Integer id = Tools.getInt(val);
		if(id!=null){
			ret = db.getFermataId(id);
		}
		return ret;
	}
	
	public static synchronized LineMap getLineMapNr(String line){
		if(line==null || line.length()<1)
			return null;
		LineMap ret = new LineMap();
		ParseData p = new ParseData();
		List<Line> ll = db.getLineByNr(line);
		for(Line t : ll){
			ret.getLinea().addAll(p.getPositionLine(t.getURI()));
		}
		HashSet<Fermata> hs_f = new HashSet<Fermata>();
		for(LinePos lp : ret.getLinea()){
			hs_f.addAll(db.getFermate(lp.getLine(), lp.getVar()));
		}
		ret.getFermate().addAll(hs_f);
		return ret;
	}
	
	public static synchronized LineMap getCacheLineMapNr(String line){
		if(line==null || line.length()<1)
			return null;
		LineMap ret = new LineMap();
		ret.getLinea().addAll(db.getLinePosHist(line));
		Collections.sort(ret.getLinea());
		HashSet<Fermata> hs_f = new HashSet<Fermata>();
		for(LinePos lp : ret.getLinea()){
			hs_f.addAll(db.getFermate(lp.getLine(), lp.getVar()));
		}
		ret.getFermate().addAll(hs_f);
		return ret;
	}
	
	public static synchronized LineMap getLineMap(String line){
		if(line==null || line.length()<1)
			return null;
		LineMap ret = new LineMap();
		ParseData p = new ParseData();
		List<Line> ll = db.getLine(line);
		for(Line t : ll){
			ret.getLinea().addAll(p.getPositionLine(t.getURI()));
		}
		HashSet<Fermata> hs_f = new HashSet<Fermata>();
		for(LinePos lp : ret.getLinea()){
			hs_f.addAll(db.getFermate(lp.getLine(), lp.getVar()));
		}
		ret.getFermate().addAll(hs_f);
		return ret;
	}
	
}
