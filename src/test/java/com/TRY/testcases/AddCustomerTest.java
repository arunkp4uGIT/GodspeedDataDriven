package com.TRY.testcases;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;

import com.TRY.testBase.Base;
import com.TRY.utilities.UtilTest;

public class AddCustomerTest extends Base {

	

	@Test(dataProviderClass=UtilTest.class,dataProvider = "value")
	public void addCustomer(String firstName, String lastName, String zipcode, String alertText)
			throws InterruptedException {
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath(or.getProperty("addcb_xpath"))));
		// driver.findElement(By.xpath(or.getProperty("addcb_xpath"))).click();
		click("addcb_xpath");
		
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(or.getProperty("firstname_css"))));
		// driver.findElement(By.cssSelector(or.getProperty("firstname_css"))).sendKeys(firstName);
		typeValue("firstname_css", firstName);
	
		// driver.findElement(By.xpath(or.getProperty("lastname_xpath"))).sendKeys(lastName);
		typeValue("lastname_xpath", lastName);
		// driver.findElement(By.cssSelector(or.getProperty("post_css"))).sendKeys(zipcode);
		typeValue("post_css", zipcode);

		// driver.findElement(By.xpath(or.getProperty("submit_xpath"))).click();
		click("submit_xpath");
		Alert alert = wait.until(ExpectedConditions.alertIsPresent());

		Assert.assertTrue(alert.getText().contains(alertText));
		alert.accept();

		Reporter.log("done login");

	}

}
