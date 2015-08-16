package Common;

import java.io.File;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import scrapping.Try;

public class Log {
	
	static final Logger logger = Logger.getLogger(Try.class);
	
	public Log(){
			File file = new File(".");
	    	 PropertyConfigurator.configure("webapps/gs-rest-service-0.1.0/WEB-INF/classes/log4j.properties");
	    	 logger.info("Logger Initialized!!");
	}
}
