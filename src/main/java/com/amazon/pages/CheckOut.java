package com.amazon.pages;

import java.util.List;

import org.apache.log4j.Logger;
import org.junit.Assert;
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
	 * Constructor is to initialize driver & page factory.
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
	private List<WebElement> Buttons;

	@FindBy(id = "com.amazon.mShop.android.shopping:id/chrome_action_bar_cart_image")
	private WebElement cartIcon2;

	@FindBy(id = "com.amazon.mShop.android.shopping:id/chrome_action_bar_cart")
	private WebElement cartIcon1;

	@FindBy(xpath = "//android.widget.ImageView[@content-desc='Cart']")
	private WebElement cartIcon;

	/**
	 * Validating product on cart page by taking cost & name of the product.
	 * 
	 * @param methodName
	 * @author vinothkumar.p08@infosys.com
	 */
	public void validateCartPage(String methodName) {
		// To click cart icon
		clickCart();

		// To get the product name on the cart page
		productName = result.getProductName();
		String requiredProductName = getRequiredText(textsOnThePage, productName);
		BaseSuite.logInfo("Validated ProductName on Cart Page is: " + requiredProductName);
		log.info("Validated ProductName on Cart Page is: " + requiredProductName);
		BaseSuite.getScreenShot(driver, "Cart Page", methodName);

		String costOncartPage = getRequiredText(textsOnThePage, ".00");
		BaseSuite.logInfo("Cost on Cart Page: " + costOncartPage);
		log.info("Cost on Cart Page: " + costOncartPage);
		result.setCostOnCart(costOncartPage);
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
			BaseSuite.logException(e);
			log.info(e);
			BaseSuite.faileStatus("Cart Icon not clicked. Hence Failing this test case.");
			Assert.fail("Cart Icon not clicked. Hence Failing this test case.");
		}
	}

	/**
	 * To get the product Name
	 * 
	 * @param elements
	 * @param productName
	 * @author vinothkumar.p08@infosys.com
	 */
	private String getRequiredText(List<WebElement> elements, String productName) {

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
	 * Method is to get cost of the product using xpath.
	 * 
	 * @return
	 * @author vinothkumar.p08@infosys.com
	 */
	private String getCostOfProduct() {
		String costOnCartPage = null;
		// Below code is to get item cost on cart page
		try {
			costOnCartPage = costElementCart.getText();
			BaseSuite.logInfo("Cost on Cart Page: " + costOnCartPage);
		} catch (Exception e) {
			BaseSuite.logInfo("Cose of the product on Cart page not taken.");
		}
		return costOnCartPage;
	}

	/**
	 * This method is to click any button bases on parameter
	 * 
	 * @param buttonName
	 * @author vinothkumar.p08@infosys.com
	 */
	private void deleteProduct(String buttonName) {
		try {
			for (WebElement button : Buttons) {
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
