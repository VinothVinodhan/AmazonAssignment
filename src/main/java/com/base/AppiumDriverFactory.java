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

import com.ebay.page.EbayAppPage;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.pojo.AppInfoPojo;

import io.appium.java_client.AppiumDriver;
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
	static // Android Driver
	AppiumDriver driver = null;
	// static ExtendReport report = new ExtendReport();
	Logger log = Logger.getLogger(AppiumDriverFactory.class);

	/**
	 * To Read json file using AppInfoPojo
	 * 
	 * @throws IOException
	 * @author vinothkumar.p08@infosys.com
	 */
	private static void readAppInfo() throws IOException {
		// read json file data to String
		jsonData = Files
				.readAllBytes(Paths.get(System.getProperty("user.dir") + File.separator + "//config//AppsInfo.json"));

		// convert json string to object
		appinfo = objectMapper.readValue(jsonData, AppInfoPojo.class);
	}

	/**
	 * To start appium server
	 * 
	 * @throws IOException
	 * @author vinothkumar.p08@infosys.com
	 */
	public static void startAppium() throws IOException {
		readAppInfo();
		builder = new AppiumServiceBuilder();
		builder.withIPAddress(appinfo.getAppiumIP());
		builder.usingPort(appinfo.getPort());
		builder.withArgument(GeneralServerFlag.SESSION_OVERRIDE);

		// Start
		service = AppiumDriverLocalService.buildService(builder);
		service.start();

		BaseSuite.logInfo("Appium Server Started");
		System.out.println("Appium Server Started");
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
			System.out.println("Appium Server Stopped");
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

	public static WebDriver getAppiumDriver() throws IOException {
		String devicename = "Android";
		startAppium();
		// convert json string to object
		appinfo = objectMapper.readValue(jsonData, AppInfoPojo.class);
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
		cap.setCapability("appActivity", appinfo.getAPPACTIVITY());
		cap.setCapability("appWaitActivity", appinfo.getAPPACTIVITY());
		cap.setCapability("automationName", "uiautomator2");

		BaseSuite.logInfo(
				"Appium URL is: " + "http://" + appinfo.getAppiumIP().trim() + ":" + appinfo.getPort() + "/wd/hub");
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
		System.out.println("Closed the App");
	}

}
