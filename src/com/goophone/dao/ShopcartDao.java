package com.goophone.dao;

import java.util.List;

import com.goophone.enity.ShopcartDetails;

public interface ShopcartDao {

	public List<ShopcartDetails> getShopcartList(String userPhone);
	public int setShopcartList(String userPhone,String productId,String count);
	public int deleteAShopCart(String shopcartId);
}
