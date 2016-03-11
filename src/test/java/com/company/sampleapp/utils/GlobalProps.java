package com.company.sampleapp.utils;

/**
 * Load properties files
 * @author tfarrar
 *
 */

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Enumeration;
import java.util.Properties;

import org.apache.log4j.Logger;







public class GlobalProps {
	private String filename=null;
	private String locationInJar="GlobalExecution.properties";
	private InputStream inputStream;
	private static Logger LOGGER = Logger.getLogger( GlobalProps.class );
	private Properties myProperties;
	
	
	

	public GlobalProps(){ 
		 this.setFilename("target\\test-classes\\GlobalExecution.properties");
	 }
	 
	 public GlobalProps(String defineFilename){
		 //All user to define the filename
		 filename=defineFilename;
	 }
	 
	 
	 
	 public Properties getMyProperties() {
		return myProperties;
	}

	public void setMyProperties(Properties myProperties) {
		this.myProperties = myProperties;
	}

	public String getFilename() {
		return filename;
	}

	public void setFilename(String filename) {
		this.filename = filename;
	}

	/**
	 * @param args
	 * @throws IOException 
	 */
	public Properties loadParameters() {


		// This Loads all properties in file defined.
		Properties  defaultProps = new Properties();
		
		/* 
		 * This will load the parameter from the local application jar file if the file does not exist it
		 * will use the properties file in the test-automation-selenium-core.jar  
		 *  
		 */
		  this.loadJarProps();
		  this.setSystemProps();
		//Overwrite Properties with System properties
		// If System property does not exist leave jar props as default.
		
		
		//
		
		/*
		 * Return the whole Properties object to be used in the 
		 */
		return this.getMyProperties();
	
	}
	
	public void setSystemProps() {


		// This Loads all properties in file defined.
		Properties  prop = new Properties();
		
	
			  
			
			prop =this.getMyProperties();
			Enumeration<?> e = this.getMyProperties().propertyNames();
			while (e.hasMoreElements()) {
				String key = (String) e.nextElement();
				String value = prop.getProperty(key);
				//LOGGER.info("Checking system property Key : " + key + ", Value : " + value);
				
					if(System.getProperty(key)!=null && !System.getProperty(key).isEmpty()){
						LOGGER.info("Setting system property for key: " + key + ", Value : " + System.getProperty(key));
						prop.setProperty(key, System.getProperty(key));
					}else{
						LOGGER.info("No system property found keeping : " + key + ", Value : " + value);
						if(key.equals("testSetID")){
							System.setProperty(key, prop.getProperty(key));
							LOGGER.info("Setting System Property : " + key + ", Value : " + value);
						}
						else if(key.equals("qcProject")){
							System.setProperty(key, prop.getProperty(key));
							LOGGER.info("Setting System Property : " + key + ", Value : " + value);
						}						
						//qcProject
					}
									
			}
			
	

		  this.setMyProperties(prop);
		
		
		/*
		 * Set properties to global
		*/
		//this.setMyProperties(props);
	
	}
	
	
	private void loadJarProps() {


		// This Loads all properties in file defined.
		Properties  props = new Properties();
		
		
	    try 
	    {
	    	 
			//getClass().getResource(resourceName);
	    	//getClass().getClassLoader().getResourceAsStream(
	    	InputStream input =getClass().getClassLoader().getResourceAsStream(this.locationInJar);
	    	//InputStream input =GlobalProps.class.getClassLoader().getResourceAsStream(this.locationInJar);
	    	//props.load(ClassLoader.getSystemResourceAsStream(this.locationInJar));
	    	 props.load(input);
	    } catch (IOException e) 
	    {
	    	LOGGER.error("Failed to load properties from jar location"+this.locationInJar);
	        e.printStackTrace();
	    }
		  
		
		
		/*
		 * Set properties to global
		*/
		this.setMyProperties(props);
	
	}
	

}
