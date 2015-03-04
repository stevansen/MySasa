package _test;

import it.unibz.mysasa.domain.LinePos;
import it.unibz.mysasa.util.ParseData;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.apache.commons.io.FileUtils;

public class Test_Read_RTPos_JSON {

	public static void main(String[] args) {
		System.out.println("Start");
		try {
			ParseData parse = new ParseData();
			File f = new File("positions.json");
			List<LinePos> lp = null;
			for (String t : FileUtils.readLines(f)) {
				System.out.println(t);
				lp = parse.getPosition(t);
			}
			for(LinePos p : lp){
				System.out.println(p.getLine()+" ("+p.getX()+", "+p.getY()+")");
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println("Stop");
	}

}
