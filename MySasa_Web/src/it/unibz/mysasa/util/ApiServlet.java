package it.unibz.mysasa.util;

import it.unibz.mysasa.domain.LineMap;
import it.unibz.mysasa.domain.timetable.TiTable;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

@SuppressWarnings("serial")
public class ApiServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String m = request.getParameter("mode");
		if (m != null) {
			try {
				if (m.equals("timetable")) {
					line(request, response);
				}
				if (m.equals("line")) {
					timetable(request, response);
				}
				if (m.equals("active")) {
					active(request, response);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	public void timetable(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String lat = request.getParameter("lat");
		String lon = request.getParameter("lon");
		String ret = "";
		if (lat != null && lon != null) {
			// Get TiTable based on Lat Lon
			TiTable t = Operations.getClosestTimetable(lat, lon);
			Gson g = new Gson();
			ret = g.toJson(t);
		}
		response.getOutputStream().print(ret);
	}

	public void line(HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String line = request.getParameter("line");
		String ret = "";
		if (line != null) {
			LineMap lm = Operations.getLineMap(line);
			Gson g = new Gson();
			ret = g.toJson(lm);
		}
		response.getOutputStream().print(ret);
	}
	
	public void active(HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String ret = "";
		ParseData pd = new ParseData();
		pd.getPosition();
		Gson g = new Gson();
		ret = g.toJson(pd.getPosition());
		response.getOutputStream().print(ret);
	}

}
