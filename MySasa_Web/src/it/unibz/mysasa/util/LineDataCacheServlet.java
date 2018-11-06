package it.unibz.mysasa.util;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import it.unibz.mysasa.db.ToolDB;

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
		String ret = "";
		ToolDB db = new ToolDB();
		Gson g = new Gson();
		ret = g.toJson(db.getLinePosHist());
		response.getOutputStream().print(ret);
	}
	
}
