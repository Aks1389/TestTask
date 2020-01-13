package com.epam.telenettv.portal.qa.pages;

import org.openqa.selenium.support.FindBy;

import com.epam.jdi.uitests.web.selenium.elements.common.Button;
import com.epam.jdi.uitests.web.selenium.elements.common.Text;
import com.epam.jdi.uitests.web.selenium.elements.composite.WebPage;
import com.epam.telenettv.portal.qa.enumerations.Pages;

import lombok.Getter;

public abstract class CustomWebPage extends WebPage {

	@Getter
	protected Pages pageName;
	
	@Getter
	@FindBy(xpath = "//p[@class = 'maintenance__message']")
	private Text maintenanceMessage;
	
	@Getter
	@FindBy(xpath = "//span[text() = 'Refresh']/parent::button")
	private Button refresh;
	
	public void waitPageLoaded() {
	}
}
