package com.goophone.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public interface Dao{
	//��ȡ����
	Connection getConn() throws Exception;
	//�ر���Դ
	void close(ResultSet resultSet,Statement statement,Connection connection)throws Exception;
	
	
}
