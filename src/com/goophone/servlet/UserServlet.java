package com.goophone.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;

import com.google.gson.GsonBuilder;
import com.goophone.dao.UserDao;
import com.goophone.dao.impl.UserDaoImpl;
import com.goophone.enity.ResponseObject;
import com.goophone.enity.User;

public class UserServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html");
		response.setCharacterEncoding("UTF-8");
		PrintWriter writer = response.getWriter();
		// Map<String, String> map = request.getParameterMap();
		// String name = map.get("username");
		// String password = map.get("password");
		// String flag = map.get("flag");
		String phone = request.getParameter("phone");
		String loginPassword = request.getParameter("loginpassword");
		String payPassword = request.getParameter("paypassword");
		String name = request.getParameter("username");
		String flag = request.getParameter("flag");
		UserDao userDao = new UserDaoImpl();
		ResponseObject result = null;
		// http://192.168.1.101:8080//GooPhoneServer/api/user?flag=login&username=zhangsan&password=123456
		if ("login".equals(flag)) {
			if (StringUtils.isNotBlank(phone)
					&& StringUtils.isNotBlank(loginPassword)) {

				User user = userDao.login(phone, loginPassword);
				if (user != null) {
					result = new ResponseObject(1, "登录成功！", user);
				} else {
					result = new ResponseObject(0, "手机号或密码错误！");

				}
			} else {
				result = new ResponseObject(0, "手机号或密码不能为空！");
			}
		} else if ("register".equals(flag)) {
			if (StringUtils.isNotBlank(phone)
					&& StringUtils.isNotBlank(loginPassword)
					&& StringUtils.isNotBlank(name)
					&& StringUtils.isNotBlank(payPassword)) {
				System.out.println(phone + "--" + name + "---" + loginPassword);
				User user = userDao.registe(name, loginPassword, payPassword,
						phone);
				if (user != null) {
					result = new ResponseObject(1, "注册成功！", user);
				} else {
					result = new ResponseObject(0, "手机号已被注册，请更换手机号！");

				}
			} else {
				result = new ResponseObject(0, "信息不能为空");
			}
		}
		writer.print(new GsonBuilder().create().toJson(result));
		writer.flush();
		writer.close();
	}
}
