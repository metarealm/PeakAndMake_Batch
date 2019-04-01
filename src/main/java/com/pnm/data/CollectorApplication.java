package com.pnm.data;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class CollectorApplication {

	private static ApplicationContext applicationContext;
	public static void main(String[] args) {
		System.out.println("before starting context");
		applicationContext =SpringApplication.run(CollectorApplication.class, args);
		//displayAllBeans();
		System.out.println("after starting context");
	}

    public static void displayAllBeans() {
        String[] allBeanNames = applicationContext.getBeanDefinitionNames();
        for(String beanName : allBeanNames) {
            System.out.println(beanName);
        }
    }
}
