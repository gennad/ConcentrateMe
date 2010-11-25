package com.gennadz.db;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.SQLException;

import org.junit.Test;

import junit.framework.TestCase;

public class DBManagerTests extends TestCase {
	@Test
	public void testCreateCorrectUrl() {
		 
		try {
			
			DBManager instance = new DBManager();
			Class dbmClass = instance.getClass();
			Class<String> c = String.class;
			Method method = dbmClass.getDeclaredMethod("createCorrectUrl", c);
			method.setAccessible(true);
			
			String result =  (String)method.invoke(instance, "http://www.gennad.ru");
			assertEquals("gennad.ru", result);
			
			result =  (String)method.invoke(instance, "www.gennad.ru");
			assertEquals("gennad.ru", result);
			
			result =  (String)method.invoke(instance, "https://www.gennad.ru");
			assertEquals("gennad.ru", result);
			
			result =  (String)method.invoke(instance, "svn://www.gennad.ru");
			assertEquals("gennad.ru", result);
			
			result =  (String)method.invoke(instance, "http://www.blog.gennad.ru");
			assertEquals("gennad.ru", result);
			
			result =  (String)method.invoke(instance, "www.blog.gennad.ru");
			assertEquals("gennad.ru", result);
			
			result =  (String)method.invoke(instance, "gennad.ru");
			assertEquals("gennad.ru", result);
			
			result =  (String)method.invoke(instance, "");
			assertEquals("", result);
			
			result =  (String)method.invoke(instance, "gennad");
			assertEquals("gennad", result);
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 
		 
	}
}
