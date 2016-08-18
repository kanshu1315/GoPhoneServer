package com.goophone.dao.impl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.goophone.dao.NewsDao;
import com.goophone.enity.News;

public class NewsDaoImpl extends BaseDao implements NewsDao {

	public List<News> getNews(int page, int size) {
		Connection connection = null;
		Statement statement = null;
		ResultSet resultSet = null;
		List<News> newss = null;
		try {
			connection = getConn();// ��ȡ��ݿ����Ӷ���
			statement = connection.createStatement();
			// com.mysql.jdbc.exceptions.jdbc4.MySQLSyntaxErrorException
			// select * from news order by news_id limit0,1
			// select * from news order by news_id limit 0,1
			String sql = "select * from news order by news_id desc limit" + " "
					+ ((page - 1) * size) + ',' + size; // 从第几条记录开始，查询几条 ,倒叙排列
			//
			System.out.println(sql);
			resultSet = statement.executeQuery(sql);
			newss = new ArrayList<News>();
	
			while (resultSet.next()) {
				News news = new News();
				news.setId(resultSet.getString("news_id"));
				news.setTitle(resultSet.getString("news_title"));
				news.setImgUrl(resultSet.getString("news_img"));
				news.setUpdataTime(resultSet.getString("news_time"));
				news.setBody(resultSet.getString("news_body"));
				newss.add(news);

			}
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} finally {
			close(resultSet, statement, connection);
		}
		return newss;
	}

	public int getCount() {
		Connection connection = null;
		Statement statement = null;
		ResultSet resultSet = null;
		// List<News> newss = null;
		int count = 0;
		try {
			connection = getConn();// ��ȡ��ݿ����Ӷ���
			statement = connection.createStatement();
			String sql = "select * from news order by news_id";
			resultSet = statement.executeQuery(sql);
			while (resultSet.next()) {// ��ȡ�ܵ����ŵ�����
				count++;
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(resultSet, statement, connection);
		}
		return count;
	}

}
