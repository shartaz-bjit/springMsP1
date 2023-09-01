package com.task.day1;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import com.task.day1.Services.Delivery;

@SpringBootApplication
public class Day1Application {
	public static void main(String[] args) {
	    ApplicationContext context = new ClassPathXmlApplicationContext("customconfig.xml"); 
	    // Creating two instance to check scope type
	    // Used string based getter for absolute xml config
	    Delivery delivery1 = (Delivery) context.getBean("delivery"); 
	    Delivery delivery2 = (Delivery) context.getBean("delivery");
	}
}