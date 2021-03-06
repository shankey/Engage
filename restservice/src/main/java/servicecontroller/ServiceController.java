package servicecontroller;


import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;



import org.apache.log4j.Logger;


import poller.DBPoller;
import poller.QueuePoller;

@RestController
public class ServiceController {

	public static Logger logger = Logger.getLogger(ServiceController.class);
    private static final String template = "Hello, %s!";
    private final AtomicLong counter = new AtomicLong();
    private static Thread dbpollerThread;
    private static Thread queuepollerThread;
    

    @RequestMapping("/greeting")
    public Greeting greeting(@RequestParam(value="name", defaultValue="World") String name) {
    	logger.info("insisde greeting method");
        return new Greeting(counter.incrementAndGet(),
                            String.format(template, name));
    }
    
   
    
//    @Bean
//    public Log log(){
//    	return new Log();
//    }
    
    //@Bean
    public DBPoller dbpoller(){
    	DBPoller dbpoller = new DBPoller();
    	new Thread(dbpoller).start();
    	return dbpoller;
    }
    
    //@Bean
    public QueuePoller queuepoller(){
    	QueuePoller queuepoller = new QueuePoller();
    	new Thread(queuepoller).start();
    	return queuepoller;
    }
    
    Thread qThread=null;
    Thread dbThread=null;
    @PostConstruct
    public void init(){
    	
    		logger.info("calling init on preconstruct");
    		if(qThread == null){
    			qThread = new Thread(new QueuePoller());
                dbThread = new Thread(new DBPoller());
                
                qThread.start();
                dbThread.start();
    		}
    	
    }
    
    @PreDestroy
    public void destroy(){
    	
    	logger.info("calling destroy on predestroy");
    	qThread.interrupt();
    	
    	dbThread.interrupt();
    	
    }

}