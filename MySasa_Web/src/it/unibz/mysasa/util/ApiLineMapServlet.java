package it.unibz.mysasa.util;

import it.unibz.mysasa.domain.LineMap;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

@SuppressWarnings("serial")
public class ApiLineMapServlet extends HttpServlet {

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
		if (line != null) {
			LineMap lm = Operations.getLineMap(line);
			Gson g = new Gson();
			ret = g.toJson(lm);
		}
		response.getOutputStream().print(ret);
	}
	
}
