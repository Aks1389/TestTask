package com.epam.telenettv.portal.qa.pages;

import static com.epam.jdi.uitests.core.settings.JDISettings.logger;

import org.openqa.selenium.support.FindBy;
import org.testng.Assert;

import com.epam.commons.Timer;
import com.epam.jdi.uitests.web.selenium.elements.complex.Elements;
import com.epam.telenettv.portal.qa.enumerations.Pages;
import com.epam.telenettv.portal.qa.sections.ContentSection;
import com.epam.telenettv.utils.AwaitilityHelper;

public class MoviesAndSeries extends CustomWebPage {
	
	public MoviesAndSeries() {
		this.pageName = Pages.MOVIES_AND_SERIES;
	}
	
	@FindBy(xpath = "//div[@class = 'component-wrapper']/div/ancestor::div[@class = 'contentDiscovery abstractDiscovery section']")
	private Elements<ContentSection> contentSection;
	
	public ContentSection getContentSection(String title) {
		ContentSection foundSection = contentSection.stream().filter(section -> section.getLabel().equals(title))
				             .findFirst().orElse(null);
		Assert.assertNotNull(foundSection);
		foundSection.focus();
		foundSection.waitLoaded();
		return foundSection;
	}

	@Override
	public void waitPageLoaded() {
		AwaitilityHelper.await(10, () -> {
			int size = contentSection.size();
			logger.debug("Number of loaded content sections: " + size);
      	  	return  size == 8;
        }, 3, "Couldn't wait until content appeared.");		
		contentSection.get(0).waitLoaded();
	}
}
