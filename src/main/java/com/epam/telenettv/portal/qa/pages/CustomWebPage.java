package com.epam.telenettv.portal.qa.pages;

import com.epam.jdi.uitests.web.selenium.elements.composite.WebPage;
import com.epam.telenettv.portal.qa.enumerations.Pages;

import lombok.Getter;

public abstract class CustomWebPage extends WebPage {

	@Getter
	protected Pages pageName;
	
	public void waitPageLoaded() {
	}
}
