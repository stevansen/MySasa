package it.unibz.mysasa.util;

import it.unibz.mysasa.domain.timetable.TiTable;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

@SuppressWarnings("serial")
public class ApiTimetableServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String lat = request.getParameter("lat");
		String lon = request.getParameter("lon");
		String ret = "";
		if (lat != null && lon != null) {
			//Get TiTable based on Lat Lon
			TiTable t = Operations.getClosestTimetable(lat, lon);
			Gson g = new Gson();
			ret = g.toJson(t);
		}
		response.getOutputStream().print(ret);
	}

	
	
	
	
}
