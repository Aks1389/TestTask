package com.epam.telenettv.portal.qa.tests;

import static com.epam.telenettv.portal.qa.site.TelenettvSite.*;

import org.testng.annotations.Test;

import com.epam.telenettv.portal.qa.InitTelenettvTest;
import com.epam.telenettv.portal.qa.enumerations.Pages;

public class PickingSeriesTest extends InitTelenettvTest{

	@Test
	public void pickSeriesTest() {
		openPage(Pages.MOVIES_AND_SERIES);
		moviesAndSeries.getContentSection("Home of HBO").getItem("Game of Thrones").clickLink();
	}
}