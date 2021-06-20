package com.TRY.testcases;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.annotations.Test;

import com.TRY.testBase.Base;
import com.TRY.utilities.UtilTest;

public class OpenAccountTest extends Base {
	
	
	@Test(dataProviderClass = UtilTest.class, dataProvider = "value")
	public void openAccTest(String name ,String lastname,String currency) throws InterruptedException {
		wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(or.getProperty("openAcc_css"))));
		
		click("openAcc_css");
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath(or.getProperty("custName_xpath"))));
		click("custName_xpath");
		System.out.println( name+" "+lastname);
	
		select("custName_xpath", name+" "+lastname);
		select("currency_css", currency);		
		
	}

}
