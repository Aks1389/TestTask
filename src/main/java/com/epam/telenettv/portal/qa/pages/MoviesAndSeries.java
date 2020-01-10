package com.epam.telenettv.portal.qa.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.epam.jdi.uitests.web.selenium.elements.complex.Elements;
import com.epam.jdi.uitests.web.selenium.elements.composite.WebPage;
import com.epam.telenettv.portal.qa.sections.ContentSection;

public class MoviesAndSeries extends WebPage {
	
	@FindBy(xpath = ".//div[@class = 'component-wrapper']/div/ancestor::div[@class = 'contentDiscovery abstractDiscovery section']")
	private Elements<ContentSection> contentSection;
	
	public ContentSection getContentSection(String title) {
		ContentSection foundSection = contentSection.stream().filter(section -> section.getLabel().equals(title))
				             .findFirst().orElse(null);
		WebDriver webDriver = this.getDriver();
		WebDriverWait wait = new WebDriverWait(webDriver, 20);
		WebElement sectionElement = foundSection.getWebElement();
		new Actions(webDriver).moveToElement(sectionElement).build().perform();
		wait.until(ExpectedConditions.visibilityOf(sectionElement));
		return foundSection;
	}
}
