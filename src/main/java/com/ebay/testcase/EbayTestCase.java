package com.ebay.testcase;

import java.lang.reflect.Method;

import org.apache.log4j.Logger;
import org.testng.ITestContext;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.base.BaseSuite;
import com.ebay.api.AppFlowImpl;

public class EbayTestCase extends BaseSuite {

	Logger log = Logger.getLogger(EbayTestCase.class);

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

		log.info("********************");
		log.info("*****Test Data from Excel File as Below*****");
		log.info("language to be selected after login: " + language);
		log.info("Pincode: " + pincode);
		log.info("Product Category: " + searchProduct);
		log.info("Item to be selected: " + requiredItem);
		log.info("Item to be selected: " + testDescription);
		log.info("Item to be selected: " + testTitle);

		log.info("********************");

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

	}

	@DataProvider
	public Object[][] purchaseItemDataProvider(ITestContext testContext) throws Exception {
		Object[][] data = testData();
		return data;
	}

}
