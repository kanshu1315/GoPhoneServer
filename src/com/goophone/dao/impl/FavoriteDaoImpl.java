package com.goophone.dao.impl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.goophone.dao.FavoriteDao;
import com.goophone.enity.Favorite;

public class FavoriteDaoImpl extends BaseDao implements FavoriteDao {

	public List<Favorite> getFavoriteList(String userPhone) {
		Connection connection = null;
		Statement statement = null;
		ResultSet resultSet = null;
		List<Favorite> favorites = null;
		try {
			connection = getConn();
			statement = connection.createStatement();
			// com.mysql.jdbc.exceptions.jdbc4.MySQLSyntaxErrorException
			// select * from news order by news_id limit0,1
			// select * from news order by news_id limit 0,1
			String sql = "select * from favorite where user_phone='"
					+ userPhone + "'";
			// String sql = "select * from favorite where user_phone='"
			// + userPhone + "','"+"product_id='"+productId+"'";
			//
			System.out.println(sql);
			resultSet = statement.executeQuery(sql);
			favorites = new ArrayList<Favorite>();
			// 遍历resultset
			while (resultSet.next()) {
				Favorite favorite = new Favorite();
				favorite.setFacoriteId(resultSet.getString("favorite_id"));
				favorite.setUserPhone(resultSet.getString("user_phone"));
				favorite.setProductId(resultSet.getString("product_id"));
				favorite.setTime(resultSet.getString("favorite_time"));
				favorites.add(favorite);

			}
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} finally {
			close(resultSet, statement, connection);
		}
		return favorites;
	}

	public boolean addAFavorite(String userPhone, String productId) {
		Connection connection = null;
		Statement statement = null;
		ResultSet resultSet = null;
		boolean isAdd = false;
		try {
			connection = getConn();
			statement = connection.createStatement();

			String sqlForAdd = "insert into user(user_phone,product_id) values('"+userPhone+"','"+productId+"')";

			isAdd = statement.execute(sqlForAdd);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(resultSet, statement, connection);
		}
		return isAdd;
	}

	public boolean deleteAFavorite(String userPhone, String productId) {
		Connection connection = null;
		Statement statement = null;
		ResultSet resultSet = null;
		boolean isDelete = false;
		try {
			connection = getConn();
			statement = connection.createStatement();
			String sqlForDelete = "delete from favorite where user_phone='"
					+ userPhone + "','" + "product_id='" + productId + "'";
			isDelete = statement.execute(sqlForDelete);

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(resultSet, statement, connection);
		}

		return isDelete;
	}

	public boolean isAFavorite(String userPhone, String productId) {
		// TODO Auto-generated method stub
		return false;
	}

}
