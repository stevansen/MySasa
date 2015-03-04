package _test;

import it.unibz.mysasa.geo.CoordConv;
import it.unibz.mysasa.geo.LatLon;

public class Test_ConvertCoordinates {

	public static void main(String[] args) {
		System.out.println("Start");
		double x = 678108.68959015;
		double y = 5151014.4499869;
		CoordConv c = new CoordConv();
		LatLon ll = c.getEPSG32632toWGS84(x, y);
		System.out.println(x+"   "+y);
		System.out.println(ll.getLat()+"    "+ll.getLon());
		System.out.println("Stop");

	}

}
