package com.hcl.test;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Path;
import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.imageio.ImageIO;

import org.openqa.selenium.By;
import org.openqa.selenium.ElementNotInteractableException;
import org.openqa.selenium.ElementNotVisibleException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;

public class BoardOfDirector {

	// webdriver variable
	static WebDriver driver = null;

	// Webdriver wait
	static WebDriverWait wait = null;

	// Folder location to save Image
	public static String output_Folder = "/target/photos/";

	@Parameters({ "DRIVER_LOCATION", "URL" })
	@BeforeTest
	public void setUp(String driverLocation, String appURL) {
		// Set the Geko driver location
		System.setProperty("webdriver.gecko.driver", driverLocation);

		// Created Firefox driver instance
		driver = new FirefoxDriver();

		// Open the URL
		driver.get(appURL);

		// Create Driver wait
		wait = new WebDriverWait(driver, 10);

		// Wait for 10 seconds
		implcitWait(10);
		
		//Maximize Browser
		driver.manage().window().maximize();
		
		//Accept all cookies
		accept_Cookies();
	}

	@Parameters({ "Director_Name" })
	@Test
	public void validate_BD(String directorName) {

		// Wait for the Leadership link to be visible

		try {
			wait.until(ExpectedConditions.elementToBeClickable(By.linkText("Leadership")));

			// Get Leadership Element and Click
			WebElement leadership = driver.findElement(By.linkText("Leadership"));
			leadership.click();

			// Wait for 5 seconds
			implcitWait(5);

			// Wait until the Board of directors is visible

			try {
				wait.until(
						ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='directors naming ']")));

				// Get Board of Directors List
				List<WebElement> bdNames = driver.findElements(By.xpath("//div[@class='directors naming ']//li"));

				// Validate whether Board of directors list elements are found
				int directorsCount = bdNames.size();
				if (directorsCount > 0) {

					// Flag for the Director name found or not
					boolean isDirectorFound = false;

					// Validate whether the argument Board of Director is in the list

					for (int i = 0; i < directorsCount; i++) {

						String tName = bdNames.get(i).findElement(By.xpath("//div/h4")).getText().trim();

						// Check the name matched or not
						if (tName.equalsIgnoreCase(directorName)) {

							Assert.assertEquals(tName.toUpperCase(), directorName.toUpperCase());
							System.out.println("Director name " + directorName + "is on the board of directors!!");
							isDirectorFound = true;
							String tImageURL = bdNames.get(i).findElement(By.xpath("//div/img")).getAttribute("src")
									.trim();

							// Download Image of the director
							try {
								downloadImage(tImageURL, tName);
							} catch (Exception e) {
								e.printStackTrace();
							}

							break;

						} else {

							// Validate whether the director found or not and throw error
							if ((i == (directorsCount - 1)) && (!isDirectorFound)) {
								Assert.fail("Director name " + directorName + "is not on the board of directors!!");
								System.out.println(
										"Director name " + directorName + "is not on the board of directors!!");
							}
						}

					}

				} else {
					System.out.println("Board of Directors Section is Empty");
					Assert.fail("Board of Directors Section is not Empty");

				}

			} catch (ElementNotVisibleException e) {
				e.printStackTrace();
				System.out.println("Board of Directors Section is not visible");
				Assert.fail("Board of Directors Section is not visible");
			}

		} catch (ElementNotInteractableException e) {
			e.printStackTrace();
			System.out.println("Leadership Element not visible on the screen to click");
			Assert.fail("Leadership Element not visible on the screen to click");
		}

	}

	/**
	 * Function to save the image
	 * 
	 * @param url      - URL of the image
	 * @param fileName - image File name
	 * @throws IOException
	 */
	public static void downloadImage(String url, String fileName) throws IOException {

		// Creating the URL variable from String
		java.net.URL imageURL = new java.net.URL(url);

		// Create a Buffered image from the Argument url of image location
		BufferedImage image = ImageIO.read(imageURL);
		
		//Define output File
		String folderPath = System.getProperty("user.dir") + output_Folder ;
		//Create the directory path of it not exists
		new File(folderPath).mkdirs();
		
		String finalPath = folderPath +  fileName + ".jpg";

		// Write the image to a file
		ImageIO.write(image, "jpg", new File(finalPath));
		System.out.println("Director Image Saved to the target\\photos folder");
		Reporter.log("Director Image Saved to the target\\photos folder");

	}

	/**
	 * Function to wait for argument seconds
	 * 
	 * @param secs
	 */
	public static void implcitWait(int secs) {
		driver.manage().timeouts().implicitlyWait(secs, TimeUnit.SECONDS);
	}
	
	/**
	 * Function to handle the accept cookies banner on page load
	 * 
	 */
	public static void accept_Cookies() {
		
		try {
		//Wait for Cookies acceptance button
		wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.id("onetrust-accept-btn-handler"))));
		
		//Click the accept cookies button
		WebElement acceptCookies = driver.findElement(By.id("onetrust-accept-btn-handler"));
		acceptCookies.click();
		
		}catch (Exception e) {
			System.out.println("Accept Cookies button Not visible");
		}
		
	}

	
	@AfterTest
	public void tearDown() {

		// Close driver
		driver.quit();
	}

}
