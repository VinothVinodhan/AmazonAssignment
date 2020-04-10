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

	@Test(dataProvider = "purchaseItemDataProvider")
	public void purchaseItem(Method method, String mobileNumber, String password, String language, String pincode,
			String searchProduct, String requiredItem) {

		log.info("********************");
		log.info("*****Test Data from Excel File as Below*****");
		log.info("UserName to Login the Application: " + mobileNumber);
		log.info("PassWord of an User: " + password);
		log.info("language to be selected after login: " + language);
		log.info("Pincode: " + pincode);
		log.info("Product Category: " + searchProduct);
		log.info("Item to be selected: " + requiredItem);
		log.info("********************");

		String methodName = method.getName();
		AppFlowImpl impl = new AppFlowImpl();
		impl.purchaseItem(methodName, searchProduct, requiredItem, mobileNumber, password, language, pincode);

	}

	@DataProvider
	public Object[][] purchaseItemDataProvider(ITestContext testContext) throws Exception {
		Object[][] data = testData();
		return data;
	}

}
