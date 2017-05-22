package com.domhnaill.jira;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;

import junit.framework.Assert;

import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.phantomjs.PhantomJSDriver;
import org.openqa.selenium.phantomjs.PhantomJSDriverService;
import org.openqa.selenium.remote.DesiredCapabilities;

import com.gargoylesoftware.htmlunit.javascript.host.file.File;
import com.sun.jna.platform.FileUtils;

public class load {

	/**
	 * @param args
	 * @return
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int counter = 0;
		String username = args[0];
		String password = args[1];
		String[] urls = Arrays.copyOfRange(args, 2, args.length);
		for (String arg : urls) {
			System.out.println("\t" + arg);

			counter = counter + 1;
			String fileName = "output" + String.valueOf(counter);
			// WebDriver driver;
			DesiredCapabilities caps = new DesiredCapabilities();
			caps.setJavascriptEnabled(true);
			System.out.print(arg);
			//TODO
			//caps.setCapability(PhantomJSDriverService.PHANTOMJS_EXECUTABLE_PATH_PROPERTY,"");

			// driver = new FirefoxDriver();
			WebDriver driver = new PhantomJSDriver(caps);
			driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

			String actualTitle = "";

			// launch Fire fox and direct it to the Base URL
			driver.get(arg);
			
			// get the actual value of the title
			driver.findElement(By.id("login-form-username")).clear();
			driver.findElement(By.id("login-form-username")).sendKeys(username);
			driver.findElement(By.id("login-form-password")).clear();
			driver.findElement(By.id("login-form-password")).sendKeys(password);
			System.out.print(username);

			driver.findElement(By.id("login-form-submit")).submit();
			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
			actualTitle = driver.getTitle();
			System.out.print(actualTitle);
			/*
			 * compare the actual title of the page with the expected one and
			 * print the result as "Passed" or "Failed"
			 */
			/*
			 * if (actualTitle.contentEquals(expectedTitle)){
			 * System.out.println("Test Passed!"); } else {
			 * System.out.println("Test Failed"); }
			 */

			String filepath = fileName + ".jpeg";
			System.out.print(filepath);
			java.io.File finalFile = ((TakesScreenshot) driver)
					.getScreenshotAs(OutputType.FILE);
			try {
				org.apache.commons.io.FileUtils.copyFile(finalFile,
						new java.io.File(filepath));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			String finalTitle = driver.getCurrentUrl();
			System.out.print(finalTitle);
			// close Fire fox
			driver.quit();
		}
		// exit the program explicitly
		System.exit(0);
	}

}
