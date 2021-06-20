package com.TRY.listeners;

import java.io.IOException;
import java.util.Arrays;
import java.util.Date;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.TRY.testBase.Base;
import com.TRY.utilities.ExtentClass;
import com.TRY.utilities.UtilTest;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.Markup;
import com.aventstack.extentreports.markuputils.MarkupHelper;


public class CustomListener extends Base implements ITestListener {

	static Date d = new Date();
	static String filename = "Extent_" + d.toString().replace(":", "_").replace(" ", "_") + ".html";

	ExtentReports repo = ExtentClass.getInstance(System.getProperty("user.dir") + "//Reports//" + filename);

	public static ThreadLocal<ExtentTest> testReport = new ThreadLocal<ExtentTest>();

	@Override
	public void onTestStart(ITestResult result) {

		ExtentTest test = repo.createTest(result.getMethod().getMethodName());
		testReport.set(test);
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		String methodname = result.getMethod().getMethodName();
		String logtext = "<b>" + "Test case:-" + methodname + " Passed" + "<b>";
		Markup m = MarkupHelper.createLabel(logtext, ExtentColor.GREEN);
		testReport.get().pass(m);

	}

	@Override
	public void onTestFailure(ITestResult result) {

		String exceptionMess = Arrays.toString(result.getThrowable().getStackTrace());
		testReport.get()
				.fail("<details>" + "<summary>" + "<b>" + "<font color=" + "red>" + "Exception Occured:Click to see"
						+ "</font>" + "</b >" + "</summary>" + exceptionMess.replaceAll(",", "<br>") + "</details>"
						+ " \n");
		try {

			UtilTest.captureScreen();
			testReport.get().fail("<b>" + "<font color=" + "red>" + "Screenshot of failure" + "</font>" + "</b>",
					MediaEntityBuilder.createScreenCaptureFromPath(UtilTest.screenshotName)
							.build());
		} catch (IOException e) {

		}
		String failureLogg = "TEST CASE FAILED";
		Markup m = MarkupHelper.createLabel(failureLogg, ExtentColor.RED);
		testReport.get().log(Status.FAIL, m);
	}

	@Override
	public void onTestSkipped(ITestResult result) {
		String methodName=result.getMethod().getMethodName();
		String logText="<b>"+"Test Case:- "+ methodName+ " Skipped"+"</b>";		
		Markup m=MarkupHelper.createLabel(logText, ExtentColor.YELLOW);
		testReport.get().skip(m);

	}

	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onStart(ITestContext context) {
		System.out.println("on start" + context);

	}

	@Override
	public void onFinish(ITestContext context) {
		if (repo != null) {

			repo.flush();
		}

	}

}
