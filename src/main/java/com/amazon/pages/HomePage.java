package com.amazon.pages;

import java.io.IOException;
import java.util.List;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.base.AppiumDriverFactory;
import com.base.BaseSuite;
import com.base.Result;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidKeyCode;
import junit.framework.Assert;

public class HomePage extends BaseSuite {

	Result result = new Result();
	public String deviceName = null;
	static AndroidDriver driver = null;
	AppiumDriverFactory obj = new AppiumDriverFactory();
	Logger log = Logger.getLogger(HomePage.class);

	/**
	 * Constructor to invoke appium server and launch the application
	 * 
	 * @param deviceName
	 * @param appName
	 * @author vinothkumar.p08@infosys.com
	 */
	public HomePage(String deviceName, String appName) {
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

	@FindBy(id = "com.amazon.mShop.android.shopping:id/chrome_action_bar_cart")
	private WebElement cartIcon1;

	@FindBy(xpath = "//android.widget.ImageView[@content-desc='Cart']")
	private WebElement cartIcon2;

	@FindBy(xpath = "/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.support.v4.widget.DrawerLayout/android.widget.RelativeLayout/android.widget.RelativeLayout[2]/android.widget.RelativeLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.LinearLayout[1]/android.widget.RelativeLayout/android.widget.LinearLayout[2]/android.widget.FrameLayout")
	private WebElement cartIcon3;

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

	@FindBy(xpath = "/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.support.v4.widget.DrawerLayout/android.widget.RelativeLayout/android.widget.RelativeLayout[2]/android.widget.RelativeLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.LinearLayout[2]/android.widget.ViewAnimator/android.widget.LinearLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.view.View/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.RelativeLayout/android.widget.RelativeLayout/android.webkit.WebView/android.webkit.WebView/android.view.View/android.view.View/android.view.View/android.view.View/android.view.View/android.view.View[3]/android.view.View[4]/android.view.View/android.view.View[2]/android.view.View[1]/android.view.View/android.view.View/android.widget.EditText")
	private WebElement costElement;

	@FindBy(xpath = "/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.support.v4.widget.DrawerLayout/android.widget.RelativeLayout/android.widget.RelativeLayout[2]/android.widget.RelativeLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.LinearLayout[2]/android.widget.ViewAnimator/android.widget.LinearLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.view.View/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.RelativeLayout/android.widget.RelativeLayout/android.webkit.WebView/android.webkit.WebView/android.view.View/android.view.View/android.view.View/android.view.View/android.view.View/android.view.View/android.view.View[2]/android.view.View[1]/android.view.View/android.view.View/android.view.View[1]/android.view.View[4]/android.view.View[1]/android.view.View/android.view.View[2]")
	private WebElement deleveryBy;

	@FindBy(xpath = "/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.support.v4.widget.DrawerLayout/android.widget.RelativeLayout/android.widget.RelativeLayout[2]/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.LinearLayout[2]/android.widget.ViewAnimator/android.view.View/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.RelativeLayout/android.widget.RelativeLayout/android.webkit.WebView/android.webkit.WebView/android.view.View/android.view.View/android.view.View[1]/android.view.View/android.view.View[4]")
	private WebElement costElementCart;

	String sizeOfOil = null;
	String productName = null;
	String quantityOfProduct = null;
	String productCost = null;

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
	 * @author vinothkumar.p08@infosys.com
	 */
	public Result searchPurchaseItem(String methodName, String searchProduct, String requiredItem, String mobileNumber,
			String password, String language, String pincode) {

		// Invoking method to login
		login(methodName, mobileNumber, password);
		BaseSuite.getScreenShot(driver, "Home Page of an application", methodName);

		// Entering Pin Code is optional
		// enterPincode();

		// Selecting language after loggd into the account
		selectLanguage(methodName, language);

		// Search and Product
		searchSelect(methodName, requiredItem);
		productName = result.getProductName();

		// Take screenshot
		BaseSuite.logInfo("After selecting the product from searched result.");
		BaseSuite.getScreenShot(driver, "Selected Item", methodName);
		log.info("Search Result Page screenshot taken");

		// To get size of the product
		String sizeOfItem = sizeOfProduct(methodName);
		System.out.println("Returned from sizeOfItem method: " + sizeOfItem);

		swipe(driver);

		String costOnProduct = getTextOnPageBackup(textsOnThePage, "rupees");
		result.setProductCost(costOnProduct);
		BaseSuite.logInfo("Product Cost on Selected Item Page: " + costOnProduct);

		// to swipe down till quantity appear
		for (int a = 0; a < 1; a++) {
			swipe(driver);
		}

		// To select One Time Purchase radio button
		try {
			selectOneTimePurchase(methodName);
		} catch (Exception e) {
			// TODO: handle exception
		}

		// below code is to take quantity from dropdown
		String qty = readQuantity(methodName);
		System.out.println("Selected Quantity: " + qty);

		// To click on add to cart
		clickAddToCart(methodName);
		BaseSuite.getScreenShot(driver, "After Clicking on Add To Cart", methodName);

		// // Creating obj for checkout page
		// CheckOut checkoutPage = new CheckOut(driver);
		// checkoutPage.cartPage(methodName);
		//
		// // To handle cart page
		// cartPage(methodName);

		return result;

	}

	public Result searchAndSelect(String methodName, String searchProduct, String requiredItem, String mobileNumber,
			String password, String language, String pincode) {

		// Entering Pin Code is optional
		// enterPincode();

		// Selecting language after logged into the account
		selectLanguage(methodName, language);

		// Search and Product
		searchSelect(methodName, requiredItem);
		productName = result.getProductName();

		// Take screenshot
		BaseSuite.logInfo("After selecting the product from searched result.");
		BaseSuite.getScreenShot(driver, "Selected Item", methodName);
		log.info("Search Result Page screenshot taken");
		return result;

	}

	/**
	 * Handleing cart page by taking cost & name of the product.
	 * 
	 * @param methodName
	 * @author vinothkumar.p08@infosys.com
	 */
	private void cartPage(String methodName) {
		// To click cart icon
		clickCart();

		// To get the product name on the cart page
		String requiredProductName = getTextOnPage(driver, text, productName);
		System.out.println("Validated ProductName on Cart Page is: " + requiredProductName);
		BaseSuite.logInfo("Validated ProductName on Cart Page is: " + requiredProductName);
		log.info("Validated ProductName on Cart Page is: " + requiredProductName);
		BaseSuite.getScreenShot(driver, "Cart Page", methodName);

		// Below code is to get item cost on cart page
		try {
			String costOnCartPage = costElementCart.getText();
			BaseSuite.logInfo("Cost on Cart Page: " + costOnCartPage);
			System.out.println("Cost on Cart Page: " + costOnCartPage);
		} catch (Exception e) {
			// TODO: handle exception
		}

		String costOncartPage = getTextOnPageBackup(textsOnThePage, ".00");
		BaseSuite.logInfo("Cost on Cart Page: " + costOncartPage);
		result.setCostOnCart(costOncartPage);

		// To delete an item
		clickButton("Delete");
	}

	/**
	 * Search and select a product.
	 * 
	 * @param methodName
	 * @param requiredItem
	 * @author vinothkumar.p08@infosys.com
	 */
	private void searchSelect(String methodName, String requiredItem) {
		// Search and Product
		searchItem(methodName, requiredItem);

		// wait for an element
		waitForElement(driver, firstItemTitle);
		System.out.println("PageSource of search result: " + driver.getPageSource());

		int randomNumber = randomInt();
		for (int i = 0; i < randomNumber; i++) {
			swipe(driver);
		}
		if (randomNumber == 0) {
			randomNumber = 1;
		}
		// Select an random item from searched result based on random number
		String selectedItem = selectRandomItem(randomNumber);
		System.out.println("Selected Item: " + selectedItem);
	}

	/**
	 * This method is to click any button bases on parameter
	 * 
	 * @param buttonName
	 * @author vinothkumar.p08@infosys.com
	 */
	private void clickButton(String buttonName) {
		for (WebElement button : btn) {
			System.out.println("Button Text: " + button.getText());
			if (button.getTagName().contains("Delete")) {
				button.click();
				BaseSuite.logInfo("Deleted an item on Cart Page");

			}
		}
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
		loginText.clear();
		loginText.clear();
		loginText.sendKeys(password);
		loginText.clear();
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
				BaseSuite.logInfo("Selected Language: " + " <b> " + language + "</b>");
				radioButton.click();
			}
		}
		getScreenShot(driver, "Selected Language", methodName);

		for (WebElement saveButton : btn) {
			System.out.println("Buttons name: " + saveButton.getText());
			if (saveButton.getText().contains("Save")) {
				saveButton.click();
				BaseSuite.logInfo("Clicked on Save button after saving language");
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
	private void selectOneTimePurchase(String methodName) {
		for (WebElement radioBtns : radioBtns) {
			System.out.println("Radio button available: " + radioBtns.getText());
			if (radioBtns.getText().contains("One-time purchase")) {
				String itemValue = radioBtns.getText();
				System.out.println("Item value on One-Time Purchase is: " + itemValue);
				int size = itemValue.length();
				System.out.println("Size of the radio button text is: " + size);
				String cost = itemValue.substring(11);
				System.out.println("Cost of item: " + cost);
				radioBtns.click();
				BaseSuite.getScreenShot(driver, "One-Time-Purchase", methodName);
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
				log.info("Selected Product Name: " + productName);
				System.out.println("Selected Product Name: " + productName);
				// Setting product name to result class object
				result.setProductName(productName);
				BaseSuite.logInfo("Product Name selected: " + "<b>" + productName + "</b>");
				ele.click();
				BaseSuite.logInfo("Clicked on product");
				break;
			}

		}
	}

	/**
	 * This is to select an item based on user's input
	 * 
	 * @param requiredItem
	 * @author vinothkumar.p08@infosys.com
	 */
	private String selectRandomItem(int randomNumber) {
		String s = "(//android.widget.TextView[@resource-id='com.amazon.mShop.android.shopping:id/item_title'])";

		String xpath = s + "[" + randomNumber + "]";

		try {
			BaseSuite.logInfo("Randomly selected product is: " + driver.findElement(By.xpath(xpath)).getText());
			log.info("Randomly selected product is: " + driver.findElement(By.xpath(xpath)).getText());
			productName = driver.findElement(By.xpath(xpath)).getText();
			// Setting product name to result class object
			result.setProductName(productName);
			BaseSuite.logInfo("Product Name selected: " + "<b>" + productName + "</b>");
		} catch (Exception e) {
			BaseSuite.logInfo("Random Xpath is: " + xpath);
			log.info("Random Xpath is: " + xpath);
			Assert.fail("Element not identified by given xpath: " + xpath);
		}
		try {
			driver.findElement(By.xpath(xpath)).click();
			BaseSuite.logInfo("Clicked on product");
		} catch (Exception e) {
			Assert.fail("Element not identified by given xpath: " + xpath);
		}

		return productName;
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
	private String getTextOnPageBackup(List<WebElement> elements, String productName) {

		for (WebElement ele : elements) {
			System.out.println("Text on the page: " + ele.getText());
			if (ele.getText().contains(productName)) {
				System.out.println("Item name on Cart Page: " + ele.getText());
				productCost = ele.getText();
				break;
			}
		}
		return productCost;
	}

	/**
	 * To click cart icon
	 * 
	 * @author vinothkumar.p08@infosys.com
	 */
	public void clickCart() {

		try {

			cartIcon.click();
		} catch (Exception e) {
			// TODO: handle exception
		}
		try {

			cartIcon1.click();
		} catch (Exception e) {
			// TODO: handle exception
		}
		try {

			cartIcon2.click();
		} catch (Exception e) {
			// TODO: handle exception
		}
		try {

			cartIcon3.click();
		} catch (Exception e) {
			// TODO: handle exception
		}

	}

}
