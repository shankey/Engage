package servicecontroller;

import hibernate.bean.SkuDetails;

import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import Common.Log;

import org.apache.log4j.Logger;

import bao.GetPriceBAO;
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
    
    @RequestMapping("/getPrice")
    public List<SkuDetails> getPrice(@RequestParam(value="sku", defaultValue="JN41345195") String sku) {
    	logger.info("insisde getPrice method");
        List<SkuDetails> list = new GetPriceBAO().getPriceDetailsForSku(sku);
        return list;
    }
    
    @Bean
    public Log log(){
    	return new Log();
    }
    
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

}