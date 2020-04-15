package com.base;

import io.appium.java_client.AppiumDriver;

public class Result {

	private static String sizeOfOil = null;
	private static String productName = null;
	private static String productQuantiy = null;
	private static String productCost = null;
	private static String costOnCart = null;
	private static AppiumDriver driver = null;

	public String getSizeOfOil() {
		return sizeOfOil;
	}

	public void setSizeOfOil(String sizeOfOil) {
		this.sizeOfOil = sizeOfOil;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getProductQuantiy() {
		return productQuantiy;
	}

	public void setProductQuantiy(String productQuantiy) {
		this.productQuantiy = productQuantiy;
	}

	public String getProductCost() {
		return productCost;
	}

	public void setProductCost(String productCost) {
		this.productCost = productCost;
	}

	public String getCostOnCart() {
		return costOnCart;
	}

	public void setCostOnCart(String costOnCart) {
		this.costOnCart = costOnCart;
	}

	public AppiumDriver getDriver() {
		return driver;
	}

	public void setDriver(AppiumDriver driver) {
		this.driver = driver;
	}
}
