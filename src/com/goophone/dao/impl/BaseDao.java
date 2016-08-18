package com.goophone.dao.impl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ResourceBundle;

import com.goophone.dao.Dao;

public class BaseDao {
	private static String url = null;
	private static String user = null;
	private static String password = null;

	static {
		try {
			//获取资源配置文件
			ResourceBundle bundle = ResourceBundle.getBundle("config");
			String driverString = bundle.getString("driver");
			//获取配置资源文件中的参数
			url = bundle.getString("url");
			user = bundle.getString("user");
			password = bundle.getString("password");
			// 加载驱动
			Class.forName(driverString);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// 获取数据库连接
	protected Connection getConn() throws Exception {

		return new Inner().getConn();

	}

	// 关闭当前的数据库连接对象
	protected void close(ResultSet resultSet, Statement statement,
			Connection connection)  {
		new Inner().close(resultSet, statement, connection);

	}

	// 创建Inner类
	private class Inner implements Dao {

		public Connection getConn() throws Exception {
			return DriverManager.getConnection(url, user, password);
		}

		public void close(ResultSet resultSet, Statement statement,
				Connection connection)  {

			if (resultSet != null) {
				try {
					resultSet.close();
					resultSet = null;
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			if (statement != null) {
				try {
					statement.close();
					statement = null;
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			if (connection != null) {
				try {
					connection.close();
					connection = null;
				} catch (Exception e) {
					e.printStackTrace();
				}
			}

		}

	}
}
