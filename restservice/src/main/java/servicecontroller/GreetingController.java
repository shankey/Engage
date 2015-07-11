package servicecontroller;

import java.util.concurrent.atomic.AtomicLong;

import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import Common.Log;

import org.apache.log4j.Logger;

@RestController
public class GreetingController {

	public static Logger logger = Logger.getLogger(GreetingController.class);
    private static final String template = "Hello, %s!";
    private final AtomicLong counter = new AtomicLong();
    

    @RequestMapping("/greeting")
    public Greeting greeting(@RequestParam(value="name", defaultValue="World") String name) {
    	logger.info("insisde greeting method");
        return new Greeting(counter.incrementAndGet(),
                            String.format(template, name));
    }
    
    @Bean
    public Log log(){
    	return new Log();
    }

}