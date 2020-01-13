package com.epam.telenettv.portal.qa.sections;

import org.openqa.selenium.support.FindBy;

import com.epam.jdi.uitests.web.selenium.elements.common.Label;
import com.epam.jdi.uitests.web.selenium.elements.composite.Section;
import com.epam.telenettv.portal.qa.sections.carusel.CaruselBelt;
import com.epam.telenettv.portal.qa.sections.carusel.Tile;

import lombok.Getter;

public class ContentSection extends Section {

	@FindBy(xpath = ".//span[@class = 'section-title']")
	private Label label;

	@Getter
	@FindBy(xpath = ".//div[@class = 'carousel-wrapper horizontal']")
	private CaruselBelt caruselBelt;

	public String getLabel() {
		return label.getText();
	}

	public Tile getItem(String title) {
		return caruselBelt.findItem(title);
	}

	
	public void waitLoaded() {
		caruselBelt.waitLoaded(label.getText());
	}
}
