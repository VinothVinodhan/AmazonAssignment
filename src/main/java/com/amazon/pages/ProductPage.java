package com.amazon.pages;

import java.util.List;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.base.BaseSuite;
import com.base.Result;

import io.appium.java_client.android.AndroidDriver;

public class ProductPage extends BaseSuite {
	Logger log = Logger.getLogger(ProductPage.class);

	static AndroidDriver driver;
	Result result = new Result();

	/**
	 * Constructor to pass driver & initialize page factory.
	 * 
	 * @param driver
	 * @author vinothkumar.p08@infosys.com
	 */
	public ProductPage(AndroidDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);

	}

	@FindBy(className = "android.widget.Button")
	private List<WebElement> btn;

	@FindBy(className = "android.view.View")
	private List<WebElement> textsOnThePage;

	@FindBy(className = "android.widget.RadioButton")
	private List<WebElement> radioBtns;

	@FindBy(className = "android.widget.RadioButton")
	private WebElement radioBtn;

	@FindBy(className = "android.widget.Spinner")
	private WebElement quantity;

	String sizeOfOil = null;
	String productName = null;
	String quantityOfProduct = null;
	String productCost = null;

	/**
	 * Method is to search and select the random product using user's key word.
	 * 
	 * @param methodName
	 * @param language
	 * @author vinothkumar.p08@infosys.com
	 */
	public void product(String methodName, String language) {
		// To get size of the product
		String sizeOfItem = sizeOfProduct(methodName);
		System.out.println("Returned from sizeOfItem method: " + sizeOfItem);

		// This is just to swipe little to see the product cost
		swipeDown(driver);

		// Selecting language after logged into the account
		try {
			selectLanguage(methodName, language);
		} catch (Exception e) {
			// TODO: handle exception
		}

		String costOnProduct = getTextOnPageBackup(textsOnThePage, "rupees");
		if (costOnProduct == null) {
			costOnProduct = "null";
		}
		// setting product cost
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

	}

	/**
	 * To get the size of the product
	 * 
	 * @param methodName
	 * @author vinothkumar.p08@infosys.com
	 */
	private String sizeOfProduct(String methodName) {
		System.out.println("Page Source of Product Page: " + driver.getPageSource());
		try {
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
		} catch (Exception e) {
			System.out.println("Element not idenntified.");
		}

		return sizeOfOil;
	}

	/**
	 * To click One-Time-Purchase radio button. This is an optional
	 * 
	 * @author vinothkumar.p08@infosys.com
	 */
	private void selectOneTimePurchase(String methodName) {
		waitForElement(driver, radioBtn);
		for (WebElement buttons : radioBtns) {
			System.out.println("Radio button available: " + buttons.getText());
			if (buttons.getText().contains("One-time purchase")) {
				String itemValue = buttons.getText();
				System.out.println("Item value on One-Time Purchase is: " + itemValue);
				int size = itemValue.length();
				System.out.println("Size of the radio button text is: " + size);
				String cost = itemValue.substring(11);
				System.out.println("Cost of item: " + cost);
				waitAndClick(driver, buttons, BaseSuite.WAIT_TEN_SECONDS);
				// buttons.click();
				BaseSuite.getScreenShot(driver, "One-Time-Purchase", methodName);
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
}
