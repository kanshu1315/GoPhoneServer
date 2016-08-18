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
import com.goophone.dao.ShopcartDao;
import com.goophone.dao.impl.ShopcartDaoImpl;
import com.goophone.enity.ResponseObject;
import com.goophone.enity.ShopcartDetails;

public class ShopcartServlet extends HttpServlet {

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

		String flag = request.getParameter("flag");
		String userPhone = request.getParameter("userphone");
		String productId = request.getParameter("productid");
		String count = request.getParameter("count");
		String shopcartId = request.getParameter("shopcartid");

		ShopcartDao shopcartDao = new ShopcartDaoImpl();
		ResponseObject result = null;
		if ("add".equals(flag)) {
			if (StringUtils.isNotBlank(userPhone)) {
				System.out.println("shopcartServlet---add---" + userPhone);

				int num = shopcartDao.setShopcartList(userPhone, productId,
						count);
				if (num > 0) {// 成功
					result = new ResponseObject(1, "成功添加到购物车！");
				} else {
					result = new ResponseObject(0, "添加失败！");

				}
			} else {
				result = new ResponseObject(0, "您还没有登录！");
			}
		} else if ("delete".equals(flag)) {
			if (StringUtils.isNotBlank(shopcartId)) {
				System.out.println("shopcartServlet---add---" + userPhone);

				int num = shopcartDao.deleteAShopCart(shopcartId);
				if (num > 0) {// 成功
					result = new ResponseObject(1, "从购物车移除成功！");
				} else {
					result = new ResponseObject(0, "没有该条记录！");

				}
			} else {
				result = new ResponseObject(0, "移除失败！");
			}

		} else if ("get".equals(flag)) {

			if (StringUtils.isNotBlank(userPhone)) {
				System.out.println("shopcartServlet---get---" + userPhone);

				List<ShopcartDetails> list = shopcartDao
						.getShopcartList(userPhone);

				if (list != null && list.size() > 0) {// 成功
					result = new ResponseObject(1, list);
				} else {
					result = new ResponseObject(0, "空荡荡的购物车！");

				}
			} else {
				result = new ResponseObject(0, "您还没有登录！");
			}

		}

		writer.print(new GsonBuilder().create().toJson(result));
		writer.flush();
		writer.close();
	}

}
