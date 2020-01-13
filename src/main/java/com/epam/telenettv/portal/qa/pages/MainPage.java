package com.epam.telenettv.portal.qa.pages;

import static com.epam.telenettv.portal.qa.site.TelenettvSite.waitIfMaintenanceFinished;

public class MainPage extends CustomWebPage{

	@Override
	public void waitPageLoaded() {
		waitIfMaintenanceFinished(this);
	}

	public void openPage() {
		open();
		waitPageLoaded();
	}
}
