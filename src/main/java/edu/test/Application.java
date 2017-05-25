package edu.test;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

//import org.springframework.boot.SpringApplication;
//import org.springframework.boot.autoconfigure.SpringBootApplication;



/*
@Configuration
@ComponentScan
@EnableAutoConfiguration
public class Application extends SpringBootServletInitializer {

	 @Override
	    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
	        return application.sources(Application.class);
	    }

	 	public static void main(String[] args) {
	    	new SpringApplicationBuilder(Application.class);
	        
	    }

}*/

/*@SpringBootApplication // same as @Configuration @EnableAutoConfiguration @ComponentScan
public class Application {

	
	
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

}*/

@Configuration
@ComponentScan
@EnableAutoConfiguration
public class Application extends org.springframework.boot.web.support.SpringBootServletInitializer {

    // Used when deploying to a standalone servlet container
    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(Application.class);
    }
    
 // Used when launching as an executable jar or war
    public static void main(String[] args) throws Exception {
        SpringApplication.run(Application.class, args);
    }
    
}
