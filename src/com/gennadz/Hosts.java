package com.gennadz;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Collection;
import java.util.Dictionary;
import java.util.Formatter;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Hosts {
	private final String hostsLocation = "C:/Windows/System32/drivers/etc/hosts";
	private final String tempFileName = "tempfile.txt";
	private String tempFileHadler;
	private String tempFileLocation;
	private File hostsFile;
	private File tempFile;
	
	
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
	
	public Hosts() {
		hostsFile = new File(hostsLocation);
		tempFile = new File(tempFileLocation);
	}

	/**
	 * Gets all hosts in the file
	 * @return 
	 */
	public Map<String, String> getAllHosts() {
		Map<String, String> hosts = new HashMap<String, String>();
		try {
			String content = readFromFile(hostsFile);
			List<String> arContent = Arrays.asList(content.split("\n"));
			Pattern pattern = Pattern.compile("\\d+\\.\\d+\\.\\d+\\.\\d+\\s+\\w");
			
			
			for (int i = 0; i<arContent.size(); i++) {
				String s = arContent.get(i);
				Matcher matcher = pattern.matcher(s);
				
				if (matcher.find()) {
					if (!s.contains("acme")) {
						String[] hostAndName = s.split("\\s");
						try {
							String ip = hostAndName[0];
							String name = hostAndName[1];
							hosts.put(ip, name);
						} catch (NullPointerException e) {
						}
					}
				}
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return hosts;
	}
	
	/**
	 * Gets all proibited hosts in the file
	 * @return
	 */
	public Map<String, String> getProhibitedHosts() {
		Map<String, String> allHosts = getAllHosts();
		Set<String> keys = allHosts.keySet();
		for (String key: keys) {
			if (!key.contains("#"));
			allHosts.remove(key);
		}
		return allHosts;
	}
	
	/**
	 * Gets all allowed hosts in the file
	 * @return
	 */
	public Map<String, String> getAllowedHosts() {
		Map<String, String> allHosts = getAllHosts();
		Set<String> keys = allHosts.keySet();
		for (String key: keys) {
			if (key.contains("#"));
			allHosts.remove(key);
		}
		return allHosts;
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
	private Boolean deleteFile(File file) {
	   
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
	 * 
	 * Appends if it exists data
	 * @throws FileNotFoundException 
	 */
	private void writeTextToFile(File file, String string) throws FileNotFoundException {
	     FileOutputStream fos = new FileOutputStream(file, true);
	     PrintWriter out = new PrintWriter(fos);
	     out.println(string);
	     out.flush();
	     out.close();
	}
	
	/**
	 * Reads string from file
	 * @throws IOException 
	 */
	private String readFromFile(File file) throws IOException {
		// input
	     FileInputStream fis  = new FileInputStream(file);
	     BufferedReader in = new BufferedReader
	         (new InputStreamReader(fis));
	     String thisLine = "";
	     StringBuilder sb = new StringBuilder();
	     while ((thisLine = in.readLine()) != null) {
	    	 sb.append(thisLine).append("\n");
	     }
	    in.close();
	    return sb.toString();
	}
	
	public void blockHosts(Set<String> hosts) {
		Set<String> hostsToBlock = new HashSet<String>();
		
		for (String host: hosts) {
			if (!isContainHost(host)) {
				appendHost(host);
			} else if (!isHostBlocked(host)) {
				hostsToBlock.add(host);
			}
		}
		blockHosts(hostsToBlock);
	}
	
	private boolean isHostBlocked(String host) {
		host = cleanHost(host);
		Map<String, String> allHosts = getAllHosts();
		Collection<String> keys = allHosts.keySet();
		
		for (String key: keys) {
			String value = allHosts.get(key);
			if (value.contains(host)) {
				if (key.contains("#")) {
					return true;
				}
			}
		}
		
		return false;
	}

	private Boolean isContainHost(String host) {
		host = cleanHost(host);
		Map<String, String> allHosts = getAllHosts();
		Collection<String> values = allHosts.values();
		
		for (String value: values) {
			if (value.contains(host)) {
				return true;
			}
		}
		
		return false;
	}
	
	/**
	 * Removes http, www and other useless stuff
	 * @return 
	 */
	private String cleanHost(String host) {
		
		Pattern pattern = Pattern.compile("\\w+\\.\\w+$");
		Matcher matcher = pattern.matcher(host);
		if (matcher.find()) {
			String r = matcher.group();
			return r;
			
		} else return host;
		
		
		
		
		
	}
	
	private Boolean appendHost(String host) {
		host = cleanHost(host);
		StringBuilder sb = new StringBuilder();
		Formatter formatter = new Formatter(sb, Locale.US);
		formatter.format("127.0.0.1 %1s", host);
		formatter.flush();
		String str = sb.toString();		
		try {
			writeTextToFile(hostsFile, str);
			return true;
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
	}
	
	
	
	/**
	 * Deletes temp file and releases all handlers
	 */
	protected void finalyze() {
		deleteFile(tempFile);
		//release all handlers
	}
}
