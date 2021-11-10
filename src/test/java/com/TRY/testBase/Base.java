package com.TRY.testBase;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Reporter;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import com.TRY.listeners.CustomListener;
import com.TRY.utilities.ExcelReader;
import com.aventstack.extentreports.Status;

public class Base {

	public static Properties config, or;
	public static WebDriver driver;
	// logger object
	public static Logger log = LogManager.getLogger(Base.class.getName());

	public static WebDriverWait wait;
	public static ExcelReader read = new ExcelReader(
			System.getProperty("user.dir") + "//src//test//resources//excel//Data.xlsx");

	public static String browser;

	// Starting method
	@BeforeSuite
	public void tearup() throws IOException {

		// Config properties load
		try {
			FileInputStream fis = new FileInputStream(
					System.getProperty("user.dir") + "\\src\\test\\resources\\properties\\Config.properties");
			config = new Properties();
			config.load(fis);
			//branch test update
			log.info("Config properties loaded successfully");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

		// OR properties load
		try {
			FileInputStream fis = new FileInputStream(
					System.getProperty("user.dir") + "\\src\\test\\resources\\properties\\OR.properties");
			or = new Properties();
			or.load(fis);
			//new change for this version
			log.info("OR properties loaded successfully");
		} catch (FileNotFoundException e) {

			e.printStackTrace();
		}

		// setting jenkins parameter for the browser
		if (System.getenv("browser") != null && !System.getenv("browser").isEmpty()) {

			browser = System.getenv("browser");
		} else {
			browser = config.getProperty("browser");
		}

		// browser driver
		config.setProperty("browser", browser);

		if (config.getProperty("browser").equals("chrome")) {
			System.setProperty("webdriver.chrome.driver",
					System.getProperty("user.dir") + "\\src\\test\\resources\\executables\\chromedriver.exe");
			log.info("Chrome browser ready");
			driver = new ChromeDriver();
			driver.get(config.getProperty("testurl"));

		} else if (config.getProperty("browser").equals("firefox")) {
			System.setProperty("webdriver.gecko.driver",
					System.getProperty("user.dir") + "\\src\\test\\resources\\executables\\geckodriver.exe");
			driver = new FirefoxDriver();
			log.info("Firefox browser ready");
			driver.get(config.getProperty("testurl"));
		}
		wait = new WebDriverWait(driver, 10);

	}

	public static WebElement dropdown;

	public void select(String locator, String value) {

		if (locator.endsWith("_css")) {
			dropdown = driver.findElement(By.cssSelector(or.getProperty(locator)));
		} else if (locator.endsWith("_xpath")) {
			dropdown = driver.findElement(By.xpath(or.getProperty(locator)));
		}

		Select select = new Select(dropdown);
		select.selectByVisibleText(value);
		CustomListener.testReport.get().log(Status.INFO, "Clicking on " + locator + " selected the value " + value);
		Reporter.log("<br>" + "Verification Done : " + "<br>");
	}

	public void click(String locator) {

		if (locator.contains("_xpath")) {
			driver.findElement(By.xpath(or.getProperty(locator))).click();

		} else if (locator.contains("_css")) {
			driver.findElement(By.cssSelector(or.getProperty(locator))).click();
		}

		CustomListener.testReport.get().log(Status.INFO, "Clicking on " + locator);
	}

	public void typeValue(String locator, String value) {
		if (locator.contains("_xpath")) {
			driver.findElement(By.xpath(or.getProperty(locator))).sendKeys(value);

		} else if (locator.contains("_css")) {
			driver.findElement(By.cssSelector(or.getProperty(locator))).sendKeys(value);

		}
		CustomListener.testReport.get().log(Status.INFO, "Clicked on " + locator + " entered the value " + value);

	}

	// verification of element presence
	public boolean isElementPresent(By by) {
		try {
			driver.findElement(by);
			return true;
		} catch (NoSuchElementException e) {
			return false;
		}
	}

	// Tear down method
	@AfterSuite
	public void tearDown() {

		if (driver != null) {
			driver.close();
		}
	}

}
