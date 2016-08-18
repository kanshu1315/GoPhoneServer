package com.goophone.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.google.gson.GsonBuilder;
import com.goophone.dao.ManufacturDao;
import com.goophone.dao.impl.ManufacturImpl;
import com.goophone.enity.Manufactur;
import com.goophone.enity.ResponseObject;

public class ManufacturServlet extends HttpServlet {

	private static final long serialVersionUID = 4370443889975501429L;

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException,IOException{
		response.setContentType("text/html");
		response.setCharacterEncoding("UTF-8");
		PrintWriter writer;
		writer = response.getWriter();
		

		ManufacturDao manufacturDao = new ManufacturImpl();
		List<Manufactur> list = manufacturDao.getManufactur();
		ResponseObject result = null;
		if (list != null && list.size() > 0) {
			result = new ResponseObject(1, list);
		} else {
			result = new ResponseObject(0, "没有数据！");
		}

		writer.println(new GsonBuilder().create().toJson(result));
		writer.flush();
		writer.close();

	}

}
