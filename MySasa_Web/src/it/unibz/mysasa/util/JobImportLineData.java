package it.unibz.mysasa.util;

import it.unibz.mysasa.db.ToolDB;
import it.unibz.mysasa.domain.LinePos;

import java.sql.Timestamp;

public class JobImportLineData extends Thread {

	private static boolean run = true;
	private static long delay = 300000; // 5min.

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
				db.insertLineHist(p, now);
			}
			}catch(Exception e){e.printStackTrace();}
			sleep(delay);
		}
		}catch(Exception e){ e.printStackTrace();}
	}
}
