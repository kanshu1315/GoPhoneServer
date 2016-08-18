package com.goophone.dao;

import java.util.List;

import com.goophone.enity.Favorite;

public interface FavoriteDao {
	//获取收藏
	 List<Favorite> getFavoriteList(String userPhone) ;
	 boolean deleteAFavorite(String userPhone,String productId) ;
	 boolean addAFavorite(String userPhone,String productId) ;
	 boolean isAFavorite(String userPhone,String productId) ;
}
