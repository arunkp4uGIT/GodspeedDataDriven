package com.TRY.utilities;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.Date;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.io.FileHandler;
import org.testng.annotations.DataProvider;

import com.TRY.testBase.Base;

public class UtilTest extends Base {

	public String path;
	
	public static String screenshotName;

	public static void captureScreen() throws IOException {
		
		Date d = new Date();
		screenshotName = d.toString().replace(":", "_").replace(" ", "_") + ".jpg";
		File scr = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		FileHandler.copy(scr, new File(System.getProperty("user.dir") + "//target//"+screenshotName+".jpg"));
	}

	@DataProvider(name = "value")
	public Object[][] getData(Method m) {
		String sheetname = m.getName();
		int row = read.getRowCount(sheetname);
		int col = read.getColumnCount(sheetname);
		System.out.println(row);
		System.out.println(col);

		Object[][] data = new Object[row - 1][col];

		for (int i = 2; i <= row; i++) {
			for (int j = 0; j < col; j++) {

				data[i - 2][j] = read.getCellData(sheetname, j, i);

			}
		}
		return data;

	}

}
