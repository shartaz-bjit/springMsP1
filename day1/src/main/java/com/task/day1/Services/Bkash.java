package com.task.day1.Services;

// The only service that implements payment.
public class Bkash implements Payment{
	
	public Bkash() {
		// To test when an object is created.
		System.out.println("[-] Creating bkash instance for Payment interface.");
	}

	@Override
	public void pay() {
		System.out.println("Paying from bkash...");
	}

}
