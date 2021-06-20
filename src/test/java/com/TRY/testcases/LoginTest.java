package com.TRY.testcases;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.TRY.testBase.Base;

public class LoginTest extends Base {

	@Test
	public void loginAsManger() throws InterruptedException {

		wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(or.getProperty("bml_css"))));
		// driver.findElement(By.cssSelector(or.getProperty("bml"))).click();
		click("bml_css");
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(or.getProperty("addcb_xpath"))));

		Assert.assertTrue(isElementPresent(By.xpath(or.getProperty("addcb_xpath"))));

	}

}
