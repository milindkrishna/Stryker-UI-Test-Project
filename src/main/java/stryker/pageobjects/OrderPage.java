package stryker.pageobjects;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import stryker.AbstractComponents.AbstractComponents;

public class OrderPage extends AbstractComponents {
	
	WebDriver driver;

	public OrderPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(css = "tr td:nth-child(3)")
	private List<WebElement> OrderItems;
	
	@FindBy(css = "tbody tr th:nth-child(1)")
	private  List<WebElement> firstOrderId;
	
	
	public boolean verifyOrderDisplay(String productName) {
		Boolean match = OrderItems.stream().anyMatch(item -> item.getText().equalsIgnoreCase(productName));
		return match;
	}
	
	

}
