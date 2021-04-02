package com.hcl.test;

import org.testng.Reporter;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class PrintNumbers {

	/**
	 * Function to Print the Numbers in the given range like 1,2,3,4,5,6,7,8.9,10..
	 * 
	 * @param number - Range of the numbers to be printed
	 */
	@Parameters({ "NumbersRange" })
	@Test
	public void printNumbersRange(String number) {

		System.out.println("Input Max Number Range --> " + number);
		Reporter.log("Input Max Number Range --> " + number);
		// Declaring the max number
		int maxNumber = 0;

		// Handling the String to Integer conversion exception
		try {
			// Convert String input to Integer
			maxNumber = Integer.parseInt(number);

			// Check if the input number is greater than zero
			if (maxNumber > 0) {

				// Final Numbers string to print
				String finalNumbers = "1";

				// Loop and create the numbers string
				for (int i = 2; i <= maxNumber; i++) {

					finalNumbers = finalNumbers + "," + i;

				}

				Reporter.log("Final Numbers String --> " + finalNumbers);
				System.out.println("Final Numbers String --> " + finalNumbers);
			}

		} catch (NumberFormatException e) {
			e.printStackTrace();
			Reporter.log("Entered Parameter is not valid!!");
			org.testng.Assert.fail("Entered Parameter is not valid!!");
			
			
		}

	}

}
