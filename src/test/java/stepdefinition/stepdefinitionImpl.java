package stepdefinition;

import org.testng.Assert;

import Stryker.com.TestComponents.BaseTest;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import stryker.pageobjects.CartPage;
import stryker.pageobjects.CheckoutPage;
import stryker.pageobjects.ConfimationPage;
import stryker.pageobjects.LandingPage;
import stryker.pageobjects.ProductCatalogue;

public class stepdefinitionImpl extends BaseTest {

	public LandingPage landingPage;
	public ProductCatalogue productCatalogue;
	public CartPage cartPage;
	public CheckoutPage checkoutPage;
	public ConfimationPage cnf;

	@Given("User on Eomm landing page")
	public void user_on_eomm_landing_page() throws Exception {
		landingPage = launchApplication();
	}

	@Given("^Logged with username (.+) and password (.+)$")
	public void logged_with_username_and_password(String username, String password) {
		// Write code here that turns the phrase above into concrete actions
		productCatalogue = landingPage.loginApplication(username, password);
	}

	@When("^I add the product (.+) to the cart$")
	public void i_add_the_product_to_the_cart(String productName) throws InterruptedException {
		// List<WebElement> products = productCatalogue.getProductList();
		productCatalogue.addProductToCart(productName);
	}

	@And("^checkout (.+) and submit the order$")
	public void checkout_and_submit_the_order(String productName) throws InterruptedException {
		// Write code here that turns the phrase above into concrete actions
		CartPage cartPage = productCatalogue.goToCart();
		Boolean match = cartPage.verifyProductDisplay(productName);
		Assert.assertTrue(match);

		// checkout page
		checkoutPage = cartPage.goToCheckout();
		checkoutPage.selectCountry("india");

		// Confirmation page
		cnf = checkoutPage.submitOrder();
	}

	@Then("{string} message is displayed on the confirmation page")
	public void message_is_displayed_on_the_confirmation_page(String string) {
		// Write code here that turns the phrase above into concrete actions
		String confirmMessage = cnf.getConfirmationMessage();
		Assert.assertTrue(confirmMessage.equalsIgnoreCase(string));
	}
	
	@And("I close the browser")
	public void i_close_the_browser() {
	    // Write code here that turns the phrase above into concrete actions
	    tearDown();
	}

}
