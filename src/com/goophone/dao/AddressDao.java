package com.goophone.dao;

import java.util.List;

import com.goophone.enity.Address;

public interface AddressDao {

	public List<Address> getAddress(String userPhone);  // 获取地址信息

	public Address updataAddress(String userPhone, String name, String phone,
			String address);//更新地址信息
    
	
}
