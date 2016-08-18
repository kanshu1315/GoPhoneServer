package com.goophone.dao.impl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import com.goophone.dao.UserDao;
import com.goophone.enity.User;

public class UserDaoImpl extends BaseDao implements UserDao {
	
	public User login(String phone, String loginPassword) {
		Connection connection = null;
		Statement statement = null;
		ResultSet resultSet = null;
		try {
			connection = getConn();
			statement = connection.createStatement();
			String sqlForCheck = "select * from user where user_phone='" + phone
					+ "'and user_login_pwd='" + loginPassword + "'";
			resultSet = statement.executeQuery(sqlForCheck);
			if(resultSet.next()) {
				User user = new User();
			
				user.setUserName(resultSet.getString("user_name"));
				user.setLoginPassword(resultSet.getString("user_login_pwd"));
				user.setPhone(resultSet.getString("user_phone"));
				return user;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(resultSet, statement, connection);
		}

		return null;
	}

	public User registe(String name, String loginPassword,String payPassword, String phone) {
		Connection connection = null;
		Statement statement = null;
		ResultSet resultSet = null;
		try {

			connection = getConn();
			statement = connection.createStatement();
			String SqlForCheck = "select * from user where user_phone='" +phone+"'";
			resultSet= statement.executeQuery(SqlForCheck);
			if (resultSet.next()) {//检查手机号是否已经注册
				return null;
			}
			              
			// String sql="insert into mysql.��Ա��(name,number,sex,email) values ('"+username+"','"+password+"','"+sex+"','"+email+"')";
			//"insert into user (user_name,user_login_pwd,user_phone) values('"+zhangws+"','"+121212+"','"+1323232323+"')��
			String sqlForInsert= "insert into user(user_name,user_login_pwd,user_phone,user_pay_pwd) values('"+name+"','"+loginPassword+"','"+phone+"','"+payPassword+"')";
			statement.execute(sqlForInsert);//
			resultSet= statement.executeQuery(SqlForCheck);//���ڼ���Ƿ����ɹ�
			while(resultSet.next()) {
				User user = new User();
				user.setUserName(resultSet.getString("user_name"));
				user.setPhone(resultSet.getString("user_phone"));
				user.setLoginPassword(resultSet.getString("user_login_pwd"));
				user.setLoginPassword(resultSet.getString("user_pay_pwd"));
			    return user;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(resultSet, statement, connection);
		}
		return null;
	}

}
