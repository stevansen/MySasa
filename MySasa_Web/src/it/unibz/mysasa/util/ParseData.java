package it.unibz.mysasa.util;

import java.io.InputStream;
import java.net.URL;
import java.util.List;
import java.util.Vector;

import org.apache.commons.io.IOUtils;

import it.unibz.mysasa.db.ToolDB;
import it.unibz.mysasa.domain.Fermata;
import it.unibz.mysasa.domain.Line;
import it.unibz.mysasa.domain.LinePos;
import it.unibz.mysasa.domain.Ort;
import it.unibz.mysasa.domain.PercorsoLine;
import it.unibz.mysasa.domain.PercorsoRotta;
import it.unibz.mysasa.domain.realtime.RTFeature;
import it.unibz.mysasa.domain.realtime.RTPos;
import it.unibz.mysasa.domain.timetable.TiTable;
import it.unibz.mysasa.geo.CoordConv;
import it.unibz.mysasa.geo.LatLon;

import com.google.gson.Gson;

public class ParseData {

	private String url_lines = "http://opensasa.info/SASAplandata/?type=REC_LID";
	private String url_perc = "http://opensasa.info/SASAplandata/?type=LID_VERLAUF";
	private String url_stat = "http://opensasa.info/SASAplandata/?type=REC_ORT";
	private String url_rtpos = "http://realtime.opensasa.info/positions";
	private String url_rtpos_l = "http://realtime.opensasa.info/positions?lines=";
	private String url_station = "http://stationboard.opensasa.info/?type=json&ORT_NR=";

	private String getURL(String val) {
		String ret = null;
		InputStream in = null;
		try {
			in = new URL(val).openStream();
			ret = IOUtils.toString(in);
		} catch (Exception e) {
		} finally {
			IOUtils.closeQuietly(in);
		}
		return ret;
	}

	public TiTable getTimetable(String t) {
		Gson gson = new Gson();
		TiTable tt = gson.fromJson(t, TiTable.class);
		return tt;
	}
	
	public TiTable getTimetableStation(String station){
		TiTable ret = null;
		String t = getURL(url_station+station);
		ret = getTimetable(t);
		return ret;
	}

	public List<LinePos> getPositionLine(String line){
		List<LinePos> ret = null;
		String t = getURL(url_rtpos_l+line);
		ret = getPosition(t);
		return ret;
	}
	
	public List<LinePos> getPosition(){
		List<LinePos> ret = null;
		String t = getURL(url_rtpos);
		ret = getPosition(t);
		return ret;
	}
	
	public List<LinePos> getPosition(String t) {
		CoordConv conv = new CoordConv();
		List<LinePos> ret = new Vector<LinePos>();
		Gson gson = new Gson();
		RTPos pos = gson.fromJson(t, RTPos.class);
		for (RTFeature f : pos.features) {
			LinePos lp = new LinePos();
			lp.setName(f.properties.lidname);
			lp.setLine(f.properties.li_nr);
			lp.setOrt(f.properties.ort_nr);
			lp.setOrtname(f.properties.ort_name);
			lp.setVar(Tools.getInt(f.properties.str_li_var));
			lp.setColor(f.properties.hexcolor);
			lp.setColor2(f.properties.hexcolor2);
			lp.setX(f.geometry.coordinates[0]);
			lp.setY(f.geometry.coordinates[1]);
			LatLon l = conv.getEPSG32632toWGS84(lp.getX(), lp.getY());
			lp.setLat(l.getLat());
			lp.setLon(l.getLon());
			ret.add(lp);
		}
		return ret;
	}
	
	public void importBasedata() {
		ToolDB db = new ToolDB();
		db.clean();
		importLine(getURL(url_lines));
		importFermata(getURL(url_stat));
		importPercorso(getURL(url_perc));
	}
	
	public void importLine(String t) {
		Gson gson = new Gson();
		Line[] l = gson.fromJson(t, Line[].class);
		ToolDB db = new ToolDB();
		for (Line bl : l) {
			db.insertLine(bl.getLineNr(), bl.getLineName(), bl.getURI());
		}
	}

	public void importFermata(String t) {
		Gson gson = new Gson();
		Ort[] ol = gson.fromJson(t, Ort[].class);
		ToolDB db = new ToolDB();
		for (Ort o : ol) {
			for (Fermata ferm : o.getFermate()) {
				db.insertStation(ferm.getOrtNr(), ferm.getLon(), ferm.getLat());
			}
		}
	}

	public void importPercorso(String t) {
		ToolDB db = new ToolDB();
		Gson gson = new Gson();
		PercorsoLine[] ol = gson.fromJson(t, PercorsoLine[].class);
		for (PercorsoLine o : ol) {
			for (PercorsoRotta r : o.getRotta()) {
				for (Integer i : r.getRoutelist()) {
					db.insertPercorso(o.getLiNr(), r.getLineVariante(), i);
				}
			}
		}
	}

}
