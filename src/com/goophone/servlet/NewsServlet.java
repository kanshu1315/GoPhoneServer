package com.goophone.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.GsonBuilder;
import com.goophone.dao.NewsDao;
import com.goophone.dao.impl.NewsDaoImpl;
import com.goophone.enity.News;
import com.goophone.enity.ResponseObject;

public class NewsServlet extends HttpServlet {

	/**
	 * ��Ѷ
	 */
	private static final long serialVersionUID = 7382535951214792294L;

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html");
		response.setCharacterEncoding("UTF-8");
		PrintWriter writer=response.getWriter();
//		Map<String, String> map = request.getParameterMap();
//		int page = Integer.parseInt(map.get("page")) ;
//        int size = Integer.parseInt(map.get("size"));
		int page = Integer.parseInt(request.getParameter("page"));
		int size = Integer.parseInt(request.getParameter("size"));
		System.out.println(page+size);
		NewsDao newsDao = new NewsDaoImpl();
		int count = newsDao.getCount();
		List<News> list = newsDao.getNews(page,size);
		ResponseObject result = null;
		if (list != null && list.size() > 0) {
			result = new ResponseObject(1, list);
			result.setPage(page);
			result.setCount((int) Math.ceil(count/size));
			result.setSize(size);
		} else {
			result = new ResponseObject(0, "没有资讯！");
		}

		writer.println(new GsonBuilder().create().toJson(result));
		writer.flush();
		writer.close();

	}

}
