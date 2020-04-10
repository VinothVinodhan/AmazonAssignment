package com.base;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.ITestContext;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeSuite;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.pojo.AppInfoPojo;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import io.appium.java_client.MobileDriver;
import io.appium.java_client.TouchAction;

public class BaseSuite {
	static Logger log = Logger.getLogger(BaseSuite.class);
	static WebDriver driver;

	static ExtentTest logger;
	static ExtentReports report;
	static byte[] jsonData = null;
	static AppInfoPojo appinfo = new AppInfoPojo();
	static ObjectMapper objectMapper = new ObjectMapper();

	// @AfterTest
	public void afterSuite() {
		AppiumDriverFactory.closeApp();
		AppiumDriverFactory.stopAppium();
		logInfo("Appium Server Stopped");
	}

	@BeforeSuite
	public static void extendReport() {
		report = new ExtentReports(System.getProperty("user.dir") + "//TestReports//ExtentReport//TestReport.html");
		logger = report.startTest("Automation Report");
	}

	/**
	 * To have information on extent report.
	 * 
	 * @param info
	 * @author vinothkumar.p08@infosys.com
	 */
	public static void logInfo(String info) {
		logger.log(LogStatus.INFO, info);

	}

	/**
	 * Take a Screenshot and store its in TestReports/Screenshots folder in
	 * current directory.
	 * 
	 * @param driver
	 * @param screenshotName
	 * @author vinothkumar.p08@infosys.com
	 */
	public static void getScreenShot(WebDriver driver, String screenshotName, String methodName) {
		File src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		try {
			FileUtils.copyFile(src, new File(System.getProperty("user.dir") + File.separator
					+ "\\TestReports\\ScreenShot\\" + methodName + "\\" + screenshotName + ".png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String ssName = System.getProperty("user.dir") + File.separator + "\\TestReports\\ScreenShot\\" + methodName
				+ "\\" + screenshotName + ".png";
		String image = logger.addScreenCapture(ssName);

	}

	public static void swipeDownInMobileApp(MobileDriver driver) {
		Dimension dim = driver.manage().window().getSize();
		int height = dim.getHeight();
		int width = dim.getWidth();
		int x = width / 2;
		int top_y = (int) (height * 0.80);
		int bottom_y = (int) (height * 0.50);
		System.out.println("coordinates :" + x + "  " + top_y + " " + bottom_y);
		TouchAction ts = new TouchAction(driver);
		ts.press(x, top_y).moveTo(x, bottom_y).release().perform();

	}

	public static void swipe(MobileDriver driver) {
		TouchAction ta = new TouchAction(driver);
		int x = 247;
		int y = 913;
		int b = 157;
		ta.press(x, y).waitAction(1000).moveTo(x, b).release().perform();
	}

	public static void waitForElement(MobileDriver driver, WebElement element) {
		WebDriverWait wait = new WebDriverWait(driver, 60);
		wait.until(ExpectedConditions.visibilityOf(element));
	}

	/**
	 * To get the product Name
	 * 
	 * @param elements
	 * @param productName
	 */
	public static String getTextOnPage(MobileDriver driver, List<WebElement> elements, String productName) {
		String validatedText = null;
		for (WebElement ele : elements) {
			System.out.println("Text on the page: " + ele.getText());
			if (ele.getText().equals(productName)) {
				System.out.println("Item name on Cart Page: " + ele.getText());
				validatedText = ele.getText();
				break;
			}
		}
		return validatedText;
	}

	/**
	 * To compare two string objects
	 * 
	 * @param discription
	 * @param actualMessage
	 * @param expectedMessage
	 */
	public static void compareMessage(String discription, String actualMessage, String expectedMessage) {
		if (expectedMessage != null && actualMessage != null) {
			if (expectedMessage.trim().equals(actualMessage)) {
				log.info("Expected and Actual Messages are same");
				log.info("Expected Message: " + expectedMessage);
				log.info("Actual Message: " + actualMessage);
			} else {
				Assert.fail(
						"Actual & Expected Messages are not matched. Hence failing the validation. Compared messages are: "
								+ actualMessage + " & " + expectedMessage);
			}
		} else {
			Assert.fail("Compared Messages are: " + actualMessage + " & " + expectedMessage);
		}
	}

	/**
	 * 
	 * @param xlFilePath
	 * @param sheetName
	 * @return
	 * @throws Exception
	 */
	public Object[][] testData() throws Exception {
		// String s = System.getProperty("user.dir") + File.separator;
		// String s1 = readAppInfo().getTestData().toString().trim();
		// String file = s + s1;
		String exlFilePath = System.getProperty("user.dir") + File.separator
				+ readAppInfo().getTestData().toString().trim();
		readAppInfo().getTestData().toString().trim();
		String sheetName = readAppInfo().getTestSheetName().toString().trim();
		Object[][] excelData = null;
		ExcelRead read = new ExcelRead(exlFilePath);
		int rows = read.getRowCount(sheetName);
		int columns = read.getColumnCount(sheetName);

		excelData = new Object[rows - 1][columns];

		for (int i = 1; i < rows; i++) {
			for (int j = 0; j < columns; j++) {
				excelData[i - 1][j] = read.getCellData(sheetName, j, i);
				System.out.println(excelData[i - 1][j]);
			}

		}
		return excelData;
	}

	/**
	 * To Read json file using AppInfoPojo
	 * 
	 * @throws IOException
	 * @author vinothkumar.p08@infosys.com
	 * @return
	 */
	private static AppInfoPojo readAppInfo() throws IOException {
		// read json file data to String
		jsonData = Files
				.readAllBytes(Paths.get(System.getProperty("user.dir") + File.separator + "//config//AppsInfo.json"));

		// convert json string to object
		appinfo = objectMapper.readValue(jsonData, AppInfoPojo.class);

		return appinfo;
	}

}
