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
	public void purchaseItem(String methodName, String searchProduct, String requiredItem, String mobileNumber,
			String password, String language, String pincode) {
		Result result = new Result();
		HomePage appPage = new HomePage("Android", "Amazon");

		// getting driver from result class
		driver = (AndroidDriver) result.getDriver();

		// Invoking method for login
		LoginPage login = new LoginPage(driver);
		login.login(methodName, mobileNumber, password);

		// invoking method for search and select the product
		result = appPage.searchAndSelect(methodName, searchProduct, requiredItem, mobileNumber, password, language,
				pincode);

		// Invoking method to validate product page
		ProductPage product = new ProductPage(driver);
		// Calling method to handle product page.
		product.product(methodName, language);

		CheckOut checkoutPage = new CheckOut(driver);
		checkoutPage.cartPage(methodName);

		// Put the information into log file.
		log.info("Items are: " + result.getProductName() + "&" + result.getProductQuantiy() + "&"
				+ result.getSizeOfOil());

		// Invoking method to compare the strings
		BaseSuite.compareMessage("Product Name Comparision between input and appeared on checkout page",
				result.getProductName(), requiredItem);

		// Comparing cost on Cart & product page
		BaseSuite.compareMessage("Comparing Cost of an item between product selected page & Cart page.",
				result.getProductCost(), result.getCostOnCart());

	}

}