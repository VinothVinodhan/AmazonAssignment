package com.amazon.pages;

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
		driver = (AndroidDriver) obj.getAppiumDriver();
		PageFactory.initElements(driver, this);

	}

	@FindBy(id = "com.amazon.mShop.android.shopping:id/rs_search_src_text")
	private WebElement searchField;

	@FindBy(id = "com.amazon.mShop.android.shopping:id/item_title")
	private List<WebElement> itemTitle;

	@FindBy(id = "com.amazon.mShop.android.shopping:id/item_title")
	private WebElement firstItemTitle;

	@FindBy(id = "add-to-cart-button")
	private WebElement addToCartButton;

	@FindBy(id = "com.amazon.mShop.android.shopping:id/chrome_action_bar_cart_image")
	private WebElement cartIcon;

	@FindBy(id = "com.amazon.mShop.android.shopping:id/chrome_action_bar_cart")
	private WebElement cartIcon1;

	@FindBy(xpath = "//android.widget.ImageView[@content-desc='Cart']")
	private WebElement cartIcon2;

	@FindBy(xpath = "/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.support.v4.widget.DrawerLayout/android.widget.RelativeLayout/android.widget.RelativeLayout[2]/android.widget.RelativeLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.LinearLayout[1]/android.widget.RelativeLayout/android.widget.LinearLayout[2]/android.widget.FrameLayout")
	private WebElement cartIcon3;

	@FindBy(className = "android.widget.Button")
	private List<WebElement> buttons;

	@FindBy(className = "android.widget.Button")
	private WebElement continueButton;

	@FindBy(className = "android.widget.RadioButton")
	private List<WebElement> radioButtons;

	@FindBy(className = "android.widget.RadioButton")
	private WebElement radioButton;

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
	private WebElement costOfProduct;

	@FindBy(xpath = "/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.support.v4.widget.DrawerLayout/android.widget.RelativeLayout/android.widget.RelativeLayout[2]/android.widget.RelativeLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.LinearLayout[2]/android.widget.ViewAnimator/android.widget.LinearLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.view.View/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.RelativeLayout/android.widget.RelativeLayout/android.webkit.WebView/android.webkit.WebView/android.view.View/android.view.View/android.view.View/android.view.View/android.view.View/android.view.View/android.view.View[2]/android.view.View[1]/android.view.View/android.view.View/android.view.View[1]/android.view.View[4]/android.view.View[1]/android.view.View/android.view.View[2]")
	private WebElement deleveryBy;

	@FindBy(xpath = "/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.support.v4.widget.DrawerLayout/android.widget.RelativeLayout/android.widget.RelativeLayout[2]/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.LinearLayout[2]/android.widget.ViewAnimator/android.view.View/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.RelativeLayout/android.widget.RelativeLayout/android.webkit.WebView/android.webkit.WebView/android.view.View/android.view.View/android.view.View[1]/android.view.View/android.view.View[4]")
	private WebElement costElementCart;

	String sizeOfOil = null;
	String productName = null;
	String quantityOfProduct = null;
	String productCost = null;

	/**
	 * Method to handle home page and search & select the product based on
	 * user's search.
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
	public Result searchAndSelectProduct(String methodName, String searchProduct, String requiredItem, String mobileNumber,
			String password, String language, String pincode) {

		// Entering Pin Code is optional
		// enterPincode();

		// Selecting language after logged into the account
		selectLanguage(methodName, language);

		// Search and Product
		selectProduct(methodName, requiredItem);
		productName = result.getProductName();

		// Take screenshot
		BaseSuite.logInfo("After selecting the product from searched result.");
		BaseSuite.getScreenShot(driver, "Selected Item", methodName);
		log.info("Search Result Page screenshot taken");
		return result;

	}

	/**
	 * Search and select a product.
	 * 
	 * @param methodName
	 * @param requiredItem
	 * @author vinothkumar.p08@infosys.com
	 */
	private void selectProduct(String methodName, String requiredItem) {
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
		for (WebElement button : buttons) {
			System.out.println("Button Text: " + button.getText());
			if (button.getTagName().contains("Delete")) {
				button.click();
				BaseSuite.logInfo("Deleted an item on Cart Page");

			}
		}
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
		waitForElement(driver, radioButton);
		// Selecting Lanugage based on user input
		for (WebElement radioButton : radioButtons) {
			System.out.println("Languages appeared on the screen: " + radioButton.getText());
			if (radioButton.getText().contains(language)) {
				BaseSuite.logInfo("Selected Language: " + " <b> " + language + "</b>");
				radioButton.click();
			}
		}
		getScreenShot(driver, "Selected Language", methodName);

		for (WebElement saveButton : buttons) {
			System.out.println("Buttons name: " + saveButton.getText());
			if (saveButton.getText().contains("Save")) {
				saveButton.click();
				BaseSuite.logInfo("Clicked on Save button after saving language");
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
		// Identifying element based on random number
		String s = "(//android.widget.TextView[@resource-id='com.amazon.mShop.android.shopping:id/item_title'])";
		// Randomized xpath to find the product from search result
		String xpath = s + "[" + randomNumber + "]";
		BaseSuite.logInfo("Randomized identifier is: " + xpath);
		log.info("Randomized identifier is: " + xpath);

		try {
			BaseSuite.logInfo("Randomly selected product is: " + driver.findElement(By.xpath(xpath)).getText());
			log.info("Randomly selected product is: " + driver.findElement(By.xpath(xpath)).getText());
			productName = driver.findElement(By.xpath(xpath)).getText();
			// Setting product name to result class object
			result.setProductName(productName);
			BaseSuite.logInfo("Product Name selected: " + "<b>" + productName + "</b>");
		} catch (Exception e) {
			Assert.fail(
					"Identifier using random integer is not detected the element on the page. Hence failing the test case.");
		}
		try {
			driver.findElement(By.xpath(xpath)).click();
			BaseSuite.logInfo("Clicked on product");
		} catch (Exception e) {
			Assert.fail(
					"Identifier using random integer is not detected the element on the page. Hence failing the test case.");
		}

		return productName;
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

}
