package _test;

import it.unibz.mysasa.db.ToolDB;
import it.unibz.mysasa.util.ParseData;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;

public class Test_ReadJSON {

	public static void main(String[] args) {
		System.out.println("Start");
		try {
			ToolDB db = new ToolDB();
			db.createDB();
			ParseData parse = new ParseData();
			File f = new File("REC_LID.json");
			for (String t : FileUtils.readLines(f)) {
				System.out.println(t);
				parse.importLine(t);
			}
			f = new File("REC_ORT.json");
			for (String t : FileUtils.readLines(f)) {
				System.out.println(t);
				parse.importFermata(t);
			}
			f = new File("LID_VERLAUF.json");
			for (String t : FileUtils.readLines(f)) {
				System.out.println(t);
				parse.importPercorso(t);
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println("Stop");
	}

}
