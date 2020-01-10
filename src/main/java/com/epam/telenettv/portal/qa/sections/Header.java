package com.epam.telenettv.portal.qa.sections;

import org.openqa.selenium.support.FindBy;

import com.epam.jdi.uitests.web.selenium.elements.common.Label;
import com.epam.jdi.uitests.web.selenium.elements.composite.Section;
import com.epam.telenettv.portal.qa.enumerations.Pages;

public class Header extends Section{

	@FindBy(xpath = ".//div[@class = 'utility-bar']//span[@class = 'utility-bar-title']")
	private Label pageName;
	
	public String getCurrentPageName() {
		return pageName.isDisplayed() ? pageName.getText() : Pages.HOME.getValue();
	}
}
