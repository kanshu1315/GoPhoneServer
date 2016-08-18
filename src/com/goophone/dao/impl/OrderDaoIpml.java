package com.goophone.dao.impl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.goophone.dao.OrderDao;
import com.goophone.enity.Address;
import com.goophone.enity.Goods;
import com.goophone.enity.Order;
import com.goophone.enity.OrderDetail;
import com.goophone.util.MyUtils;

/**
 * 
 * @author Administrator
 * 
 */
public class OrderDaoIpml extends BaseDao implements OrderDao {

	Connection connection = null;
	Statement statement = null;
	ResultSet resultSet = null;

	public List<OrderDetail> getOrdersList(String userPhone) {
		List<OrderDetail> list = new ArrayList<OrderDetail>();
		try {
			connection = getConn();
			statement = connection.createStatement();
			String sql = "select * from ome where user_phone='" + userPhone
					+ "' order by order_built_date desc";
			// String sql = "select * from ome,ome_detail where user_phone='"
			// + userPhone + "'and ome.order_id=ome_detail.order_id";
			System.out.println(sql);
			resultSet = statement.executeQuery(sql);
			while (resultSet.next()) {
				OrderDetail detail = new OrderDetail();
				detail.setOrderId(resultSet.getString("order_id"));
				detail.setPayDate(resultSet.getString("order_pay_date"));
				detail.setOrderDate(resultSet.getString("order_built_date"));
				detail.setCompleteDate(resultSet
						.getString("order_complete_date"));
				detail.setPayState(resultSet.getString("order_paystate"));
				detail.setPrice(resultSet.getString("order_price"));
				detail.setAddressId(resultSet.getString("address_id"));
				list.add(detail);
			}
			// String sqlString =
			// "select * from product where product in(select product_id from ome where user_phone='"
			// + userPhone + "')";

		} catch (Exception e) {
			// TODO: handle exception
		} finally {
			close(resultSet, statement, connection);
		}
		return list;
	}

	public Order sendOrderDetail(String userPhone, String addressId,
			String price, String productId) {

		try {
			connection = getConn();
			statement = connection.createStatement();
			// String sql =
			// "insert into order(user_phone,address_id,order_price,product_id,order_date) values('"
			// + userPhone
			// + "','"
			// + addressId
			// + "','"
			// + price
			// + "','"
			// + productId
			// + "',now())";
			/**
			 * 
			 * order 是mysql 的关键字
			 * 
			 * 
			 */

			String sqlForInsert = "insert into ome(user_phone,address_id,order_price,product_id,order_built_date) values('"
					+ userPhone
					+ "','"
					+ addressId
					+ "','"
					+ price
					+ "','"
					+ productId + "'," + "now())";
			System.out.println(sqlForInsert);

			// 执行插入语句，并返回新纪录的主键
			statement.executeUpdate(sqlForInsert,
					Statement.RETURN_GENERATED_KEYS);
			Order order = new Order();
			resultSet = statement.getGeneratedKeys(); // 获取自增主键！
			String order_id = null;
			if (resultSet.next()) {
				order_id = resultSet.getString(1);
				order.setId(order_id);
				resultSet = statement
						.executeQuery("select order_price from ome where order_id='"
								+ order_id + "'");
				if (resultSet.next()) {
					order.setPrice(resultSet.getString("order_price"));
				}

			}

			// String[] strings = MyUtils.stringToStrArray(productId, ',');
			// for (String string : strings) {
			// String sql2 =
			// "insert into ome_detail(order_id,product_id) values('"
			// + order_id + "','" + string + "')";
			// statement.executeUpdate(sql2);
			// }

			return order;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(resultSet, statement, connection);
		}
		return null;
	}

	public int payForOrder(String phone, String orderId, String payPwd) {
		try {
			connection = getConn();
			statement = connection.createStatement();
			String payPassWordFromDB = null;// 用于保存从数据库获取的支付密码
			int success = 0;
			// 从数据库获取用户的支付密码
			String sqlForGetPayPwd = "select user_pay_pwd from user where user_phone='"
					+ phone + "'";
			System.out.println("----" + sqlForGetPayPwd);
			resultSet = statement.executeQuery(sqlForGetPayPwd);
			while (resultSet.next()) {
				payPassWordFromDB = resultSet.getString("user_pay_pwd");
			}
			if (payPassWordFromDB != null && payPassWordFromDB.equals(payPwd)) {// 如果匹配
				// 将订单状态改为1，表示已经支付

				String sqlForUpdateOrderState = "update ome set order_paystate='1',order_pay_date =now() where order_id='"
						+ orderId + "'";
				System.out.println(sqlForUpdateOrderState);
				success = statement.executeUpdate(sqlForUpdateOrderState);
				return success;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(resultSet, statement, connection);
		}
		return 0;
	}

	public List<Goods> getGoodList(String orderId) {
		List<Goods> list = new ArrayList<Goods>();
		try {
			connection = getConn();
			statement = connection.createStatement();
			String sql = "select product_image1,product_title,product_price from product where product_id in (select product_id from ome where order_id='"
					+ orderId + "')";
			// String sql = "select * from ome,ome_detail where user_phone='"
			// + userPhone + "'and ome.order_id=ome_detail.order_id";

			resultSet = statement.executeQuery(sql);
			while (resultSet.next()) {
				Goods goods = new Goods();
				goods.setImageUrl1(resultSet.getString("product_image1"));
				goods.setPrice(resultSet.getString("product_price"));
				goods.setTitle(resultSet.getString("product_title"));

				list.add(goods);
			}
			// String sqlString =
			// "select * from product where product in(select product_id from ome where user_phone='"
			// + userPhone + "')";

		} catch (Exception e) {
			// TODO: handle exception
		} finally {
			close(resultSet, statement, connection);
		}
		return list;
	}

//	public Address getAddress(String addressId) {
//		Address address = new Address();
//		try {
//			connection = getConn();
//			statement = connection.createStatement();
//			String sql = "select * from address where address_id ='"
//					+ addressId + "'";
//
//			resultSet = statement.executeQuery(sql);
//			if (resultSet.next()) {
//				address.setPhone(resultSet.getString("address_phone"));
//				address.setAddres(resultSet.getString("address_datail"));
//				address.setName(resultSet.getString("address_name"));
//			}
//
//		} catch (Exception e) {
//			// TODO: handle exception
//		} finally {
//			close(resultSet, statement, connection);
//		}
//		return address;
//	}

	public int delOrder(String orderid) {
		try {
			connection = getConn();
			statement = connection.createStatement();
			String sql = "delete from ome where order_id ='" + orderid + "'";

			return statement.executeUpdate(sql);

		} catch (Exception e) {
			// TODO: handle exception
		} finally {
			close(resultSet, statement, connection);
		}
		return 0;
	}

	public int uodateForSure(String orderId) {
		try {
			connection = getConn();
			statement = connection.createStatement();
			String sql = "update ome set order_paystate='2',order_complete_date =now() where order_id='"
					+ orderId + "'";

			return statement.executeUpdate(sql);

		} catch (Exception e) {
			// TODO: handle exception
		} finally {
			close(resultSet, statement, connection);
		}
		return 0;
	}

}
