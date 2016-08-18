package com.goophone.dao.impl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.goophone.dao.GoodsDao;
import com.goophone.enity.Goods;

public class GoodsDaoImpl extends BaseDao implements GoodsDao {
	Connection connection = null;
	Statement statement = null;
	ResultSet resultSet = null;
	List<Goods> goodss = null;
	public List<Goods> getGoods(String id) {

		
		try {
			connection = getConn();
			statement = connection.createStatement();
			//com.mysql.jdbc.exceptions.jdbc4.MySQLSyntaxErrorException 
			//select * from news order by news_id limit0,1
			//select * from news order by news_id limit 0,1
			String sql = "select * from product where manufacturer_id='"+id+"'";
			//
			System.out.println(sql);
			resultSet = statement.executeQuery(sql);
			goodss = new ArrayList<Goods>();
			// 遍历resultset
			while (resultSet.next()) {
				Goods goods = new Goods();
				goods.setId(resultSet.getString("product_id"));
				goods.setTitle(resultSet.getString("product_title"));
				goods.setManufacturName(resultSet.getString("manufacturer_name"));
				goods.setPrice(resultSet.getString("product_price"));
				goods.setGoodDetails(resultSet.getString("product_details"));
				goods.setImageUrl1(resultSet.getString("product_image1"));
				goods.setImageUrl2(resultSet.getString("product_image2"));
				goods.setImageUrl3(resultSet.getString("product_image3"));
				goods.setDetailsUrl(resultSet.getString("product_details_image"));
				goodss.add(goods);

			}
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} finally {
			close(resultSet, statement, connection);
		}
		return goodss;
	}

	public List<Goods> getGoods2(String userInput) {
		try {
			connection = getConn();
			statement = connection.createStatement();
			//com.mysql.jdbc.exceptions.jdbc4.MySQLSyntaxErrorException 
			//select * from news order by news_id limit0,1
			//select * from news order by news_id limit 0,1
			String sql = "select * from product where product_title like'%"+userInput+"%'";
			//
			System.out.println(sql);
			resultSet = statement.executeQuery(sql);
			goodss = new ArrayList<Goods>();
			// 遍历resultset
			while (resultSet.next()) {
				Goods goods = new Goods();
				goods.setId(resultSet.getString("product_id"));
				goods.setTitle(resultSet.getString("product_title"));
				goods.setManufacturName(resultSet.getString("manufacturer_name"));
				goods.setPrice(resultSet.getString("product_price"));
				goods.setGoodDetails(resultSet.getString("product_details"));
				goods.setImageUrl1(resultSet.getString("product_image1"));
				goods.setImageUrl2(resultSet.getString("product_image2"));
				goods.setImageUrl3(resultSet.getString("product_image3"));
				goods.setDetailsUrl(resultSet.getString("product_details_image"));
				goodss.add(goods);

			}
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} finally {
			close(resultSet, statement, connection);
		}
		return goodss;
	}

	

}
