package com.amazon.pages;

import java.util.List;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.base.BaseSuite;
import com.base.Result;

import io.appium.java_client.android.AndroidDriver;

public class CheckOut extends BaseSuite {
	String productName = null;
	String productCost = null;

	static AndroidDriver driver;
	static Result result = new Result();
	Logger log = Logger.getLogger(CheckOut.class);

	/**
	 * Constructor is to initilize driver & page factory.
	 * 
	 * @param driver
	 * @author vinothkumar.p08@infosys.com
	 */
	public CheckOut(AndroidDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(className = "android.widget.Image")
	private List<WebElement> text;

	@FindBy(xpath = "/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.support.v4.widget.DrawerLayout/android.widget.RelativeLayout/android.widget.RelativeLayout[2]/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.LinearLayout[2]/android.widget.ViewAnimator/android.view.View/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.RelativeLayout/android.widget.RelativeLayout/android.webkit.WebView/android.webkit.WebView/android.view.View/android.view.View/android.view.View[1]/android.view.View/android.view.View[4]")
	private WebElement costElementCart;

	@FindBy(className = "android.view.View")
	private List<WebElement> textsOnThePage;

	@FindBy(className = "android.widget.Button")
	private List<WebElement> btn;

	@FindBy(id = "com.amazon.mShop.android.shopping:id/chrome_action_bar_cart_image")
	private WebElement cartIcon;

	@FindBy(id = "com.amazon.mShop.android.shopping:id/chrome_action_bar_cart")
	private WebElement cartIcon1;

	@FindBy(xpath = "//android.widget.ImageView[@content-desc='Cart']")
	private WebElement cartIcon2;

	@FindBy(xpath = "/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.support.v4.widget.DrawerLayout/android.widget.RelativeLayout/android.widget.RelativeLayout[2]/android.widget.RelativeLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.LinearLayout[1]/android.widget.RelativeLayout/android.widget.LinearLayout[2]/android.widget.FrameLayout")
	private WebElement cartIcon3;

	/**
	 * Handleing cart page by taking cost & name of the product.
	 * 
	 * @param methodName
	 * @author vinothkumar.p08@infosys.com
	 */
	public void cartPage(String methodName) {

		// To click cart icon
		clickCart();
		productName = result.getProductName();

		// To get the product name on the cart page
		String requiredProductName = getTextOnPageBackup(textsOnThePage, productName);
		// BaseSuite.getTextOnPage(driver, text, productName);
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
	 * To click cart icon
	 * 
	 * @author vinothkumar.p08@infosys.com
	 */
	private void clickCart() {
		try {

			waitAndClick(driver, cartIcon, BaseSuite.WAIT_TEN_SECONDS);
		} catch (Exception e) {
			System.out.println("Element not clicked using: " + cartIcon);
		}
		try {

			waitAndClick(driver, cartIcon1, BaseSuite.WAIT_TEN_SECONDS);
		} catch (Exception e) {
			System.out.println("Element not clicked using: " + cartIcon1);
		}
		try {

			waitAndClick(driver, cartIcon2, BaseSuite.WAIT_TEN_SECONDS);
		} catch (Exception e) {
			System.out.println("Element not clicked using: " + cartIcon2);
		}
		try {

			waitAndClick(driver, cartIcon3, BaseSuite.WAIT_TEN_SECONDS);
		} catch (Exception e) {
			System.out.println("Element not clicked using: " + cartIcon3);
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
	 * This method is to click any button bases on parameter
	 * 
	 * @param buttonName
	 * @author vinothkumar.p08@infosys.com
	 */
	private void clickButton(String buttonName) {
		try {
			for (WebElement button : btn) {
				System.out.println("Button Text: " + button.getText());
				if (button.getTagName().contains("Delete")) {
					button.click();
					BaseSuite.logInfo("Deleted an item on Cart Page");

				}
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

}
