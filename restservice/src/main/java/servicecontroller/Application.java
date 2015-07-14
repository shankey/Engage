package servicecontroller;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.web.SpringBootServletInitializer;

import poller.DBPoller;
import poller.QueuePoller;

@SpringBootApplication
public class Application  extends SpringBootServletInitializer {
	
	@Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(Application.class);
    }

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
        
    }
    
    Thread qThread=null;
    Thread dbThread=null;
    @PostConstruct
    public void init(){
    	if(qThread==null){
    		qThread = new Thread(new QueuePoller());
            dbThread = new Thread(new DBPoller());
            
            qThread.start();
            dbThread.start();
    	}
    	
    }
    
    /*@PreDestroy
    public void destroy(){
    	qThread.interrupt();
    	
    	dbThread.interrupt();
    }*/
}