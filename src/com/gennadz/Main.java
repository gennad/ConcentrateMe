package com.gennadz;

import java.sql.SQLException;

import com.gennadz.db.DBManager;

public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		try {
			DBManager dbm = new DBManager();
			dbm.createTableIfNotExists();
			dbm.insertToTable("qqq", "ya.ry");
			dbm.selectFromTable();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
