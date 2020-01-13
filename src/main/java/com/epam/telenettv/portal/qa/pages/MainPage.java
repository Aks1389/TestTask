package com.epam.telenettv.portal.qa.pages;

import static com.epam.telenettv.portal.qa.site.TelenettvSite.waitIfMaintenanceFinished;

import com.epam.telenettv.portal.qa.enumerations.Pages;

public class MainPage extends CustomWebPage{

	public MainPage() {
		this.pageName = Pages.HOME;
	}
	
	@Override
	public void waitPageLoaded() {
		waitIfMaintenanceFinished(this);
	}

	public void openPage() {
		open();
		waitPageLoaded();
	}
}
