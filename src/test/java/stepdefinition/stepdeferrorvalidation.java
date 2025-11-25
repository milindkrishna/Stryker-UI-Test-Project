package stepdefinition;

import Stryker.com.TestComponents.BaseTest;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import stryker.pageobjects.LandingPage;

public class stepdeferrorvalidation extends BaseTest {
	
	public LandingPage landingPage;

	@Given("User is on Eomm landing page")
	public void user_is_on_eomm_landing_page() throws Exception {
		landingPage = launchApplication();
	}
	
	@And("^Logged in with username (.+) and password (.+)$")
	public void logged_in_with_username_and_password(String username, String password) {
		// Write code here that turns the phrase above into concrete actions
		landingPage.loginApplication(username, password);
	}
	
	@Then("{string} message is displayed")
	public void message_is_displayed(String string) {
		// Write code here that turns the phrase above into concrete actions
		String errorMessage = landingPage.getErrorMessage();
		org.testng.Assert.assertEquals(string, errorMessage);
	}
	
	@And("I close the browser and end the session")
	public void close_the_browser_and_end_the_session() {
	    // Write code here that turns the phrase above into concrete actions
	    tearDown();
	}
	
}
