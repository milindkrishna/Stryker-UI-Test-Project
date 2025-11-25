package stryker.pageobjects;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import stryker.AbstractComponents.AbstractComponents;

public class CartPage extends AbstractComponents {

	WebDriver driver;

	public CartPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath = "//button[normalize-space()='Checkout']")
	private WebElement checkoutBtn;

	// private By cartItemsBy = By.cssSelector(".cart ul li h3");
	
	@FindBy(css = ".cartSection h3")
	private List<WebElement> cartProducts;

	public Boolean verifyProductDisplay(String productName) {
		// wait for cart items to be present then re-fetch to avoid stale/empty PageFactory list
		// waitForElementToAppear(cartItemsBy);
		// List<WebElement> items = driver.findElements(cartItemsBy);
		// return items.stream().anyMatch(item -> item.getText().trim().equalsIgnoreCase(productName));
		Boolean match = cartProducts.stream().anyMatch(product -> product.getText().equalsIgnoreCase(productName));
		return match;
	}

	public CheckoutPage goToCheckout() {
		checkoutBtn.click();
		CheckoutPage checkoutPage = new CheckoutPage(driver);
		return checkoutPage;
		
	}

}