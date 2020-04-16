package com.amazon.api;

import org.apache.log4j.Logger;

import com.amazon.pages.CheckOut;
import com.amazon.pages.HomePage;
import com.amazon.pages.LoginPage;
import com.amazon.pages.ProductPage;
import com.base.BaseSuite;
import com.base.Result;

import io.appium.java_client.android.AndroidDriver;

public class AppFlowImpl {
	Logger log = Logger.getLogger(AppFlowImpl.class);
	static AndroidDriver driver = null;

	/**
	 * Method is to purchase an item based on user's input
	 * 
	 * @param methodName
	 * @param searchProduct
	 * @param requiredItem
	 * @param mobileNumber
	 * @param password
	 * @param language
	 * @param pincode
	 * @author vinothkumar.p08@infosys.com
	 */
	public void purchaseItemApi(String methodName, String searchProduct, String requiredItem, String mobileNumber,
			String password, String language, String pincode) {
		Result result = new Result();
		HomePage appPage = new HomePage("Android", "Amazon");

		// Getting driver from result class
		driver = (AndroidDriver) result.getDriver();

		// Method to handle login page
		LoginPage login = new LoginPage(driver);
		login.login(methodName, mobileNumber, password);

		// Method tor search and select the product
		result = appPage.searchAndSelectProduct(methodName, searchProduct, requiredItem, mobileNumber, password, language,
				pincode);

		// Method to validate product page
		ProductPage product = new ProductPage(driver);
		product.validateProduct(methodName, language);

		CheckOut checkoutPage = new CheckOut(driver);
		checkoutPage.validateCartPage(methodName);

		// Put the information into log file.
		log.info("Items are: " + result.getProductName() + "&" + result.getProductQuantiy() + "&"
				+ result.getSizeOfOil());

		// Method to compare the inputs
		BaseSuite.compareMessage("Product Name Comparision between input and appeared on checkout page",
				result.getProductName(), requiredItem);

		// Validating cost on Product detail & Cart page
		BaseSuite.validateMessage("Comparing Cost of an item between product selected page & Cart page.",
				result.getProductCost(), result.getCostOnCart());

	}

}