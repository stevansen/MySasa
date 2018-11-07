package it.unibz.mysasa.util;

import java.io.IOException;
import java.util.Collections;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import it.unibz.mysasa.db.ToolDB;
import it.unibz.mysasa.domain.LinePos;

public class LineDataCacheServlet extends HttpServlet  {

	private static final long serialVersionUID = 1L;
	private JobImportLineData job = new JobImportLineData();
	
	public void init() {
		job.start();
	}
	
	@Override
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String line = request.getParameter("line");
		String ret = "";
		ToolDB db = new ToolDB();
		Gson g = new Gson();
		List<LinePos> l = null;
		if(line!=null && line.length()>0) {
			l = db.getLinePosHist(line);
	    } else {
			l = db.getLinePosHist();
		}
		Collections.sort(l);
		ret = g.toJson(l);
		response.getOutputStream().print(ret);
	}
	
}
