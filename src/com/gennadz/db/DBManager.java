package com.gennadz.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public class DBManager {
	Connection conn;
	Statement stat;
	
	public DBManager() throws ClassNotFoundException, SQLException {
		Class.forName("org.sqlite.JDBC");
	    conn =
	      DriverManager.getConnection("jdbc:sqlite:test.db");
	    stat = conn.createStatement();
	}
	
	public void createTableIfNotExists() throws SQLException {
		
		stat.executeUpdate("create table if not exists hosts (id INTEGER PRIMARY KEY, name TEXT, url TEXT);");
	}
	
	public void dropTableIfExists() throws SQLException {
		stat.executeUpdate("drop table if exists hosts;");
	}
	
	public void insertToTable(String name, String url) throws ClassNotFoundException, SQLException {
		PreparedStatement prep = conn.prepareStatement(
	      "insert into hosts values (NULL, ?, ?);");
	    prep.setString(1, "vk");
	    prep.setString(2, "vk.net");
	    prep.addBatch();
	    prep.setString(1, "fb");
	    prep.setString(2, "facebook.ru");
	    prep.addBatch();
	    conn.setAutoCommit(false);
	    prep.executeBatch();
	    conn.setAutoCommit(true);
	}
	
	public void selectFromTable() throws SQLException {
		ResultSet rs = stat.executeQuery("select * from hosts;");
	    while (rs.next()) {
	      System.out.println("name = " + rs.getString("name"));
	      System.out.println("url = " + rs.getString("url"));
	    }
	    rs.close();
	}
	
	/**
	 * Very simple implementation of update method
	 * 
	 * 
	 * @param tableName
	 * @param data
	 * @param whereClause
	 * @throws SQLException 
	 */
	public void updateFromTable(String tableName, Map<String, Object> data, Map<String, Object> where) throws SQLException {
		conn.setAutoCommit(false);
		Set<String> keySet = data.keySet();
		Iterator it = keySet.iterator();
		String updateClause = "";
		while(it.hasNext()) {
			String keyName = (String) it.next();
			String valueName = (String) data.get(keyName);
			if (updateClause.equals("")) {
				updateClause = keyName+"="+valueName;
			} else {
				updateClause = ", "+keyName+"="+valueName;
			}
		}
		
		keySet = where.keySet();
		it=keySet.iterator();
		String whereClause = " where ";
		while (it.hasNext()) {
			String keyName = (String) it.next();
			String valueName = (String) data.get(keyName);
			
			if (whereClause.equals(" where ")) {
				whereClause = keyName+"="+valueName;
			} else {
				whereClause = ", "+keyName+"="+valueName;
			}
		}
		
		stat.executeQuery("update "+tableName+" set 'fieldName'=1 where userid=1;");
		
		conn.commit();
	}
	
	protected void finalize() {
		try {
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
