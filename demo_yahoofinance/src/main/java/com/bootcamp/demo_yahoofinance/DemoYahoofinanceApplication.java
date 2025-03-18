package com.bootcamp.demo_yahoofinance;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class DemoYahoofinanceApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoYahoofinanceApplication.class, args);
	}

}

