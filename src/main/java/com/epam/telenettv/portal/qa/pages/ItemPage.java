package com.epam.telenettv.portal.qa.pages;

import static com.epam.jdi.uitests.core.settings.JDISettings.logger;

import java.util.concurrent.TimeUnit;

import org.awaitility.Awaitility;
import org.openqa.selenium.support.FindBy;

import com.epam.jdi.uitests.web.selenium.elements.common.Label;
import com.epam.telenettv.portal.qa.sections.carusel.CaruselBelt;

import lombok.Getter;

public class ItemPage extends CustomWebPage {

	@Getter
	@FindBy(xpath = ".//div[@class = 'orion-titlecard-header']//h1")
	private Label itemTitle;
	
	@Getter
	@FindBy(xpath = ".//div[@class = 'orion-titlecard-episodes-picker']//div[@class = 'carousel-wrapper horizontal']")
	private CaruselBelt carucelBelt;

	@Override
	public void waitPageLoaded() {
		Awaitility.await().with().pollInterval(3, TimeUnit.SECONDS)
		                         .timeout(10, TimeUnit.SECONDS)
		          .until(() -> itemTitle.isDisplayed());
	}
}
