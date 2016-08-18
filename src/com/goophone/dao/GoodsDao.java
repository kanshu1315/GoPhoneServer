package com.goophone.dao;

import java.util.List;

import com.goophone.enity.Goods;

public interface GoodsDao  {
 
	
	 List<Goods> getGoods(String id);
	 
	 List<Goods> getGoods2(String userInput);
	 
}
