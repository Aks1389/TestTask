package com.epam.telenettv.portal.qa.site;

import java.awt.Dimension;
import java.awt.Toolkit;

import org.openqa.selenium.Rectangle;
import org.openqa.selenium.support.FindBy;

import com.epam.jdi.uitests.core.interfaces.complex.tables.interfaces.CheckPageTypes;
import com.epam.jdi.uitests.core.logger.LogLevels;
import com.epam.jdi.uitests.web.selenium.elements.base.Element;
import com.epam.jdi.uitests.web.selenium.elements.composite.WebSite;
import com.epam.jdi.uitests.web.selenium.elements.pageobjects.annotations.JPage;
import com.epam.jdi.uitests.web.settings.WebSettings;
import com.epam.telenettv.portal.qa.enumerations.Pages;
import com.epam.telenettv.portal.qa.pages.CustomWebPage;
import com.epam.telenettv.portal.qa.pages.ItemPage;
import com.epam.telenettv.portal.qa.pages.MainPage;
import com.epam.telenettv.portal.qa.pages.MoviesAndSeries;
import com.epam.telenettv.portal.qa.sections.Header;
import com.epam.telenettv.portal.qa.sections.Sidebar;
import com.epam.telenettv.utils.AwaitilityHelper;

import lombok.Getter;
import lombok.Setter;

public class TelenettvSite extends WebSite {
	@Getter
	@Setter
	private static int waitingElementsTimeout;

	//------------- Pages ------------------------------
	@JPage(url = "", title = "Home")
	public static MainPage mainPage;
	
	@JPage(urlTemplate = "https?:\\/{2}.+\\/movies-and-series\\/.+\\.html", urlCheckType = CheckPageTypes.MATCH)
	public static MoviesAndSeries moviesAndSeriesPage;
	
	@JPage(urlTemplate = "https?:\\/{2}w{3}\\..+asset\\.html.+\\.html", urlCheckType = CheckPageTypes.MATCH)
	public static ItemPage itemPage;
	
	//-------------- Common elements -----------------
	@FindBy(xpath = "//div[@class = 'site-header-wrapper']")
	public static Sidebar sidebar;
	
	@FindBy(xpath = "//div[contains(@class, 'top-menu')]")
	public static Header header;
	
	public static void openPage(Pages page) {
		sidebar.selectPage(page);
		
	}
	
	public static boolean isElementBeyondTheScreen(Element element) {
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		Rectangle rectangle = element.getWebElement().getRect();
		return rectangle.getX() > screenSize.getWidth() ||
			   rectangle.getY() > screenSize.getHeight();
	}
	
	public static void waitIfMaintenanceFinished(CustomWebPage page) {
		page.getMaintenanceMessage().setWaitTimeout(3);
		try {
			page.getMaintenanceMessage().waitDisplayed();
		}
		catch (AssertionError e) {
			WebSettings.logger.setLogLevel(LogLevels.DEBUG);
		}
		AwaitilityHelper.await(20, () ->{
			if(page.getMaintenanceMessage().isDisplayed()) {
				page.getRefresh().click();
			}
			return !page.getMaintenanceMessage().isDisplayed();
		}, 2, "Couldn't wait finish of maintenance on site.");
	}
	
	public static void moveElementWithinScreenFrames(Element element) {
		try {
			AwaitilityHelper.await(5, () ->{
				element.focus();
				return !isElementBeyondTheScreen(element);
			}, 1, "");
		}
		catch (Exception e) {
			WebSettings.logger.setLogLevel(LogLevels.DEBUG);
		}
	}
}
