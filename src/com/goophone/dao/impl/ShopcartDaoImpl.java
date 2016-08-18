package com.goophone.dao.impl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.goophone.dao.ShopcartDao;
import com.goophone.enity.ShopcartDetails;

public class ShopcartDaoImpl extends BaseDao implements ShopcartDao {

	public List<ShopcartDetails> getShopcartList(String userPhone) {
		Connection connection1 = null;

		Statement statement1 = null;

		ResultSet resultSet_shopcart = null;
		List<ShopcartDetails> list = null;
		try {
			connection1 = getConn();
			statement1 = connection1.createStatement();
			// String sqlForGet = "select * from shopcart where user_phone='"
			// + userPhone + "'order by shopcart_id";
			// String sql =
			// "select * from product where product_id in(select product_id from shopcart where product_id='"
			// + userPhone + "')";

			// 初始化list java.lang.NullPointerException
			list = new ArrayList<ShopcartDetails>();
			String sql = "select product.product_title,product.product_price,product.product_id,"
					+ "product.product_image1,product.product_image2,product.product_image3,shopcart.shopcart_id,shopcart.count,shopcart.time from product,shopcart"
					+ " where product.product_id = shopcart.product_id and shopcart.user_phone='"
					+ userPhone + "'";
			// System.out.println("ShopcartDaoImpl_----"+Sequel);

			resultSet_shopcart = statement1.executeQuery(sql);
			// resultSet_shopcart = statement2.executeQuery(sqlForGet);
			while (resultSet_shopcart.next()) {

				ShopcartDetails shopcartDetails = new ShopcartDetails();

				shopcartDetails.setImageUrl1(resultSet_shopcart
						.getString("product_image1"));
				shopcartDetails.setImageUrl2(resultSet_shopcart
						.getString("product_image2"));
				shopcartDetails.setImageUrl3(resultSet_shopcart
						.getString("product_image3"));
				shopcartDetails.setProductId(resultSet_shopcart
						.getString("product_id"));
				shopcartDetails.setId(resultSet_shopcart
						.getString("shopcart_id"));
				// System.out.println("product_id---"+resultSet_shopcart.getString("product_id"));

				shopcartDetails.setProductTitle(resultSet_shopcart
						.getString("product_title"));
				shopcartDetails.setPrice(resultSet_shopcart
						.getString("product_price"));

				shopcartDetails.setCount(resultSet_shopcart.getString("count"));
				shopcartDetails.setTime(resultSet_shopcart.getString("time"));

				list.add(shopcartDetails);

			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(resultSet_shopcart, statement1, connection1);
		}

		return list;
	}

	public int setShopcartList(String userPhone, String productId, String count) {
		Connection connection = null;
		Statement statement = null;
		ResultSet resultSet = null;

		try {
			connection = getConn();
			statement = connection.createStatement();
			// 插入数据
			String sqlForAdd = "insert into shopcart(user_phone,product_id,count,time) values('"
					+ userPhone
					+ "','"
					+ productId
					+ "','"
					+ count
					+ "',now())";
			System.out.println(sqlForAdd);
			// Numb表示执行后数据库的影响行数，如果a的值大于0，表示执行操作成功；

			return statement.executeUpdate(sqlForAdd);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(resultSet, statement, connection);
		}

		return 0;
	}

	public int deleteAShopCart(String shopcartId) {
		Connection connection = null;
		Statement statement = null;
		ResultSet resultSet = null;

		try {
			connection = getConn();
			statement = connection.createStatement();
			// 插入数据
			String sqlForDel = "DELETE FROM shopcart WHERE shopcart_id IN("+shopcartId+")";
			// Numb表示执行后数据库的影响行数，如果a的值大于0，表示执行操作成功；

			return statement.executeUpdate(sqlForDel);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(resultSet, statement, connection);
		}
		return 0;
	}

}
