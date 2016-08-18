package com.goophone.dao;

import java.util.List;

import com.goophone.enity.Address;
import com.goophone.enity.Goods;
import com.goophone.enity.Order;
import com.goophone.enity.OrderDetail;

public interface OrderDao {
	/**
	 * 上传订单
	 * 
	 * @param userPhone
	 * @param addressId
	 * @param price
	 * @param payState
	 * @return
	 */
	Order sendOrderDetail(String userPhone, String addressId, String price,
			String productId);

	/**
	 * 获取订单列表
	 * 
	 * @param userPhone
	 * @return
	 */
	List<OrderDetail> getOrdersList(String userPhone);

	/**
	 * 用于完成订单支付
	 * 
	 * @param phone
	 *            帐号
	 * @param orderId
	 *            订单id
	 * @param payPwd
	 *            支付密码
	 * @return 
	 */
	int payForOrder(String phone, String orderId, String payPwd);
	
	/**
	 * 
	 * @param order_id
	 * @return
	 */
	List<Goods> getGoodList(String order_id);
	/**
	 * 
	 * @param address_id
	 * @return
	 */
	//Address getAddress(String address_id);
	/**
	 * 删除订单
	 * @param orderid
	 * @return
	 */
	int delOrder(String orderid);
	
	int uodateForSure(String order_id);

}
