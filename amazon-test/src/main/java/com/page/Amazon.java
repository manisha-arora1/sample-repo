package com.page;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class Amazon {
	@FindBy(id="twotabsearchtextbox")
	WebElement searchBox;
	
	@FindBy(id="nav-search-submit-button")
	WebElement searchButton;
	
	public void enterText(String abc)
	{
		searchBox.sendKeys(abc);
	}
	public void searchItem()
	{
		searchButton.click();
	}

}
