package scrapping;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

public class Try {

	static final Logger logger = Logger.getLogger(Try.class);
	 
	public static void main2(String[] args) {
		PropertyConfigurator.configure("log4j.properties");
        logger.debug("Hello World!");

	}

}
