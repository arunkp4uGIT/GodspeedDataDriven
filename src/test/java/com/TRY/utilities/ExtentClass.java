package com.TRY.utilities;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class ExtentClass {
	
	public static ExtentReports extent;
	
	public static ExtentReports getInstance(String path) {
		
		ExtentSparkReporter htmlReporter=new  ExtentSparkReporter(path);
		htmlReporter.config().setTheme(Theme.STANDARD);
        htmlReporter.config().setDocumentTitle("Title:Automation reports");
        htmlReporter.config().setEncoding("utf-8");
        htmlReporter.config().setReportName("REPORT AUTOMATION BANK");
        
        extent=new ExtentReports();
        extent.attachReporter(htmlReporter);
        extent.setSystemInfo("Automation Tester", "Rahul Arora");
        extent.setSystemInfo("Organization", "Way2Automation");
       
        return extent;

        
        
        
	}

}
