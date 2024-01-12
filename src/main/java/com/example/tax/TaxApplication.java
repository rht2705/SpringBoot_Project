package com.example.tax;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.Scanner;

@SpringBootApplication
public class TaxApplication {

	public static void main(String[] args) {

		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
		Scanner scanner = new Scanner(System.in);
		System.out.println("Welcome to the Tax Payment Application");
		while (true) {
			System.out.println("Please select which tax you want to pay: \n1. Income \n2. Property\n3. Exit");
			int userChoice = scanner.nextInt();
			String taxChoice = "";
			switch (userChoice) {
				case 1 -> {
					// Set the taxChoice string as the Income tax bean id.
					taxChoice = "incomeTax";

				}
				case 2 -> {
					// Set the taxChoice string as the Property tax bean id.
					taxChoice = "propertyTax";
				}
				case 3 -> {
					// Print the message "Exiting..." and return.
					System.out.println("Exiting...");
					return;

				}
				default -> {
					// Print the message "Invalid choice" and return.
					System.out.println("Invalid choice");
					return;

				}
			}
			// Pick the tax bean using context.getBean() method using taxChoice string.
			Tax tax = (Tax) context.getBean(taxChoice);


				/*
			1. Check if the tax is already paid, if yes print the message:
			    "You have already paid Income/Property(get this getTaxType() interface method) tax."
			2. If the tax is not paid:
				a. Take the input of taxableAmount from the user.
				b. Set the taxableAmount using setTaxableAmount() interface method.
				c. Calculate the taxAmount using calculateTaxAmount() interface method.
				d. Ask user if he wants to pay the tax. if yes call the payTax() method.
			 */

			if(tax.isTaxPayed()==true){
				System.out.println("You have already paid " + tax.getTaxType()+ " tax.");
			}else{
				System.out.println("Please enter your Income/Property value:");
				tax.setTaxableAmount(scanner.nextInt());
				tax.calculateTaxAmount();
				System.out.println("You have selected " + tax.getTaxType()+ " tax and your tax amount is: "+ tax.getTaxAmount());

				System.out.println("Please select which tax you want to pay: \n1. Yes \n2. Exit");
				userChoice = scanner.nextInt();
				switch (userChoice) {
					case 1 -> {
						tax.payTax();
					}
					case 2 -> {
						// Print the message "Exiting..." and return.
						System.out.println("Exiting...");
						return;
					}
					default -> {
						// Print the message "Invalid choice" and return.
						System.out.println("Invalid choice");
						return;
					}
				}
			}
		}
	}

}
