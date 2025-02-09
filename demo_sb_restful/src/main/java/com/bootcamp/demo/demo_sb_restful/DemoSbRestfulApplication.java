package com.bootcamp.demo.demo_sb_restful;

import org.apache.catalina.core.ApplicationContext;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication //@SpringBootConfiguration + @EnableAuto
//! ComponentScan -> Search the whole project, check if any 
//! @Controller , @Service, @Repository, @Configuattion , @
//! @Controller , @Service, @Repository, @Configuattion are a type of Component

public class DemoSbRestfulApplication {

	public static ApplicationContext  context;
	public static void main(String[] args) {
		 context= SpringApplication.run(DemoSbRestfulApplication.class, args);
	}

}
