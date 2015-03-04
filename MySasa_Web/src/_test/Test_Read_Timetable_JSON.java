package _test;

import it.unibz.mysasa.domain.timetable.TiRides;
import it.unibz.mysasa.domain.timetable.TiTable;
import it.unibz.mysasa.util.ParseData;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;

public class Test_Read_Timetable_JSON {

	public static void main(String[] args) {
		System.out.println("Start");
		try {
			ParseData parse = new ParseData();
			File f = new File("stationboard.json");
			TiTable tt = null;
			for (String t : FileUtils.readLines(f)) {
				System.out.println(t);
				tt = parse.getTimetable(t);
			}
			System.out.println(tt.stationname);
			for(TiRides r : tt.rides){
				System.out.println(r.lidname+"   "+r.departure+"    "+r.delay_min+"  "+r.last_station);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println("Stop");
	}
	
}
