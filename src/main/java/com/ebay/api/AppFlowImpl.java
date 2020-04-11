package com.ebay.api;

import com.base.BaseSuite;
import com.base.Result;
import com.ebay.page.EbayAppPage;

import io.appium.java_client.android.AndroidDriver;

public class AppFlowImpl {
	AndroidDriver driver = null;

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
		EbayAppPage appPage = new EbayAppPage("Android", "Amazon");
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// Calling page level class
		Result result = appPage.searchPurchaseItem(methodName, searchProduct, requiredItem, mobileNumber, password,
				language, pincode);

		System.out.println("Items are: " + result.getProductName() + "&" + result.getProductQuantiy() + "&"
				+ result.getSizeOfOil());
		// Invoking method to compare the strings
		BaseSuite.compareMessage("Product Name Comparision between input and appeared on checkout page",
				result.getProductName(), requiredItem);

		// Comparing cost on Cart & product page
		BaseSuite.compareMessage("Comparing Cost of an item between product selected page & Cart page.",
				result.getProductCost(), result.getCostOnCart());
	}

}