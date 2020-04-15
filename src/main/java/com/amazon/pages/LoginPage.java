package com.amazon.pages;

import java.util.List;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.base.BaseSuite;
import com.base.Result;

import io.appium.java_client.android.AndroidDriver;

public class LoginPage extends BaseSuite {
	Logger log = Logger.getLogger(LoginPage.class);

	static AndroidDriver driver;
	Result result;

	@FindBy(className = "android.widget.EditText")
	private WebElement loginText;

	@FindBy(id = "com.amazon.mShop.android.shopping:id/sign_in_button")
	private WebElement signIn;

	@FindBy(id = "com.amazon.mShop.android.shopping:id/sign_in_button")
	private WebElement signInMob;

	@FindBy(id = "com.amazon.mShop.android.shopping:id/sign_in_button")
	private WebElement signInAnd;

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
	 * Constructor to pass driver and initialize page factory.
	 * 
	 * @param driver
	 * @author vinothkumar.p08@infosys.com
	 */
	public LoginPage(AndroidDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);

	}

	/**
	 * Method is to login to the application whose is having an account with
	 * valid credintials.
	 * 
	 * @param mobileNumber
	 * @param password
	 * @author vinothkumar.p08@infosys.com
	 */
	public void login(String methodName, String mobileNumber, String password) {

		// To take screen shot
		getScreenShot(driver, "Before Login screen", methodName);

		// wait sign in button to be appeared
		waitForElement(driver, signInMob);
		// wait and click on sign in button
		waitAndClick(driver, signIn, BaseSuite.WAIT_TEN_SECONDS);
		// printing information in log file
		log.info("Clicked on Sign In");
		// printing information in extent report
		BaseSuite.logInfo("Clicked on Sign In");

		// Method is to select Already a customer? radio button
		loginMethod(methodName);

		// To enter mobile number
		waitForElement(driver, loginText);
		enterText(driver, loginText, BaseSuite.WAIT_TEN_SECONDS, mobileNumber);
		log.info("Entered Mobile Number");
		BaseSuite.logInfo("Entered Mobile Number");

		waitForElement(driver, continueButton);
		waitAndClick(driver, continueButton, BaseSuite.WAIT_TEN_SECONDS);
		log.info("Clicked on continue Button after entering Mobile Number");
		BaseSuite.logInfo("Clicked on continue Button after entering Mobile Number");

		// De select show password checkbox
		deselectShowPassword();

		// Entering Password
		waitForElement(driver, loginText);
		enterText(driver, loginText, BaseSuite.WAIT_TEN_SECONDS, password);
		log.info("Entered Password.");
		BaseSuite.logInfo("Entered Password.");

		// Clicking on continue button once user enters password.
		waitForElement(driver, continueButton);
		waitAndClick(driver, continueButton, BaseSuite.WAIT_TEN_SECONDS);
		log.info("Clicked on continue Button after entering Password");
		BaseSuite.logInfo("Clicked on continue Button after entering Password");

	}

	/**
	 * Method is to De-select show password check box. to avoid taking
	 * screenshot of password
	 * 
	 * @author vinothkumar.p08@infosys.com
	 */
	private void deselectShowPassword() {
		// De-Selecting the show password check box
		try {
			waitForElement(driver, showPassword);
			waitAndClick(driver, showPassword, BaseSuite.WAIT_TEN_SECONDS);
		} catch (Exception e) {
			log.info(e);
			e.printStackTrace();
		}
	}

	/**
	 * Method is to click Login. Already a customer? radio button for login.
	 * 
	 * @param methodName
	 * @author vinothkumar.p08@infosys.com
	 */
	private void loginMethod(String methodName) {
		// wait for login method radio buttons to be displayed
		waitForElement(driver, radioBtn);
		for (WebElement radioButton : radioBtns) {
			System.out.println(radioButton.getText());
			if (radioButton.getText().contains("Login. Already a customer?")) {
				// wait and click on radio button
				waitAndClick(driver, radioButton, BaseSuite.WAIT_TEN_SECONDS);
				// printing information in log file
				log.info("Selected Login.Already a customer? Option");
				// printing information in extent report
				BaseSuite.logInfo("Selected Login.Already a customer? Option");
				// To take screen shot
				getScreenShot(driver, "Login Method", methodName);
			}
		}
	}

}
