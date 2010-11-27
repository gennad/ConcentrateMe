package com.gennadz;

import java.util.Dictionary;

public class Hosts {
	private final String hostsLocation = "C:/windows/system32/drivers/etc/hosts";
	
	/**
	 * Gets all hosts in the file
	 * @return 
	 */
	public Dictionary<String, String> getAllHosts() {
		return null;
		
	}
	
	/**
	 * Gets all proibited hosts in the file
	 * @return
	 */
	public Dictionary<String, String> getProhibitedHosts() {
		return null;
		
	}
	
	/**
	 * Gets all allowed hosts in the file
	 * @return
	 */
	public Dictionary<String, String> getAllowedHosts() {
		return null;
	}
	
	/**
	 * Reads
	 * @return
	 */
	private String[] readFile() {
		
	}
	
	/**
	 * Creates temp file for hosts temp storage
	 */
	private void createTempFile() {
		
	}
	
	/**
	 * Deletes temp file for hosts temp storage
	 */
	deleteTempFile() {
		
	}
	
	/**
	 * Writes string to file
	 */
	writeStringToFile() {
		
	}
	
	/**
	 * Reads string from file
	 */
	readStringFromFile() {
		
	}
	
	/**
	 * Deletes temp file and releases all handlers
	 */
	protected void finalyze() {
		deleteTempFile();
		//release all handlers
	}
}
