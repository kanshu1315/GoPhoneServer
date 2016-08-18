package com.goophone.dao.impl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.goophone.dao.AddressDao;
import com.goophone.enity.Address;

public class AddressDaoIpml extends BaseDao implements AddressDao {
	Connection connection = null;
	Statement statement = null;
	ResultSet resultSet = null;

	/**
	 * 获取地址信息
	 * 
	 */
	public List<Address> getAddress(String userPhone) {
		List<Address> list = null;
		try {
			connection = getConn();
			statement = connection.createStatement();
			String sqlForGet = "select * from address where'" + userPhone + "'";
			resultSet = statement.executeQuery(sqlForGet);
			list = new ArrayList<Address>();
			while (resultSet.next()) {
				Address address = new Address();
				address.setId(resultSet.getString("address_id"));// 主键 id
				address.setName(resultSet.getString("address_name"));// 收件人姓名
				address.setPhone(resultSet.getString("address_phone"));// 收件人电话
				address.setUserPhone(resultSet.getString("user_phone"));// 用户电话
				address.setAddres(resultSet.getString("address_detail"));// 地址详情
				list.add(address);
			}

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			close(resultSet, statement, connection);
		}

		return list;
	}

	/**
	 * @author Administrator
	 * 
	 *         更新地址信息
	 * 
	 * 
	 */
	public Address updataAddress(String userPhone, String name, String phone,
			String address) {

		try {
			connection = getConn();
			statement = connection.createStatement();
			String sqlForDel = "delete from address where user_phone='"
					+ userPhone + "'";
			String sqlForUpdata = "insert into address(address_phone,address_name,user_phone,address_detail) values('"
					+ phone
					+ "','"
					+ name
					+ "','"
					+ userPhone
					+ "','"
					+ address + "')";
			String sqlForGet = "select * from address where user_phone ='"
					+ userPhone + "'";

			statement.executeUpdate(sqlForDel);// 删除表中的数据
			statement.executeUpdate(sqlForUpdata);// 返回数据更新影响的行数
			resultSet = statement.executeQuery(sqlForGet);

			while (resultSet.next()) {
                 Address address1 = new Address();
                 address1.setId(resultSet.getString("address_id"));
                 return address1;
			}

		} catch (Exception e) {
			// TODO: handle exception
		} finally {
			close(resultSet, statement, connection);
		}

		return null;
	}

}
