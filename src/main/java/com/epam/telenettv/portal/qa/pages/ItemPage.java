package com.epam.telenettv.portal.qa.pages;

import static com.epam.telenettv.portal.qa.site.TelenettvSite.waitIfMaintenanceFinished;

import org.openqa.selenium.support.FindBy;
import org.testng.Assert;

import com.epam.jdi.uitests.web.selenium.elements.common.Button;
import com.epam.jdi.uitests.web.selenium.elements.common.Label;
import com.epam.telenettv.portal.qa.sections.carusel.CaruselBelt;
import com.epam.telenettv.portal.qa.sections.carusel.Tile;
import com.epam.telenettv.utils.AwaitilityHelper;

import lombok.Getter;

public class ItemPage extends CustomWebPage {
	
	@FindBy(xpath = "//a[contains(@class, 'back-button')]/parent::span")
	private Button back;
	
	@Getter
	@FindBy(xpath = "//div[@class = 'orion-titlecard-header']//h1")
	private Label itemTitle;
	
	@Getter
	@FindBy(xpath = "//div[@class = 'orion-titlecard-header']//h2")
	private Label seriesEpisodeTitle;
	
	@Getter
	@FindBy(xpath = "//div[@class = 'orion-titlecard-episodes-picker']//div[@class = 'carousel-wrapper horizontal']")
	private CaruselBelt caruselBelt;

	@Override
	public void waitPageLoaded() {
		waitIfMaintenanceFinished(this);
		AwaitilityHelper.await(10, () -> itemTitle.isDisplayed(), 3, "Coudln't wait until item title appeared.");
		caruselBelt.waitLoaded(itemTitle.getText());
	}
	
	public void goBack(CustomWebPage pageToReturn) {
		back.click();
		pageToReturn.checkOpened();
		pageToReturn.waitPageLoaded();
	}

	public Tile getEpisode(int episodeNumber) {
		int actualNumber = episodeNumber-1;
		int numberOfEpisodes = caruselBelt.getItems().size();
		Assert.assertFalse(numberOfEpisodes < episodeNumber, 
				String.format("%s doesn't have episode number %s\nActual number of episodes is: %s", 
						      itemTitle.getText(), episodeNumber, numberOfEpisodes));
		return caruselBelt.findItem(actualNumber);
	}

	public void checkSeriesEpisodeNumber(int episodeNumber) {
		String regExp = String.format("S\\s*\\d+.+Ep\\.\\s*%s:\\s*(\\w+\\s?)+", episodeNumber);
		Assert.assertTrue(seriesEpisodeTitle.getText().matches(regExp), 
				String.format("Wrong episode. Expected: %s. Actual: %s", episodeNumber, seriesEpisodeTitle.getText()));
	}
}