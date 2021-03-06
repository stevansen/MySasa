package it.unibz.mysasa.util;

import it.unibz.mysasa.db.ToolDB;
import it.unibz.mysasa.domain.LinePos;

import java.sql.Timestamp;

public class JobImportLineData extends Thread {

	private static boolean run = false;
	private static long delay = 60000; // 1min.

	public static void setRunning(boolean v) {
		run = v;
	}

	public void run() {
		try{
		while (run) {
			ParseData pd = new ParseData();
			Timestamp now = new Timestamp(System.currentTimeMillis());
			try{
			ToolDB db = new ToolDB();
			for (LinePos p : pd.getPosition()) {
				db.insertLinePosHist(p, now);
			}
			db.cleanLinePosHist();
			}catch(Exception e){e.printStackTrace();}
			sleep(delay);
		}
		}catch(Exception e){ e.printStackTrace();}
	}
}
