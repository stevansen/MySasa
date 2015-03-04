package _test;

import it.unibz.mysasa.domain.Fermata;
import it.unibz.mysasa.domain.LineMap;
import it.unibz.mysasa.domain.LinePos;
import it.unibz.mysasa.util.Operations;

public class Test_RetriveLineMapData {

	public static void main(String[] args) {
		System.out.println("Start");
		LineMap lm = Operations.getLineMap("201");
		for(LinePos l : lm.getLinea()){
			System.out.println(l.getName()+"  "+l.getX()+"  "+l.getY());
		}
		for(Fermata f: lm.getFermate()){
			System.out.println(f.getOrtNr()+"   "+f.getLat()+"   "+f.getLon());
		}
		System.out.println("Stop");
	}
}
