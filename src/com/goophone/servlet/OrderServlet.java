package com.goophone.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;

import com.google.gson.GsonBuilder;
import com.goophone.dao.OrderDao;
import com.goophone.dao.impl.OrderDaoIpml;
import com.goophone.enity.Address;
import com.goophone.enity.Goods;
import com.goophone.enity.Order;
import com.goophone.enity.OrderDetail;
import com.goophone.enity.ResponseObject;

public class OrderServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8787121377995728879L;

	@Override
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		// 编码格式
		response.setContentType("text/html");
		response.setCharacterEncoding("UTF-8");
		PrintWriter writer = response.getWriter();

		// 获取请求参数
		String flag = request.getParameter("flag");
		String userPhone = request.getParameter("userphone");
		String addressId = request.getParameter("addressid");
		String productId = request.getParameter("productid");
		String orderId = request.getParameter("orderid");
		String payPwd = request.getParameter("paypassword");
		String price = request.getParameter("price");
		String addressid = request.getParameter("addressid");

		OrderDao orderDao = new OrderDaoIpml();
		ResponseObject result = null;
		if (flag.equals("send")) {// 上传数据
			System.out.println("OrderServlet   sent");
			if (StringUtils.isNotBlank(userPhone)
					&& StringUtils.isNotBlank(addressId)
					&& StringUtils.isNotBlank(productId)
					&& StringUtils.isNotBlank(price)) {
				System.out.println("OrderServlet   isnotbank");
				Order order = orderDao.sendOrderDetail(userPhone, addressId,
						price, productId);

				if (order != null) {//
					result = new ResponseObject(1, order);
				} else {
					result = new ResponseObject(0, "提交失败！");
				}

			} else {

				result = new ResponseObject(0, "请求参数有误！");
			}
		} else if (flag.equals("get")) {
			if (StringUtils.isNotBlank(userPhone)) {
				System.out.println("OrderServlet---get");
				List<OrderDetail> list = orderDao.getOrdersList(userPhone);
				if (list != null && list.size() > 0) {
					result = new ResponseObject(1, list);
				} else {
					result = new ResponseObject(0, "没有订单信息！");
				}

			} else {
				result = new ResponseObject(0, "请求参数有误！");
			}

		} else if ("pay".equals(flag)) {
			if (StringUtils.isNotBlank(payPwd)) {
				int success = orderDao.payForOrder(userPhone, orderId, payPwd);
				if (success > 0) {//
					result = new ResponseObject(1, "支付成功！" + success);
				} else {
					result = new ResponseObject(0, "支付失败！");
				}

			} else {

				result = new ResponseObject(0, "支付密码为空！");
			}
		} else if ("getgoods".equals(flag)) {
			System.out.println(flag+"efefef"+orderId);

			List<Goods> goods = orderDao.getGoodList(orderId);
		//	Address address = orderDao.getAddress(addressid);
			if (goods != null && goods.size() > 0 ) {
				OrderDetail detail = new OrderDetail(goods, null);
				result = new ResponseObject(1, detail);
			}else {
				result =new ResponseObject(0, "获取失败！");
			}

		} else if ("delete".equals(flag)) {
			int success = orderDao.delOrder(orderId);

			if (success > 0) {
				result = new ResponseObject(1, "操作成功!");
			} else {
				result = new ResponseObject(0, "操作失败!");
			}

		} else if ("sureorder".equals(flag)) {
			int success = orderDao.uodateForSure(orderId);

			if (success > 0) {
				result = new ResponseObject(1, "操作成功!");
			} else {
				result = new ResponseObject(0, "操作失败!");
			}
		}

		writer.println(new GsonBuilder().create().toJson(result));
		writer.flush();
		writer.close();
	}
}
