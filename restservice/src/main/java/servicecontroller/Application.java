package servicecontroller;

import javax.annotation.PostConstruct;

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
    
    @PostConstruct
    public void init(){
    	new Thread(new QueuePoller()).start();
        new Thread(new DBPoller()).start();
    }
}