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
import com.goophone.dao.AddressDao;
import com.goophone.dao.impl.AddressDaoIpml;
import com.goophone.enity.Address;
import com.goophone.enity.ResponseObject;

public class AddressServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// 编码格式
		response.setContentType("text/html");
		response.setCharacterEncoding("UTF-8");
		PrintWriter writer = response.getWriter();

		// 获取请求参数
		String userPhone = request.getParameter("userphone");
		String flag = request.getParameter("flag");
		String address_phone = request.getParameter("phone");
		String address_detail = request.getParameter("address");
		String address_name = request.getParameter("name");
		AddressDao addressDao = new AddressDaoIpml();
		ResponseObject result = null;
		if (flag.equals("get")) {// 
			System.out.println("AddressServlet   get");
			List<Address> list = addressDao.getAddress(userPhone);

			if (list != null && list.size() > 0) {// 遍历
				result = new ResponseObject(1, list.get(0));

			} else {// 还没有数据
				result = new ResponseObject(0, "你还没有保存过数据！");

			}

		} else if (flag.equals("updata")) {// 更新
			if (StringUtils.isNotBlank(address_detail)
					&& StringUtils.isNotBlank(address_name)
					&& StringUtils.isNotBlank(address_phone)) {
				System.out.println(address_name);
				Address address = addressDao.updataAddress(userPhone,
						address_name, address_phone, address_detail);
				if (address != null) {//
					result = new ResponseObject(1,address);
				} else {
					result = new ResponseObject(0, "保存失败！");
				}

			} else {

				result = new ResponseObject(0, "所填信息不能为空，请填写完整！");
			}

		}

		writer.println(new GsonBuilder().create().toJson(result));
		writer.flush();
		writer.close();
	}
}
