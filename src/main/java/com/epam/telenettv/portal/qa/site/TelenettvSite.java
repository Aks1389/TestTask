package com.epam.telenettv.portal.qa.site;

import org.openqa.selenium.support.FindBy;

import com.epam.commons.Timer;
import com.epam.jdi.uitests.web.selenium.elements.base.Element;
import com.epam.jdi.uitests.web.selenium.elements.composite.WebSite;
import com.epam.jdi.uitests.web.selenium.elements.pageobjects.annotations.JPage;
import com.epam.telenettv.portal.qa.enumerations.Pages;
import com.epam.telenettv.portal.qa.pages.ItemPage;
import com.epam.telenettv.portal.qa.pages.MainPage;
import com.epam.telenettv.portal.qa.pages.MoviesAndSeries;
import com.epam.telenettv.portal.qa.sections.Header;
import com.epam.telenettv.portal.qa.sections.Sidebar;

import lombok.Getter;
import lombok.Setter;

public class TelenettvSite extends WebSite {
	@Getter
	@Setter
	private static int waitingElementsTimeout;

	//------------- Pages ------------------------------
	@JPage(url = "/", title = "Home")
	public static MainPage mainPage;
	
	@JPage(urlTemplate = "https?:\\/{2}.+\\/movies-and-series\\/.+\\.html")
	public static MoviesAndSeries moviesAndSeries;
	
	@JPage(urlTemplate = "https?:\\/{2}w{3}\\..+asset\\.html.+\\.html")
	public static ItemPage itemPage;
	
	//-------------- Common elements -----------------
	@FindBy(xpath = "//div[@class = 'site-header-wrapper']")
	public static Sidebar sidebar;
	
	@FindBy(xpath = "//div[contains(@class, 'top-menu')]")
	public static Header header;
	
	@FindBy(xpath = "//div[contains(@class,'content-wrapper')]/div[contains(@class, 'aem-Grid')]")
	public static Element contentPresenceIndicator;

	
	public static void waitLoading() {
		waitLoading(waitingElementsTimeout);
	}
	
	public static void waitLoading(int timeoutSec) {
		contentPresenceIndicator.setWaitTimeout(timeoutSec);
		contentPresenceIndicator.waitDisplayed();
	}
	
	public static void openPage(Pages page) {
		sidebar.selectPage(page);
		Timer.sleep(2000);
		waitLoading();
	}
}
