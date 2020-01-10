package com.epam.telenettv.portal.qa.sections.carusel;

import org.openqa.selenium.support.FindBy;

import com.epam.jdi.uitests.web.selenium.elements.common.Image;
import com.epam.jdi.uitests.web.selenium.elements.common.Link;
import com.epam.jdi.uitests.web.selenium.elements.common.Text;
import com.epam.jdi.uitests.web.selenium.elements.composite.Section;

import lombok.Getter;

public class Tile extends Section{

	@FindBy(xpath = ".//img")
	private Image poster;
	
	@FindBy(xpath = ".//a[@class = 'poster-tile__link']")
	private Link link;
	
	@Getter
	@FindBy(xpath = ".//div[contains(@class, 'ui-kit-tile-string--primary')]")
	private Text title;
	
	public void clickLink() {
		link.click();
	}
}
