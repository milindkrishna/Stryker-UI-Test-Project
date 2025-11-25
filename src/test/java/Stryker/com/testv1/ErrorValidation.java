package Stryker.com.testv1;

import org.testng.Assert;
import org.testng.annotations.Test;


import Stryker.com.TestComponents.BaseTest;
import stryker.pageobjects.CartPage;
import stryker.pageobjects.ProductCatalogue;
import Stryker.com.TestComponents.Retry;

public class ErrorValidation extends BaseTest {

	@Test(groups = { "ErrorHandling" }, retryAnalyzer = Retry.class)
	public void ErrorValidationLogin() throws InterruptedException {
		landingPage.loginApplication("milindkrishna1998@gmail.com", "Milind@98");
		String errorMessage = landingPage.getErrorMessage();
		Assert.assertEquals("Incorrect email or password.", errorMessage);

	}

	@Test(groups = { "ErrorHandling" }, retryAnalyzer = Retry.class)
	public void ErrorValidationproduct() throws InterruptedException {

		String productName = "ZARA COAT 3";
		ProductCatalogue productCatalogue = landingPage.loginApplication("milindkrishna1998@gmail.com", "Milind@98");
		productCatalogue.addProductToCart(productName);
		CartPage cartPage = productCatalogue.goToCart();
		boolean match = cartPage.verifyProductDisplay(productName);
		Assert.assertTrue(match);

	}
}
