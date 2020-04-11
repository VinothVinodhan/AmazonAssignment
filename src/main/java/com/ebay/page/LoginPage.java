package com.ebay.page;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.base.BaseSuite;

import io.appium.java_client.android.AndroidDriver;

public class LoginPage extends BaseSuite {

	AndroidDriver driver = null;

	@FindBy(className = "android.widget.EditText")
	private WebElement loginText;

	@FindBy(id = "com.amazon.mShop.android.shopping:id/sign_in_button")
	private WebElement signIn;

	@FindBy(className = "android.widget.RadioButton")
	private List<WebElement> radioBtns;

	@FindBy(className = "android.widget.RadioButton")
	private WebElement radioBtn;

	@FindBy(className = "android.widget.Button")
	private WebElement continueButton;

	@FindBy(className = "android.widget.CheckBox")
	private WebElement showPassword;

	@FindBy(id = "com.amazon.mShop.android.shopping:id/chrome_action_bar_cart_image")
	private WebElement cartIcon;

	/**
	 * 
	 * @param driver
	 */
	public LoginPage(AndroidDriver driver) {
		super();
	}

	/**
	 * Method is to login to the application
	 * 
	 * @param mobileNumber
	 * @param password
	 * @author vinothkumar.p08@infosys.com
	 */
	private void login(String methodName, String mobileNumber, String password) {

		getScreenShot(driver, "Before Login screen", methodName);
		waitForElement(driver, signIn);
		signIn.click();

		waitForElement(driver, radioBtn);
		for (WebElement radioButton : radioBtns) {
			System.out.println(radioButton.getText());
			if (radioButton.getText().contains("Login. Already a customer?")) {
				radioButton.click();
				getScreenShot(driver, "Login Method", methodName);
			}
		}

		waitForElement(driver, loginText);
		loginText.click();
		loginText.sendKeys(mobileNumber);

		waitForElement(driver, continueButton);
		continueButton.click();

		waitForElement(driver, loginText);
		loginText.click();
		loginText.sendKeys(password);

		// De-Selecting the show password check box
		try {
			waitForElement(driver, showPassword);
			showPassword.click();
		} catch (Exception e) {
			// TODO: handle exception
		}

		waitForElement(driver, continueButton);
		continueButton.click();

	}

	public void clickCart() {
		waitForElement(driver, cartIcon);
		cartIcon.click();
	}
}
