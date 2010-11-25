package com.gennadz.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DBManager {
	Connection conn;
	Statement stat;
	
	public DBManager() throws ClassNotFoundException, SQLException {
		Class.forName("org.sqlite.JDBC");
	    conn =
	      DriverManager.getConnection("jdbc:sqlite:test.db");
	    stat = conn.createStatement();
	}
	
	/**
	 * Returns the url that correctly spelled
	 * 
	 * It must has only one point, domain and extension parts.
	 * It mustn't have http or other prefixes
	 * @param url url to correct
	 * @return correct url
	 */
	public String createCorrectUrl(String url) {
		//seek final point
		int pos = url.lastIndexOf(".");
		Integer posEnd = null;
		//truncate last point
		String truncatedUrl = url.substring(0, pos);
		Pattern pattern = Pattern.compile("[^A-Za-z]");
		Matcher matcher = pattern.matcher(truncatedUrl);
		String result = "";
		if (matcher.find()) {
			posEnd = matcher.end();
			result = url.substring(posEnd);
		}
		return result;
	
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
	
	public void insertOrUpdate(String name, String url) {
		
	}
	
	public Map<String, String> selectFromTable(String whereClause) throws SQLException {
		if (!whereClause.equals("")) whereClause = " "+whereClause;
		ResultSet rs = stat.executeQuery("select * from hosts"+whereClause+";");
		Map<String, String> result = new HashMap<String, String>();
	    while (rs.next()) {
	    	String name = rs.getString("name");
	    	String value = rs.getString("url");
	    	result.put(name, value);	    
	    }
	    rs.close();
	    return result;
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
	public void update(Map<String, Object> data, String where, Object whereValue) throws SQLException {
		final String UPDATE_STATEMENT = "UPDATE hosts SET ";
		conn.setAutoCommit(false);
		Set<String> keySet = data.keySet();
		Iterator it = keySet.iterator();
		String updateClause = UPDATE_STATEMENT;
		while(it.hasNext()) {
			String keyName = (String) it.next();
			String valueName = (String) data.get(keyName);
			if (updateClause.equals(UPDATE_STATEMENT)) {
				updateClause = keyName+"="+valueName;
			} else {
				updateClause = ", "+keyName+"="+valueName;
			}
		}
		
		String whereClause = " where "+where+"="+whereValue+";";
		updateClause += whereClause;
		stat.executeQuery(updateClause);
		
		conn.commit();
		conn.setAutoCommit(true);
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
