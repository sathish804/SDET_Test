package com.hcl.test;

import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class AreaOfTriangle {

	/**
	 * Function to calculate the Area of Triangle on the height and breadth
	 * 
	 * @param height  - Height of Triangle
	 * @param breadth - Breadth of Triangle
	 */
	@Parameters({ "Height", "Breadth" })
	@Test (groups = { "area_HB" } )
	public void areaOfTriangle_HB(double height, double breadth) {

		System.out.println("3 Sides of Triangle --> " + height + " , " + breadth);
		Reporter.log("3 Sides of Triangle --> " + height + " , " + breadth);

		// Validate Height and Breadth
		if (height > 0 && breadth > 0) {
			System.out.println("Triangle is valid!!");
			Reporter.log("Triangle is valid!!");

			// Calculate and Print the Area
			double area = (height * breadth) / 2;
			System.out.println("Area of the Triangle --> " + area);
			Reporter.log("Area of the Triangle --> " + area);

		} else {
			System.out.println("Please enter the valid Height and Breadth");
			Reporter.log("Please enter the valid Height and Breadth");
			Assert.fail("Please enter the valid Height and Breadth");
		}

	}

	/**
	 * Function to Calculate Area of Triangle based on the 3 sides of the triangle
	 * 
	 * @param side1
	 * @param side2
	 * @param side3
	 */
	@Parameters({ "Side1", "Side2", "Side3" })
	@Test (groups = { "area_Sides" } )
	public void areaOfTriangle_Sides(double side1, double side2, double side3) {

		System.out.println("3 Sides of Triangle --> " + side1 + " , " + side2 + " ," + side3);
		Reporter.log("3 Sides of Triangle --> " + side1 + " , " + side2 + " ," + side3);

		// Validate the Sides are greater than zero
		if (side1 > 0 && side2 > 0 && side3 > 0) {
			System.out.println("Triangle Sides are greater than 0");
			Reporter.log("Triangle Sides are greater than 0");

			// Validate whether the Triangle is valid or not
			if ((side1 + side2 > side3) && (side2 + side3 > side1) && (side3 + side1 > side2)) {
				System.out.println("Triangle is valid!!");
				Reporter.log("Triangle is valid!!");
				
				//Calculate Permiter of the triangle
				double perimeter = (side1+side2+side3)/2;
				//Calculate Area of the triangle
				double area = Math.sqrt(perimeter * ( perimeter-side1) * (perimeter-side2) * (perimeter-side3));
				
				System.out.println("Area of the Triangle --> " + area);
				Reporter.log("Area of the Triangle --> " + area);


			} else {
				System.out.println("Triangle is not valid");
				Reporter.log("Triangle is not valid");
				Assert.fail("Triangle is not valid");
			}

		} else {
			System.out.println("Argument Sides are not valid!!");
			Reporter.log("Argument Sides are not valid!!");
			Assert.fail("Argument Sides are not valid!!");
		}

	}

}
