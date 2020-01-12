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
		String requiredItemName = "Game of Thrones";
		moviesAndSeriesPage.getContentSection("Home of HBO").getItem(requiredItemName).clickLink();
		Assert.assertEquals(itemPage.getTitle(), requiredItemName);
	}
}