package com.goophone.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public interface Dao{
	//获取连接
	Connection getConn() throws Exception;
	//关闭资源
	void close(ResultSet resultSet,Statement statement,Connection connection)throws Exception;
	
	
}
