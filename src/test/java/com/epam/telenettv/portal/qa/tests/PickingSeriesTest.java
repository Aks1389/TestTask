package com.epam.telenettv.portal.qa.tests;

import static com.epam.jdi.uitests.core.preconditions.PreconditionsState.isInState;
import static com.epam.telenettv.portal.qa.enumerations.Preconditions.*;
import static com.epam.telenettv.portal.qa.site.TelenettvSite.*;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.epam.telenettv.portal.qa.InitTelenettvTest;

public class PickingSeriesTest extends InitTelenettvTest{

	@Test
	public void pickSeriesTest() {
		isInState(IS_MOVIES_AND_SERIES_PAGE_OPENED);
		
		String contentSectionName = "Home of HBO";
		String showTitle = "Game of Thrones";
		int episodeNumber = 10;
		
		moviesAndSeriesPage.getContentSection(contentSectionName).getItem(showTitle).clickLink();
		Assert.assertEquals(itemPage.getItemTitle().getText(), showTitle);
		
		itemPage.getEpisode(episodeNumber).clickLink();
		itemPage.checkSeriesEpisodeNumber(episodeNumber);
		itemPage.goBack(moviesAndSeriesPage);
	}
}