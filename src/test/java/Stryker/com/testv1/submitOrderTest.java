package Stryker.com.testv1;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import Stryker.com.TestComponents.BaseTest;
import stryker.pageobjects.CartPage;
import stryker.pageobjects.CheckoutPage;
import stryker.pageobjects.ConfimationPage;
import stryker.pageobjects.OrderPage;
import stryker.pageobjects.ProductCatalogue;
import Stryker.com.TestComponents.Retry;

public class submitOrderTest extends BaseTest {

	private static String username = "milindkrishna1998@gmail.com";
	private static String password = "Milind@98";
	private static String productName = "ZARA COAT 3";
	private static String countryName = "india";

	// launch browser

	@Test(dataProvider = "getData", groups = { "Purchase" })
	public void submitOrder(HashMap<String, String> input) throws Exception {
		// product catalogue page
		ProductCatalogue productCatalogue = landingPage.loginApplication(input.get("email"), input.get("password"));
		//List<WebElement> products = productCatalogue.getProductList();
		//productCatalogue.addProductToCart(input.get("product"));
		// cart page
		CartPage cartPage = productCatalogue.goToCart();
		Boolean match = cartPage.verifyProductDisplay(input.get("product"));
		Assert.assertTrue(match);

		// checkout page
		CheckoutPage checkoutpage = cartPage.goToCheckout();
		checkoutpage.selectCountry(countryName);

		// Confirmation page
		ConfimationPage cnf = checkoutpage.submitOrder();
		
		String confirmMessage = cnf.getConfirmationMessage();
		Assert.assertTrue(confirmMessage.equalsIgnoreCase("THANKYOU FOR THE ORDER."));

	}

	@Test(dependsOnMethods = {"submitOrder"}, retryAnalyzer = Retry.class)
	public void OrderHistoryTest() {
		ProductCatalogue productCatalogue = landingPage.loginApplication(username, password);
		OrderPage ordersPage = productCatalogue.goToOrders();
		Boolean match = ordersPage.verifyOrderDisplay(productName);
		Assert.assertTrue(match);

	}
	
	
	// extends report
	

	@DataProvider
	public Object[][] getData() throws IOException {

		// one way of data driven for multiple set of data
		// return new Object[][] {{"milindkrishna1998@gmail.com", "Milind@98", "ZARA
		// COAT 3"},{"soumilighosh@gmail.com", "Milind@1998", "ADIDAS ORIGINAL"} };

		// 2nd way of data driven using HashMap
//		HashMap<String, String> map = new HashMap<String, String>();
//		map.put("email", "milindkrishna1998@gmail.com");
//		map.put("password", "Milind@98");
//		map.put("product", "ZARA COAT 3");
//		
//		HashMap<String, String> map1 = new HashMap<String, String>();
//		map1.put("email", "soumilighosh@gmail.com");
//		map1.put("password", "Milind@1998");
//		map1.put("product", "ADIDAS ORIGINAL");
		
		// 3rd way of data driven using external json file using jackson databind

		List<HashMap<String, String>> data = getJsonData(
				System.getProperty("user.dir") + "\\src\\test\\java\\data\\PurchaseOrder.json");

		return new Object[][] { { data.get(0) }, { data.get(1) } };
	}

}
