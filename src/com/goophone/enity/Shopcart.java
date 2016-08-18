package com.goophone.enity;

public class Shopcart {
	private String shopcart_id;// id
	private String user_phone;// 用户手机
	private String product_id;// 商品ID
	private String count;// 商品数量
	private String time;//计入购物车的时间

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public String getCount() {
		return count;
	}

	public String getShopcart_id() {
		return shopcart_id;
	}

	public void setShopcart_id(String shopcartId) {
		shopcart_id = shopcartId;
	}

	public String getUser_phone() {
		return user_phone;
	}

	public void setUser_phone(String userPhone) {
		user_phone = userPhone;
	}

	public String getProduct_id() {
		return product_id;
	}

	public void setProduct_id(String productId) {
		product_id = productId;
	}

	public void setCount(String count) {
		this.count = count;
	}

}
