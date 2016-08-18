package com.goophone.dao;

import com.goophone.enity.User;


public interface UserDao {
	//登录
	//public List<User> getUser();
	
	//��¼�ķ���
	User login(String phone,String loginPassword);
	User registe(String name,String loginPassword,String payPassword,String phone);
}
