package com.amazon.testcase;

import java.lang.reflect.Method;

import org.apache.log4j.Logger;
import org.testng.ITestContext;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.amazon.api.AppFlowImpl;
import com.base.BaseSuite;

public class AmazonTestCase extends BaseSuite {

	Logger log = Logger.getLogger(AmazonTestCase.class);

	/**
	 * Purchase an item based on user's input
	 * 
	 * @param method
	 * @param mobileNumber
	 * @param password
	 * @param language
	 * @param pincode
	 * @param searchProduct
	 * @param requiredItem
	 * @param testDescription
	 * @param testTitle
	 */
	@Test(dataProvider = "purchaseItemDataProvider")
	public void purchaseItem(Method method, String mobileNumber, String password, String language, String pincode,
			String searchProduct, String requiredItem, String testDescription, String testTitle) {

		// Below are to print the informations into log file.
		log.info("********************");
		log.info("*****Test Data from Excel File as Below*****");
		log.info("language to be selected after login: " + language);
		log.info("Pincode: " + pincode);
		log.info("Product Category: " + searchProduct);
		log.info("Item to be selected: " + requiredItem);
		log.info("Item to be selected: " + testDescription);
		log.info("Item to be selected: " + testTitle);
		log.info("********************");
		// Below are to print the informations into extent report.
		BaseSuite.logInfo("*****Test Data from Excel File as Below*****");
		BaseSuite.logInfo("language to be selected after login: " + language);
		BaseSuite.logInfo("Pincode: " + pincode);
		BaseSuite.logInfo("Product Category: " + searchProduct);
		BaseSuite.logInfo("Item to be selected: " + requiredItem);

		// To get the method name
		String methodName = method.getName();
		// Creating object of AppFlowImpl class
		AppFlowImpl impl = new AppFlowImpl();
		// Invoking method to do purchase
		impl.purchaseItem(methodName, searchProduct, requiredItem, mobileNumber, password, language, pincode);
		log.info("Test Case Completed.");
		BaseSuite.logInfo("Test Case Completed.");

	}

	@DataProvider
	public Object[][] purchaseItemDataProvider(ITestContext testContext) {
		Object[][] data = null;
		try {
			data = testData();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			log.info(e);
			BaseSuite.logException(e);
		}
		return data;
	}

}
