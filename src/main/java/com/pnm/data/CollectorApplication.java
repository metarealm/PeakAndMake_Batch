package com.pnm.data;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.*;

@Configuration
@ComponentScan(basePackages = {"com.pnm.batching.writer", "com.pnm.batching.tasklets"})
@SpringBootApplication
public class CollectorApplication {

	public static void main(String[] args) {
		System.out.println("before starting context");
		SpringApplication.run(CollectorApplication.class, args);
		System.out.println("after starting context");
	}

}
