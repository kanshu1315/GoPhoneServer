package com.goophone.dao.impl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.goophone.enity.Manufactur;
import com.goophone.dao.ManufacturDao;

public class ManufacturImpl extends BaseDao implements ManufacturDao {

	public List<Manufactur> getManufactur()  {
		Connection connection = null;
		Statement statement = null;
		ResultSet resultSet = null;
		List<Manufactur> manufacturs = null;
		try {
			connection = getConn();
			statement = connection.createStatement();
			resultSet = statement
					.executeQuery("select * from manufacturer order by manufacturer_identy");
			manufacturs = new ArrayList<Manufactur>();
			
			while (resultSet.next()) {
				Manufactur manufactur =new Manufactur();
				manufactur.setId(resultSet.getString("manufacturer_id"));
				manufactur.setName(resultSet.getString("manufacturer_name"));
				manufactur.setIdenty(resultSet.getString("manufacturer_identy"));
				manufacturs.add(manufactur);
				
			}
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}finally{
			close(resultSet, statement, connection);
		}
		return manufacturs;
	}

}
