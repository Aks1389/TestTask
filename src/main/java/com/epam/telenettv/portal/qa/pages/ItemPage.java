package com.epam.telenettv.portal.qa.pages;

import java.util.concurrent.TimeUnit;

import org.awaitility.Awaitility;
import org.openqa.selenium.support.FindBy;

import com.epam.jdi.uitests.web.selenium.elements.common.Button;
import com.epam.jdi.uitests.web.selenium.elements.common.Label;
import com.epam.telenettv.portal.qa.sections.carusel.CaruselBelt;
import com.epam.telenettv.utils.AwaitilityHelper;

import lombok.Getter;

public class ItemPage extends CustomWebPage {

	@FindBy(xpath = ".//span[contains(@class, 'back-button')]")
	private Button back;
	
	@Getter
	@FindBy(xpath = ".//div[@class = 'orion-titlecard-header']//h1")
	private Label itemTitle;
	
	@Getter
	@FindBy(xpath = ".//div[@class = 'orion-titlecard-header']//h2")
	private Label itemSecondaryTitle;
	
	@Getter
	@FindBy(xpath = ".//div[@class = 'orion-titlecard-episodes-picker']//div[@class = 'carousel-wrapper horizontal']")
	private CaruselBelt carucelBelt;

	@Override
	public void waitPageLoaded() {
		AwaitilityHelper.await(10, () -> itemTitle.isDisplayed(), 3, "Coudln't wait until item title appeared.");
		carucelBelt.waitLoaded(itemTitle.getText());
	}
	
	public void goBack() {
		back.click();
		
	}
	
}
