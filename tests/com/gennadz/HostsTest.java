/**
 * 
 */
package com.gennadz;

import java.io.File;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import org.junit.Before;
import org.junit.Test;

import junit.framework.TestCase;


/**
 * @author gennad
 *
 */
public class HostsTest extends TestCase{
	@Test
	public void testCreateTempFile() throws SecurityException, NoSuchMethodException, IllegalArgumentException, IllegalAccessException, InvocationTargetException {
		Hosts h = new Hosts();
		Class hostsClass = h.getClass();
		//Class<String> c = String.class;
		Method method = hostsClass.getDeclaredMethod("createTempFile", null);
		method.setAccessible(true);
		
		Boolean result =  (Boolean)method.invoke(h);
		Boolean t = new Boolean(true); //hack
	    assertEquals(t, result);
		
		
	}
	
	
	
	
	
	@Test
	public void testWriteStringToFile() throws SecurityException, NoSuchMethodException, IllegalArgumentException, IllegalAccessException, InvocationTargetException {
		Hosts h = new Hosts();
		Class hostsClass = h.getClass();
		//Class<String> c = String.class;
		Method method = hostsClass.getDeclaredMethod("createTempFile", null);
		method.setAccessible(true);
		Boolean result =  (Boolean)method.invoke(h);
		Boolean t = new Boolean(true); //hack
	    assertEquals(t, result);
	    
	    
		
	}
	
	@Test
	public void testDeleteTempFile() throws SecurityException, NoSuchMethodException, IllegalArgumentException, IllegalAccessException, InvocationTargetException, NoSuchFieldException {
		Hosts h = new Hosts();
		Class hostsClass = h.getClass();
		//Class<String> c = String.class;
		Method method = hostsClass.getDeclaredMethod("deleteFile", String.class);
		method.setAccessible(true);
		
		Field fieldName = hostsClass.getDeclaredField("tempFileName");
		fieldName.setAccessible(true);
		String tempFileName = (String) fieldName.get(h);
		Field fieldFile = hostsClass.getDeclaredField("tempFile");
		fieldFile.setAccessible(true);
		File fileTempFile = (File) fieldFile.get(h);
		fileTempFile = new File(tempFileName);
		
		Boolean result =  (Boolean)method.invoke(h, fileTempFile);
		Boolean t = new Boolean(true); //hack
	    assertEquals(t, result);
	}
	
	public void testGetAllHosts() {
		Hosts h = new Hosts();
		h.getAllHosts();
	}
	
	public void testCleanHost() throws SecurityException, NoSuchMethodException, IllegalArgumentException, IllegalAccessException, InvocationTargetException {
		Hosts h = new Hosts();
		Class hc = h.getClass();
		Method m = hc.getDeclaredMethod("cleanHost", String.class);
		m.setAccessible(true);
		String result =  (String)m.invoke(h, "http://www.yandex.ru");
		assertEquals("yandex.ru", result);
		
		result =  (String)m.invoke(h, "www.gennad.ru");
		assertEquals("gennad.ru", result);
		
		result =  (String)m.invoke(h, "https://www.gennad.ru");
		assertEquals("gennad.ru", result);
		
		result =  (String)m.invoke(h, "svn://www.gennad.ru");
		assertEquals("gennad.ru", result);
		
		result =  (String)m.invoke(h, "http://www.blog.gennad.ru");
		assertEquals("gennad.ru", result);
		
		result =  (String)m.invoke(h, "www.blog.gennad.ru");
		assertEquals("gennad.ru", result);
		
		result =  (String)m.invoke(h, "gennad.ru");
		assertEquals("gennad.ru", result);
		
		result =  (String)m.invoke(h, "");
		assertEquals("", result);
		
		result =  (String)m.invoke(h, "gennad");
		assertEquals("gennad", result);
	}
	
	public void testAppendHost() throws SecurityException, NoSuchMethodException, IllegalArgumentException, IllegalAccessException, InvocationTargetException {
		Hosts h = new Hosts();
		Class hc = h.getClass();
		Method m = hc.getDeclaredMethod("appendHost", String.class);
		m.setAccessible(true);
		Boolean result =  (Boolean)m.invoke(h, "http://www.yandex.ru");
		assertEquals(new Boolean(true), result);
	}
}
