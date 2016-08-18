package com.goophone.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;

import com.google.gson.GsonBuilder;
import com.goophone.dao.GoodsDao;
import com.goophone.dao.impl.GoodsDaoImpl;
import com.goophone.enity.Goods;
import com.goophone.enity.ResponseObject;

public class GoodsServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		response.setCharacterEncoding("UTF-8");
		PrintWriter writer = response.getWriter();
		// 获取参数
		String flag = request.getParameter("flag");
		String manufacturid = request.getParameter("manufacturid");
		String userInput=request.getParameter("input");
		//
		
		ResponseObject result=null;
		GoodsDao goodsDao = new GoodsDaoImpl();
		if (flag.equals("get")) {
			
			List<Goods> list = goodsDao.getGoods(manufacturid);
			result = null;
			if (list != null && list.size() > 0) {
				result = new ResponseObject(1, list);

			} else {
				result = new ResponseObject(0, "没有商品！");
			}
		}else if (flag.equals("search")) {
			
			if (StringUtils.isNotBlank(userInput)) {
				List<Goods> list = goodsDao.getGoods2(userInput);
				result = null;
				if (list != null && list.size() > 0) {
					result = new ResponseObject(1, list);

				} else {
					result = new ResponseObject(0, "没有查到");
				}
			}else {
				result = new ResponseObject(0, "请输入你搜索条件！");
			}
		}
		writer.println(new GsonBuilder().create().toJson(result));
		writer.flush();
		writer.close();

	}

}
