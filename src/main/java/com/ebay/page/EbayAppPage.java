package com.ebay.page;

import java.io.IOException;
import java.util.List;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.base.AppiumDriverFactory;
import com.base.BaseSuite;
import com.base.Result;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidKeyCode;

public class EbayAppPage extends BaseSuite {

	Result result = new Result();
	public String deviceName = null;
	AndroidDriver driver = null;
	AppiumDriverFactory obj = new AppiumDriverFactory();
	Logger log1 = Logger.getLogger(EbayAppPage.class);

	public EbayAppPage(String deviceName, String appName) {
		try {
			driver = (AndroidDriver) obj.getAppiumDriver();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		PageFactory.initElements(driver, this);
	}

	@FindBy(id = "com.amazon.mShop.android.shopping:id/rs_search_src_text")
	private WebElement searchField;

	@FindBy(id = "com.amazon.mShop.android.shopping:id/item_title")
	private List<WebElement> itemTitle;

	@FindBy(id = "com.amazon.mShop.android.shopping:id/item_title")
	private WebElement firstItemTitle;

	@FindBy(id = "add-to-cart-button")
	private WebElement addToCartBtn;

	@FindBy(id = "com.amazon.mShop.android.shopping:id/chrome_action_bar_cart_image")
	private WebElement cartIcon;

	@FindBy(className = "android.widget.Button")
	private List<WebElement> btn;

	@FindBy(className = "android.widget.Button")
	private WebElement continueButton;

	@FindBy(className = "android.widget.RadioButton")
	private List<WebElement> radioBtns;

	@FindBy(className = "android.widget.RadioButton")
	private WebElement radioBtn;

	@FindBy(className = "android.widget.Image")
	private List<WebElement> text;

	@FindBy(className = "android.view.View")
	private WebElement textOnThePage;

	@FindBy(className = "android.view.View")
	private List<WebElement> textsOnThePage;

	@FindBy(className = "android.widget.Spinner")
	private WebElement quantity;

	@FindBy(id = "com.amazon.mShop.android.shopping:id/sign_in_button")
	private WebElement signIn;

	@FindBy(xpath = "//android.view.View[@bounds='[48,317][492,386]']")
	private WebElement langSelectDropdown;

	@FindBy(className = "android.widget.EditText")
	private WebElement loginText;

	@FindBy(id = "continue")
	private WebElement continueButton1;

	@FindBy(className = "android.widget.TextView")
	private WebElement enterPincode;

	@FindBy(id = "com.amazon.mShop.android.shopping:id/loc_ux_pin_code_button")
	private WebElement enterPin;

	@FindBy(className = "android.widget.CheckBox")
	private WebElement showPassword;

	String sizeOfOil = null;
	String productName = null;
	String quantityOfProduct = null;

	/**
	 * To search for the product
	 * 
	 * @param methodName
	 * @param searchProduct
	 * @param requiredItem
	 * @param mobileNumber
	 * @param password
	 * @param language
	 * @param pincode
	 * @return
	 */
	public Result searchPurchaseItem(String methodName, String searchProduct, String requiredItem, String mobileNumber,
			String password, String language, String pincode) {

		// Invoking method to login
		login(methodName, mobileNumber, password);

		// Entering Pincode is optional
		// enterPincode();

		// Selecting language after loggd into the account
		selectLanguage(methodName, language);

		// Search and Product
		searchItem(methodName, requiredItem);

		System.out.println(driver.getPageSource());
		enterPincode(pincode);

		// wait for an element
		waitForElement(driver, firstItemTitle);
		// Select an item from searched result
		selectItem(requiredItem);

		// ake screenshot
		BaseSuite.getScreenShot(driver, "Search Result 1", methodName);
		log1.info("Search Result Page screenshot taken");

		// To get size of the product
		String sizeOfItem = sizeOfProduct(methodName);
		System.out.println("Returned from sizeOfItem method: " + sizeOfItem);

		// Below is to take Rupees just below the Size: 5 litre button
		System.out.println("Rupees on the page: " + textOnThePage.getText());

		// to swipe down till quantity appear
		for (int a = 0; a < 2; a++) {
			swipe(driver);
		}

		// below code is to take quantity from dropdown
		String qty = readQuantity(methodName);
		System.out.println("Selected Quantity: " + qty);

		System.out.println("Add to card button: " + driver.getPageSource());

		// To click on add to cart
		clickAddToCart(methodName);
		BaseSuite.getScreenShot(driver, "After Clicking on Add To Cart", methodName);

		waitForElement(driver, cartIcon);
		cartIcon.click();
		// To get the product name on the cart page
		String requiredProductName = getTextOnPage(driver, text, productName);
		System.out.println("Validated ProductName on Cart Page is: " + requiredProductName);
		return result;

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

	/**
	 * Method is to Enter Pincode
	 * 
	 * @param pincode
	 * @author vinothkumar.p08@infosys.com
	 */
	private void enterPincode(String pincode) {
		try {
			waitForElement(driver, enterPincode);
			enterPincode.click();
		} catch (Exception e) {
			// TODO: handle exception
		}
		System.out.println("Enter Pincode text editor pagesoure: " + driver.getPageSource());

		try {
			enterPin.click();
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	/**
	 * To select language based on user input
	 * 
	 * @param language
	 * @author vinothkumar.p08@infosys.com
	 */
	private void selectLanguage(String methodName, String language) {
		waitForElement(driver, radioBtn);
		// Selecting Lanugage based on user input
		for (WebElement radioButton : radioBtns) {
			System.out.println("Languages appeared on the screen: " + radioButton.getText());
			if (radioButton.getText().contains(language)) {
				radioButton.click();
			}
		}
		getScreenShot(driver, "Selected Langiage", methodName);

		for (WebElement saveButton : btn) {
			System.out.println("Buttons name: " + saveButton.getText());
			if (saveButton.getText().contains("Save")) {
				saveButton.click();
			}
		}

	}

	/**
	 * To read the quantity appeared on product page before clicking add to cart
	 * button.
	 * 
	 * @author vinothkumar.p08@infosys.com
	 */
	private String readQuantity(String methodName) {
		// below code is to take quantity from dropdown
		System.out.println("Quantity of the product: " + quantity.getText());
		quantityOfProduct = quantity.getText();
		result.setProductQuantiy(quantityOfProduct);
		getScreenShot(driver, "Quantity", methodName);
		return quantityOfProduct;
	}

	/**
	 * To click One-Time-Purchase radio button. This is an optional
	 * 
	 * @author vinothkumar.p08@infosys.com
	 */
	private void selectOneTimePurchase() {
		for (WebElement radioBtns : radioBtns) {
			System.out.println("Radio button available: " + radioBtns.getText());
			if (radioBtns.getText().contains("One-time purchase")) {
				String itemValue = radioBtns.getText();
				System.out.println("Item value on One-Time Purchase is: " + itemValue);
				int size = itemValue.length();
				System.out.println("Size of the radio button text is: " + size);
				String cost = itemValue.substring(11);
				System.out.println("Cost of item: " + cost);

			}
		}
	}

	/**
	 * To Search a product
	 * 
	 * @param searchProduct
	 * @author vinothkumar.p08@infosys.com
	 */
	private void searchItem(String methodName, String searchProduct) {
		System.out.println(driver.getPageSource());
		searchField.click();

		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		searchField.clear();
		searchField.sendKeys(searchProduct);
		driver.pressKeyCode(AndroidKeyCode.ENTER);
		getScreenShot(driver, "Searched Result", methodName);
	}

	/**
	 * This is to select an item based on user's input
	 * 
	 * @param requiredItem
	 * @author vinothkumar.p08@infosys.com
	 */
	private void selectItem(String requiredItem) {
		for (WebElement ele : itemTitle) {
			System.out.println("Title name: " + ele.getText());
			if (ele.getText().contains(requiredItem)) {
				productName = ele.getText();
				System.out.println("Product Name: " + productName);
				// Setting product name to result class object
				result.setProductName(productName);
				ele.click();
				break;
			}

		}
	}

	/**
	 * To get the size of the product
	 * 
	 * @param methodName
	 * @author vinothkumar.p08@infosys.com
	 */
	private String sizeOfProduct(String methodName) {
		for (WebElement btns : btn) {
			System.out.println("Button name: " + btns.getText());
			if (btns.getText().contains("Size: ")) {
				String size = btns.getText();
				System.out.println("Size of the procuct is: " + size);
				System.out.println(size.length());
				sizeOfOil = size.substring(7);
				System.out.println(size.substring(7));
				result.setSizeOfOil(sizeOfOil);
				BaseSuite.getScreenShot(driver, "Selected Item", methodName);
				break;
			}
		}
		return sizeOfOil;
	}

	/**
	 * To click add to cart button
	 * 
	 * @param methodName
	 * @param buttonName
	 * @author vinothkumar.p08@infosys.com
	 */
	private void clickAddToCart(String methodName) {
		// swipe(driver);
		for (WebElement btns : btn) {
			System.out.println("Button name: " + btns.getText());
			if (btns.getText().contains("Add to Cart")) {
				BaseSuite.getScreenShot(driver, "Before clicking Add to Cart", methodName);
				btns.click();
				break;
			}

		}
	}

	/**
	 * To get the product Name
	 * 
	 * @param elements
	 * @param productName
	 * @author vinothkumar.p08@infosys.com
	 */
	private void getTextOnPageBackup(List<WebElement> elements, String productName) {

		for (WebElement ele : elements) {
			System.out.println("Text on the page: " + ele.getText());
			if (ele.getText().equals(productName)) {
				System.out.println("Item name on Cart Page: " + ele.getText());
				break;
			}
		}
	}

}
