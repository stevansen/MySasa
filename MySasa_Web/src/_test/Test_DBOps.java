package _test;

import it.unibz.mysasa.db.ToolDB;
import it.unibz.mysasa.domain.Fermata;

public class Test_DBOps {

	public static void main(String[] args){
		System.out.println("Start");
		ToolDB db = new ToolDB();
		for(Fermata f : db.getFermate()){
			System.out.println(f.getOrtNr());
		}
		System.out.println("-------------------------------");
		for(Fermata f : db.getFermate(201)){
			System.out.println(f.getOrtNr());
		}
		System.out.println("Stop");
	}
	
}
