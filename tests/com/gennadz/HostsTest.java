/**
 * 
 */
package com.gennadz;

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
	public void testDeleteTempFile() throws SecurityException, NoSuchMethodException, IllegalArgumentException, IllegalAccessException, InvocationTargetException {
		Hosts h = new Hosts();
		Class hostsClass = h.getClass();
		//Class<String> c = String.class;
		Method method = hostsClass.getDeclaredMethod("deleteTempFile", null);
		method.setAccessible(true);
		
		Boolean result =  (Boolean)method.invoke(h);
		Boolean t = new Boolean(true); //hack
	    assertEquals(t, result);
	}
	
	public void testGetAllHosts() {
		Hosts h = new Hosts();
		h.getAllHosts();
	}
}
