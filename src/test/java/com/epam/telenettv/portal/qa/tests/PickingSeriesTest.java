package com.epam.telenettv.portal.qa.tests;

import static com.epam.jdi.uitests.core.preconditions.PreconditionsState.isInState;
import static com.epam.telenettv.portal.qa.enumerations.Preconditions.IS_HOME_PAGE_OPENED;
import static com.epam.telenettv.portal.qa.enumerations.Preconditions.IS_MOVIES_AND_SERIES_PAGE_OPENED;
import static com.epam.telenettv.portal.qa.site.TelenettvSite.itemPage;
import static com.epam.telenettv.portal.qa.site.TelenettvSite.moviesAndSeriesPage;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.epam.telenettv.portal.qa.InitTelenettvTest;

public class PickingSeriesTest extends InitTelenettvTest{

	@BeforeMethod
	public void prep() {
		isInState(IS_HOME_PAGE_OPENED);
	}
	
	@Test(invocationCount = 10)
	public void pickSeriesTest() {
		isInState(IS_MOVIES_AND_SERIES_PAGE_OPENED);
		
		String contentSectionName = "Home of HBO";
		String showTitle = "Watchmen";
		int episodeNumber = 9;
		
		moviesAndSeriesPage.getContentSection(contentSectionName).getItem(showTitle).clickLink();
		Assert.assertEquals(itemPage.getItemTitle().getText(), showTitle);
		
		itemPage.getEpisode(episodeNumber).clickLink();
		itemPage.checkSeriesEpisodeNumber(episodeNumber);
		itemPage.goBack(moviesAndSeriesPage);
	}
}