package stryker.pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import stryker.AbstractComponents.AbstractComponents;

public class ConfimationPage extends AbstractComponents {
	
	WebDriver driver;

	public ConfimationPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	
	@FindBy(css=".hero-primary")
	private WebElement confirmMessage;
	
	public String getConfirmationMessage() {
		return confirmMessage.getText();
	}
	
}
