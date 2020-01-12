package com.epam.telenettv.portal.qa.sections;

import static com.epam.jdi.uitests.core.settings.JDISettings.logger;
import static com.epam.telenettv.portal.qa.site.TelenettvSite.isElementBeyondTheScreen;

import java.util.List;
import java.util.stream.Collectors;

import org.openqa.selenium.support.FindBy;

import com.epam.commons.Timer;
import com.epam.jdi.uitests.web.selenium.elements.common.Label;
import com.epam.jdi.uitests.web.selenium.elements.composite.Section;
import com.epam.telenettv.portal.qa.sections.carusel.CaruselBelt;
import com.epam.telenettv.portal.qa.sections.carusel.Tile;
import com.epam.telenettv.utils.AwaitilityHelper;

import lombok.Getter;

public class ContentSection extends Section {

	@FindBy(xpath = ".//span[@class = 'section-title']")
	private Label label;

	@Getter
	@FindBy(xpath = ".//div[@class = 'carousel-wrapper horizontal']")
	private CaruselBelt carucelBelt;

	public String getLabel() {
		return label.getText();
	}

	public Tile getItem(String title) {
		return carucelBelt.findItem(title);
	}

	/**
	 * Takes elements in a carucel that are within screen frames and waits until all posters are loaded
	 * */
	public void waitLoaded() {
		carucelBelt.waitLoaded(label.getText());
	}
}
