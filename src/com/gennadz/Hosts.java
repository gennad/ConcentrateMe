package com.gennadz;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Dictionary;

public class Hosts {
	private final String hostsLocation = "C:/windows/system32/drivers/etc/hosts";
	private final String tempFileName = "tempfile.txt";
	private String tempFileHadler;
	private String tempFileLocation;
	
	
	public String getTempFileHadler() {
		return tempFileHadler;
	}

	public void setTempFileHadler(String tempFileHadler) {
		this.tempFileHadler = tempFileHadler;
	}

	public String getTempFileLocation() {
		return tempFileLocation;
	}

	public void setTempFileLocation(String tempFileLocation) {
		this.tempFileLocation = tempFileLocation;
	}

	public String getHostsLocation() {
		return hostsLocation;
	}

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
		return null;
		
	}
	
	/**
	 * Creates temp file for hosts temp storage
	 * @throws IOException 
	 */
	private Boolean createTempFile() throws IOException {
		File file;
	    file=new File(tempFileName);
	    	if(!file.exists()) {
	    		Boolean result = file.createNewFile();
	    		return result;
	    	} else {
	    		return true;
	    	}
	}
	
	/**
	 * Deletes temp file for hosts temp storage
	 * @return 
	 */
	private Boolean deleteTempFile() {
	    // A File object to represent the filename
	    File file = new File(tempFileName);

	    // Make sure the file or directory exists and isn't write protected
	    if (!file.exists())
	      throw new IllegalArgumentException(
	          "Delete: no such file or directory: " + tempFileName);

	    if (!file.canWrite())
	      throw new IllegalArgumentException("Delete: write protected: "
	          + tempFileName);

	    // If it is a directory, make sure it is empty
	    if (file.isDirectory()) {
	      String[] files = file.list();
	      if (files.length > 0)
	        throw new IllegalArgumentException(
	            "Delete: directory not empty: " + tempFileName);
	    }

	    // Attempt to delete it
	    Boolean result = file.delete();
	    return result;
	}
	
	/**
	 * Writes string to file
	 * @throws FileNotFoundException 
	 */
	private void writeTextToFile(File file, String string) throws FileNotFoundException {
	     FileOutputStream fos = new FileOutputStream(file);
	     PrintWriter out = new PrintWriter(fos);
	     out.println(string);
	     out.flush();
	     out.close();
	}
	
	/**
	 * Reads string from file
	 * @throws IOException 
	 */
	private String readStringFromFile(File file) throws IOException {
		// input
	     FileInputStream fis  = new FileInputStream(file);
	     BufferedReader in = new BufferedReader
	         (new InputStreamReader(fis));
	     String thisLine = "";
	     StringBuilder sb = new StringBuilder();
	     while ((thisLine = in.readLine()) != null) {
	    	 sb.append(thisLine);
	     }
	    in.close();
	    return sb.toString();
	}
	
	/**
	 * Deletes temp file and releases all handlers
	 */
	protected void finalyze() {
		deleteTempFile();
		//release all handlers
	}
}
