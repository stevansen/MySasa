package it.unibz.mysasa.util;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@SuppressWarnings("serial")
public class SvgServlet extends HttpServlet {

	private String getBubble(String color){
		String t = "";
		t+="<?xml version=\"1.0\" encoding=\"utf-8\"?>";
		t+="<svg version=\"1.2\" baseProfile=\"tiny\" id=\"Ebene_1\" xmlns=\"http://www.w3.org/2000/svg\" xmlns:xlink=\"http://www.w3.org/1999/xlink\"";
		t+="	 x=\"0px\" y=\"0px\" viewBox=\"0 0 512 512\" xml:space=\"preserve\">";
		t+="<ellipse fill=\""+color+"\" stroke=\""+color+"\" stroke-miterlimit=\"10\" cx=\"256\" cy=\"256\" rx=\"241.6\" ry=\"245.6\"/>";
		t+="</svg>";
		return t;
	}
	
	private String getMySasa(String c1, String c2){
		String t = "";
		t+="<?xml version=\"1.0\" encoding=\"utf-8\"?>";
		t+="<svg version=\"1.2\" baseProfile=\"tiny\" xmlns=\"http://www.w3.org/2000/svg\" xmlns:xlink=\"http://www.w3.org/1999/xlink\" x=\"0px\"";
		t+="	 y=\"0px\" viewBox=\"0 0 512 512\" xml:space=\"preserve\">";
		t+="<g id=\"Ebene_2\">";
		t+="	<ellipse fill=\""+c2+"\" stroke=\""+c2+"\" stroke-miterlimit=\"10\" cx=\"256\" cy=\"256\" rx=\"248\" ry=\"250.3\"/>";
		t+="</g>";
		t+="<g id=\"Ebene_1\">";
		t+="	<path stroke=\""+c1+"\" stroke-miterlimit=\"10\" d=\"M408.7,401.9H103.3c-6.6,0-12-5.4-12-12V99.4c0-6.6,5.4-12,12-12h305.5";
		t+="		c6.6,0,12,5.4,12,12v290.5C420.7,396.5,415.3,401.9,408.7,401.9z\"/>";
		t+="	<path stroke=\""+c1+"\" stroke-miterlimit=\"10\" d=\"M377.4,436.2h-38.6c-6.6,0-12-5.4-12-12v-10.3c0-6.6,5.4-12,12-12h38.6";
		t+="		c6.6,0,12,5.4,12,12v10.3C389.4,430.8,384,436.2,377.4,436.2z\"/>";
		t+="	<path fill=\""+c2+"\" stroke=\""+c1+"\" stroke-miterlimit=\"10\" d=\"M173.1,370.1h-38.6c-6.6,0-12-5.4-12-12v-39.7c0-6.6,5.4-12,12-12";
		t+="		h38.6c6.6,0,12,5.4,12,12v39.7C185.1,364.7,179.7,370.1,173.1,370.1z\"/>";
		t+="	<path fill=\""+c2+"\" stroke=\""+c1+"\" stroke-miterlimit=\"10\" d=\"M377.4,370.1h-38.6c-6.6,0-12-5.4-12-12v-39.7c0-6.6,5.4-12,12-12";
		t+="		h38.6c6.6,0,12,5.4,12,12v39.7C389.4,364.7,384,370.1,377.4,370.1z\"/>";
		t+="	<path stroke=\""+c1+"\" stroke-miterlimit=\"10\" d=\"M173.1,436.2h-38.6c-6.6,0-12-5.4-12-12v-10.3c0-6.6,5.4-12,12-12h38.6";
		t+="		c6.6,0,12,5.4,12,12v10.3C185.1,430.8,179.7,436.2,173.1,436.2z\"/>";
		t+="	<path fill=\""+c2+"\" stroke=\""+c1+"\" stroke-miterlimit=\"10\" d=\"M385.3,287.3h-260c-6.6,0-12-5.4-12-12V117.4c0-6.6,5.4-12,12-12";
		t+="		h260c6.6,0,12,5.4,12,12v157.8C397.3,281.9,391.9,287.3,385.3,287.3z\"/>";
		t+="</g>";
		t+="</svg>";
		return t;
	}
	
	public String getStation(String c1, String c2){
		String t = "";
		t+="<?xml version=\"1.0\" encoding=\"utf-8\"?>";
		t+="<svg version=\"1.2\" baseProfile=\"tiny\" xmlns=\"http://www.w3.org/2000/svg\" xmlns:xlink=\"http://www.w3.org/1999/xlink\" x=\"0px\"";
		t+="	 y=\"0px\" viewBox=\"0 0 512 512\" xml:space=\"preserve\">";
		t+="<g id=\"Ebene_1\">";
		t+="	<ellipse fill=\""+c2+"\" stroke=\""+c2+"\" stroke-miterlimit=\"10\" cx=\"256\" cy=\"256\" rx=\"240.8\" ry=\"243\"/>";
		t+="</g>";
		t+="<g id=\"Ebene_2\">";
		t+="	<rect x=\"87\" y=\"128.9\" stroke=\""+c1+"\" stroke-miterlimit=\"10\" width=\"338.1\" height=\"29.1\"/>";
		t+="	<rect x=\"184.8\" y=\"317.2\" stroke=\""+c1+"\" stroke-miterlimit=\"10\" width=\"142.5\" height=\"14.9\"/>";
		t+="	<rect x=\"87\" y=\"158\" stroke=\""+c1+"\" stroke-miterlimit=\"10\" width=\"9\" height=\"218.7\"/>";
		t+="	<rect x=\"184.7\" y=\"332.1\" stroke=\""+c1+"\" stroke-miterlimit=\"10\" width=\"5.5\" height=\"44.6\"/>";
		t+="	<rect x=\"416\" y=\"158\" stroke=\""+c1+"\" stroke-miterlimit=\"10\" width=\"9\" height=\"218.7\"/>";
		t+="	<rect x=\"321.8\" y=\"332.1\" stroke=\""+c1+"\" stroke-miterlimit=\"10\" width=\"5.5\" height=\"44.6\"/>";
		t+="</g>";
		t+="<g id=\"Ebene_3\">";
		t+="</g>";
		t+="</svg>";
		return t;
	}
	
	public void doGet(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		/*-*** Init Parameters ***-*/
		String c1 = req.getParameter("c");
		String c2 = req.getParameter("c2");
		String typ = req.getParameter("t");
		if(typ==null)
			typ = "";
		if(c1==null || c1.length()!=6)
			c1 = "000000";
		if(c2==null || c2.length()!=6)
			c2 = "999999";
		res.setContentType("image/svg+xml");
	    res.setHeader("Content-Disposition", "filename=\"img.svg\"");
		ServletOutputStream out = res.getOutputStream();
		try {
			String t = getBubble("#"+c1);
			if(typ.equals("MySasa"))
				t = getMySasa("#"+c1, "#"+c2);
			if(typ.equals("Station"))
				t = getStation("#"+c1, "#"+c2);
			out.print(t);
			out.flush();
			out.close();
		} catch (IOException e) {
			System.out.println("IOException: " + e.getMessage());
		}
	}

}
