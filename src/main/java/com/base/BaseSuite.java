package com.base;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map;
import java.util.Random;

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
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.pojo.AppInfoPojo;

import io.appium.java_client.MobileDriver;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;

public class BaseSuite {
	static Logger log = Logger.getLogger(BaseSuite.class);
	static AndroidDriver driver;

	static ExtentTest logger = null;
	static ExtentReports report = null;
	static ExtentHtmlReporter htmlReporter;
	static byte[] jsonData = null;
	static AppInfoPojo appinfo = new AppInfoPojo();
	static ObjectMapper objectMapper = new ObjectMapper();

	public static final int WAIT_TEN_SECONDS = 10;

	/**
	 * This method is an optional. We can extend to ExtendReport class for
	 * report generation.
	 * 
	 * @author vinothkumar.p08@infosys.com
	 */
	@BeforeSuite
	public void extendReport() {
		ExtendReport.extendReport();
	}

	@BeforeTest
	/**
	 * Initialize Extent Report before Test
	 * 
	 * @author vinothkumar.p08@infosys.com
	 */
	public static void extentReport() {
		htmlReporter = new ExtentHtmlReporter("./TestReports/ExtentReport/TestReport.html");
		System.out.println("Report created");
		report = new ExtentReports();
		report.attachReporter(htmlReporter);
		logger = report.createTest("Search the Product",
				"Search the product based on user input and add it to the cart.");

	}

	@AfterTest
	/**
	 * After Test Method
	 * 
	 * @author vinothkumar.p08@infosys.com
	 */
	public void completeTestCase() {
		report.flush();
		AppiumDriverFactory.closeApp();
		AppiumDriverFactory.stopAppium();
		logInfo("Appium Server Stopped");

	}

	/*
	 * public void reportTitle(String title, String discription) { logger =
	 * report.createTest(title, discription); }
	 */

	/**
	 * To have information on extent report.
	 * 
	 * @param info
	 * @author vinothkumar.p08@infosys.com
	 */
	public static void logInfo(String info) {
		logger.log(Status.INFO, info);
		// ExtendReport.logInfo(info);
	}

	/**
	 * This method is to show the pass status
	 * 
	 * @param details
	 * @author vinothkumar.p08@infosys.com
	 */
	public static void passStatus(String details) {
		logger.log(Status.PASS, details);
	}

	/**
	 * This method is to show the fail status
	 * 
	 * @param details
	 * @author vinothkumar.p08@infosys.com
	 */
	public static void faileStatus(String details) {
		logger.log(Status.FAIL, details);
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
		// To add screenshot path in report
		logger.log(Status.INFO, "ScreenShotPath: " + ssName);

		try {
			logger.log(Status.INFO, screenshotName + " Attached Below");
			logger.info(screenshotName, MediaEntityBuilder.createScreenCaptureFromPath(ssName).build());
			// logger.addScreenCaptureFromPath(ssName);
			// logger.addScreencastFromPath(ssName);
			// logger.info(screenshotName,
			// MediaEntityBuilder.createScreenCaptureFromPath(ssName).build());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	/**
	 * Swipe down in mobile app
	 * 
	 * @param driver
	 * @author vinothkumar.p08@infosys.com
	 */
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

	/**
	 * Swipe based on co-ordinates
	 * 
	 * @param driver
	 * @author vinothkumar.p08@infosys.com
	 */
	public static void swipe(MobileDriver driver) {
		TouchAction ta = new TouchAction(driver);
		int x = 247;
		int y = 913;
		int b = 157;
		ta.press(x, y).waitAction(1000).moveTo(x, b).release().perform();
	}

	/**
	 * Swipe based on co-ordinates
	 * 
	 * @param driver
	 * @author vinothkumar.p08@infosys.com
	 */
	public static void swipeDown(MobileDriver driver) {
		TouchAction ta = new TouchAction(driver);
		int x = 268;
		int y = 176;
		int b = 350;
		ta.press(x, y).waitAction(1000).moveTo(x, b).release().perform();
	}

	/**
	 * Wait for an element
	 * 
	 * @param driver
	 * @param element
	 * @author vinothkumar.p08@infosys.com
	 */
	public static void waitForElement(MobileDriver driver, WebElement element) {
		WebDriverWait wait = new WebDriverWait(driver, 60);
		wait.until(ExpectedConditions.visibilityOf(element));
	}

	/**
	 * Wait for given seconds and click the element.
	 * 
	 * @param driver
	 * @param element
	 * @param waitTimeInSeconds
	 * @author vinothkumar.p08@infosys.com
	 */
	public void waitAndClick(MobileDriver driver, WebElement element, int waitTimeInSeconds) {
		WebDriverWait wait = new WebDriverWait(driver, waitTimeInSeconds);
		wait.until(ExpectedConditions.elementToBeClickable(element));
		element.click();
	}

	/**
	 * This method is to enter given string into a text editor.
	 * 
	 * @param driver
	 * @param element
	 * @param waitTimeInSeconds
	 * @param text
	 * @author vinothkumar.p08@infosys.com
	 */
	public void enterText(MobileDriver driver, WebElement element, int waitTimeInSeconds, String text) {
		WebDriverWait wait = new WebDriverWait(driver, waitTimeInSeconds);
		wait.until(ExpectedConditions.elementToBeClickable(element));
		element.click();
		element.clear();
		element.clear();
		element.sendKeys(text);
	}

	/**
	 * To get the product Name
	 * 
	 * @param elements
	 * @param productName
	 * @author vinothkumar.p08@infosys.com
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
	 * @author vinothkumar.p08@infosys.com
	 */
	public static void compareMessage(String discription, String actualMessage, String expectedMessage) {
		if (expectedMessage != null && actualMessage != null) {
			if (expectedMessage.trim().equals(actualMessage)) {
				log.info("Expected and Actual Messages are same");
				log.info("Expected Message: " + expectedMessage);
				log.info("Actual Message: " + actualMessage);
				logger.log(Status.INFO, "Expected and Actual Messages are same");
				logger.log(Status.INFO, "Expected Message: " + "<b>" + expectedMessage + "</b>");
				logger.log(Status.INFO, "Actual Message: " + "<b>" + actualMessage + "</b>");
				logger.log(Status.PASS,
						discription + "Expected = <font face=\"verdana\" color=\"green\">" + expectedMessage + "</font>"
								+ "Actual = <font face=\"verdana\" color=\"green\"> " + actualMessage + "</font>");
				logger.log(Status.PASS, "Test Case Passed");

			} else {

				logger.log(Status.FAIL,
						"Expected = <font face=\"verdana\" color=\"red\"> " + expectedMessage + "</font><br/>"
								+ "Actual = <font face=\"verdana\" color=\"red\">" + actualMessage + "</font><br/>");
				logger.log(Status.FAIL, "Test Case Failed");

				Assert.fail(
						"Actual & Expected Messages are not matched. Hence failing the validation. Compared messages are: "
								+ actualMessage + " & " + expectedMessage);
			}
		} else {
			Assert.fail("Compared Messages are: " + actualMessage + " & " + expectedMessage);
		}
	}

	/**
	 * Handling Excel File
	 * 
	 * @param xlFilePath
	 * @param sheetName
	 * @return
	 * @throws Exception
	 * @author vinothkumar.p08@infosys.com
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

	/**
	 * Method will give random integer value.
	 * 
	 * @return
	 */
	public static int randomInt() {
		int max = 3;
		Random rand = new Random();
		int randomIntValue = rand.nextInt(max);
		return randomIntValue;
	}

}
