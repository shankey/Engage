package com.engage.common;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;


public class Log {
	
	static final Logger logger = Logger.getLogger(Log.class);
	static File queueFile = new File("/tmp/Queue");
	
	
	public Log(){
			 File file = new File(".");
			 System.out.println(file.getAbsolutePath());
			 Logger.getLogger("").setLevel(Level.INFO);
	    	 //PropertyConfigurator.configure("webapps/gs-rest-service-0.1.0/WEB-INF/classes/log4j.properties");
			 PropertyConfigurator.configure("src/main/resources/log4j.properties");
	    	 System.out.println(logger.getParent());
	    	 logger.info("Logger Initialized!!");
	    	 logger.debug("What the");
	    	 
	}
	
	public static void write(String str) throws IOException{
		if (!queueFile.exists()) {
			queueFile.createNewFile();
		}

		FileWriter fw = new FileWriter(queueFile.getAbsoluteFile(), true);
		BufferedWriter bw = new BufferedWriter(fw);
		bw.write(str);
		bw.close();
	}
}