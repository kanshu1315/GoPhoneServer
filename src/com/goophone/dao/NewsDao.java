package com.goophone.dao;

import java.util.List;

import com.goophone.enity.News;

public interface NewsDao {

//	public List<News> getList(String manufacturerId,int page,int size);
//	
//	public double getCount(String manufacturerId);
	
	//��ȡNews�б�
	public List<News> getNews(int page,int size);
	
	public int  getCount() ;
	
}
