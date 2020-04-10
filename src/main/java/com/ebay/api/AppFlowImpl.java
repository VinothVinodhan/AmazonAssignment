package com.ebay.api;

import com.base.BaseSuite;
import com.base.Result;
import com.ebay.page.EbayAppPage;

public class AppFlowImpl {
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
	 */
	public void purchaseItem(String methodName, String searchProduct, String requiredItem, String mobileNumber,
			String password, String language, String pincode) {
		EbayAppPage appPage = new EbayAppPage("Android", "Amazon");
		System.out.println("AppOpened");
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Result result = appPage.searchPurchaseItem(methodName, searchProduct, requiredItem, mobileNumber, password, language,
				pincode);
		System.out.println("Items are: " + result.getProductName() + "&" + result.getProductQuantiy() + "&"
				+ result.getSizeOfOil());
		BaseSuite.compareMessage("Product Name Comparision between input and appeared on checkout page",
				result.getProductName(), requiredItem);
	}

}