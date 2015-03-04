package it.unibz.mysasa.geo;

import org.geotoolkit.geometry.DirectPosition2D;
import org.geotoolkit.referencing.CRS;
import org.geotoolkit.referencing.crs.DefaultGeographicCRS;
import org.opengis.geometry.DirectPosition;
import org.opengis.referencing.crs.CoordinateReferenceSystem;
import org.opengis.referencing.operation.MathTransform;

public class CoordConv {

	private CoordinateReferenceSystem sourceCRS = null; // WGS84 (lat, lon)
	private CoordinateReferenceSystem targetCRS = null; // UTM 32N
	private MathTransform tr = null;
	
	public CoordConv() {
		try {
			targetCRS = DefaultGeographicCRS.WGS84; // WGS84 (lat, lon)
			sourceCRS = CRS.decode("EPSG:32632"); // UTM 32N
			tr = CRS.findMathTransform(sourceCRS, targetCRS);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public LatLon getEPSG32632toWGS84(double x,double y){
		LatLon ret = null;
		try{
			DirectPosition sourcePt = new DirectPosition2D(sourceCRS, x, y);
			DirectPosition targetPt = new DirectPosition2D(targetCRS);
			targetPt = tr.transform(sourcePt, targetPt);
			double[] p = targetPt.getCoordinate();
			ret = new LatLon();
			ret.setLon(p[0]);
			ret.setLat(p[1]);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ret;
	}

}
