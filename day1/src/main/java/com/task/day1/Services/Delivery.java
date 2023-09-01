package com.task.day1.Services;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

public class Delivery implements ApplicationContextAware {
    private Payment payment;
    private Contact contact;
    
    // To test when an object is created.
    public Delivery() {
        System.out.println("[-] Creating a delivery object.");
    }

    // This class is called after object in created, so I did the initialization here.
	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		contact = (Contact) applicationContext.getBean("contact");
		payment = (Payment) applicationContext.getBean("payment");
	}
}
