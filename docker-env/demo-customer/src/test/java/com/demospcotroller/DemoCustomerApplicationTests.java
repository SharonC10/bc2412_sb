package com.demospcotroller;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

//"mvn clean install" -> includes "mvn test
// "mvn compile" -> ensures main code syntax alright.
// "mvn test-compile" -> ensures test code syntax alright
// "mvn test" -> execute test -> @Spring Boot test -> test once for the target testing env.
@SpringBootTest //simulate the actual Spring Bean Cycle.
class DemoCustomerApplicationTests {

	//! test all the dependency between components
	@Test
	void contextLoads() {
	}

}
