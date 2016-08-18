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
			//��ȡ��Դ�����ļ�
			ResourceBundle bundle = ResourceBundle.getBundle("config");
			String driverString = bundle.getString("driver");
			//��ȡ������Դ�ļ��еĲ���
			url = bundle.getString("url");
			user = bundle.getString("user");
			password = bundle.getString("password");
			// ��������
			Class.forName(driverString);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// ��ȡ���ݿ�����
	protected Connection getConn() throws Exception {

		return new Inner().getConn();

	}

	// �رյ�ǰ�����ݿ����Ӷ���
	protected void close(ResultSet resultSet, Statement statement,
			Connection connection)  {
		new Inner().close(resultSet, statement, connection);

	}

	// ����Inner��
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
