package com.base;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.pojo.AppInfoPojo;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.AndroidMobileCapabilityType;
import io.appium.java_client.remote.MobileCapabilityType;
import io.appium.java_client.remote.MobilePlatform;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import io.appium.java_client.service.local.flags.GeneralServerFlag;

public class AppiumDriverFactory {
	static AppInfoPojo appinfo = new AppInfoPojo();
	private static AppiumDriverLocalService service = null;
	private static AppiumServiceBuilder builder;
	public static boolean serverstarted;
	// create ObjectMapper instance
	static ObjectMapper objectMapper = new ObjectMapper();
	static byte[] jsonData = null;
	static AndroidDriver driver = null;
	// static ExtendReport report = new ExtendReport();
	static Logger logs = Logger.getLogger(AppiumDriverFactory.class);
	static Result result = new Result();

	/**
	 * To Read json file using AppInfoPojo
	 * 
	 * @throws IOException
	 * @author vinothkumar.p08@infosys.com
	 */
	private static void readAppInfo() {
		// read json file data to String
		try {
			jsonData = Files.readAllBytes(
					Paths.get(System.getProperty("user.dir") + File.separator + "//config//AppsInfo.json"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			logs.info(e);
			BaseSuite.logException(e);
		}

		// convert json string to object
		try {
			appinfo = objectMapper.readValue(jsonData, AppInfoPojo.class);
		} catch (JsonParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			logs.info(e);
			BaseSuite.logException(e);
		} catch (JsonMappingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			logs.info(e);
			BaseSuite.logException(e);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			logs.info(e);
			BaseSuite.logException(e);
		}

	}

	/**
	 * To start appium server
	 * 
	 * @throws IOException
	 * @author vinothkumar.p08@infosys.com
	 */
	public static void startAppium() {
		readAppInfo();
		builder = new AppiumServiceBuilder();
		builder.withIPAddress(appinfo.getAppiumIP());
		builder.usingPort(appinfo.getPort());
		builder.withArgument(GeneralServerFlag.SESSION_OVERRIDE);

		// Start
		service = AppiumDriverLocalService.buildService(builder);
		service.start();

		BaseSuite.logInfo("Appium Server Started");
		BaseSuite.logInfo("Appium Server IP Address: " + appinfo.getAppiumIP());
		BaseSuite.logInfo("Appium Server Port" + appinfo.getPort());
		serverstarted = true;
	}

	/**
	 * To Stop the appium server
	 * 
	 * @author vinothkumar.p08@infosys.com
	 */
	public static void stopAppium() {
		if (serverstarted) {
			service.stop();
			logs.info("Appium Server Stopped");
			BaseSuite.logInfo("Appium Server Stopped");

		} else {
			serverstarted = false;
		}

	}

	/**
	 * To get Appium driver
	 * 
	 * @author vinothkumar.p08@infosys.com
	 * @throws IOException
	 * @author vinothkumar.p08@infosys.com
	 */

	public static WebDriver getAppiumDriver() {
		String devicename = "Android";
		startAppium();
		// convert json string to object
		try {
			appinfo = objectMapper.readValue(jsonData, AppInfoPojo.class);
		} catch (JsonParseException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			logs.info(e1);
			BaseSuite.logException(e1);
		} catch (JsonMappingException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			logs.info(e1);
			BaseSuite.logException(e1);
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			logs.info(e1);
			BaseSuite.logException(e1);
		}
		DesiredCapabilities cap = new DesiredCapabilities();

		File app = new File(System.getProperty("user.dir") + File.separator + appinfo.getPath());
		BaseSuite.logInfo("App Path is: " + app);
		System.out.println("App Path is: " + app);

		cap.setCapability(MobileCapabilityType.APP, app.getAbsolutePath());
		cap.setCapability(MobileCapabilityType.FULL_RESET, "true");
		cap.setCapability(MobileCapabilityType.NEW_COMMAND_TIMEOUT, 1500);
		cap.setCapability("unicodeKeyboard", true);
		cap.setCapability("resetKeyboard", true);

		cap.setCapability(MobileCapabilityType.DEVICE_NAME, devicename);
		cap.setCapability(MobileCapabilityType.PLATFORM_NAME, MobilePlatform.ANDROID);
		cap.setCapability("appPackage", appinfo.getAPPPACKAGE());
		BaseSuite.logInfo("App Package: " + appinfo.getAPPPACKAGE());
		logs.info("App Package: " + appinfo.getAPPPACKAGE());
		cap.setCapability("appActivity", appinfo.getAPPACTIVITY());
		BaseSuite.logInfo("App Activity: " + appinfo.getAPPACTIVITY());
		logs.info("App Activity: " + appinfo.getAPPACTIVITY());
		cap.setCapability("appWaitActivity", appinfo.getAPPACTIVITY());
		cap.setCapability("automationName", "uiautomator2");

		BaseSuite.logInfo(
				"Appium URL is: " + "http://" + appinfo.getAppiumIP().trim() + ":" + appinfo.getPort() + "/wd/hub");
		System.out.println("URL is: " + "http://" + appinfo.getAppiumIP().trim() + ":" + appinfo.getPort() + "/wd/hub");
		logs.info("Appium URL is: " + "http://" + appinfo.getAppiumIP().trim() + ":" + appinfo.getPort() + "/wd/hub");

		try {
			driver = new AndroidDriver(
					new URL("http://" + appinfo.getAppiumIP().trim() + ":" + appinfo.getPort() + "/wd/hub"), cap);
		} catch (MalformedURLException e) {
			e.printStackTrace();
			logs.info(e);
		}

		result.setDriver(driver);
		return driver;
	}

	/**
	 * This method is to just open the app which is already installed in mobile.
	 * 
	 * @return
	 * @throws IOException
	 * @author vinothkumar.p08@infosys.com
	 */
	public static WebDriver getAppiumDriverBackup() throws IOException {
		String devicename = "Android";
		startAppium();
		// convert json string to object
		appinfo = objectMapper.readValue(jsonData, AppInfoPojo.class);
		DesiredCapabilities cap = new DesiredCapabilities();

		File app = new File(System.getProperty("user.dir") + File.separator + appinfo.getPath());
		BaseSuite.logInfo("App Path is: " + app);
		System.out.println("App Path is: " + app);

		cap.setCapability("unicodeKeyboard", false);
		cap.setCapability("resetKeyboard", false);

		cap.setCapability(MobileCapabilityType.DEVICE_NAME, devicename);
		cap.setCapability(MobileCapabilityType.PLATFORM_NAME, MobilePlatform.ANDROID);
		cap.setCapability(MobileCapabilityType.NO_RESET, false);
		cap.setCapability(MobileCapabilityType.FULL_RESET, false);
		cap.setCapability(MobileCapabilityType.NEW_COMMAND_TIMEOUT, 1500);
		cap.setCapability(AndroidMobileCapabilityType.APP_PACKAGE, appinfo.getAPPPACKAGE());
		cap.setCapability(AndroidMobileCapabilityType.APP_ACTIVITY, appinfo.getAPPACTIVITY());
		// cap.setCapability(AndroidMobileCapabilityType.APP_WAIT_ACTIVITY,
		// appinfo.getAPPACTIVITY());

		// cap.setCapability("appActivity", appinfo.getAPPACTIVITY());
		// cap.setCapability("appActivity", appinfo.getAPPACTIVITY());
		// cap.setCapability("automationName", "uiautomator2");

		System.out.println("URL is: " + "http://" + appinfo.getAppiumIP().trim() + ":" + appinfo.getPort() + "/wd/hub");

		try {
			driver = new AndroidDriver(
					new URL("http://" + appinfo.getAppiumIP().trim() + ":" + appinfo.getPort() + "/wd/hub"), cap);
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
		return driver;
	}

	/**
	 * To close the application after the test suite completed.
	 * 
	 * @author vinothkumar.p08@infosys.com
	 */
	public static void closeApp() {
		driver.closeApp();
		logs.info("Closed the App");
		BaseSuite.logInfo("Closed the App");
		driver.removeApp(appinfo.getAPPPACKAGE());
		logs.info("Uninstalled application package: " + appinfo.getAPPPACKAGE());
		BaseSuite.logInfo("Uninstalled application package: " + appinfo.getAPPPACKAGE());
		System.out.println("Closed the App");
	}

}
