package com.absa.base;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.apache.log4j.Logger;

public class BaseSetup {
	
	protected static Properties testProperties = new Properties();
	
	private String INIT_PROPERTIES = System.getProperty("user.dir") + "\\Config\\init.properties";
	
	//Set Test Server Properties 
	public void init() throws IOException {
		
		loadPropertiesFile();
	}
	
	public void loadPropertiesFile() throws IOException {
		File f = new File(INIT_PROPERTIES);
		testProperties.load(new FileInputStream(f));
	}

}
